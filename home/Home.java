package home;

import java.io.IOException;
import java.io.BufferedReader;
import java.io.FileReader;

public class Home {
	
	public Home() {
		
	}
	
	public void readFile(String pathToFile) throws IOException {

		BufferedReader dataFile = new BufferedReader(new FileReader(pathToFile));
		patientCount = getExistingPatientCountFromFile(dataFile);
		existingPatients = new Patient[patientCount];
		
		dataFile.close();
		dataFile = new BufferedReader(new FileReader(pathToFile));
		
		String featureLine = dataFile.readLine(); //Features
		featureNames = featureLine.split("\t");

		int featureCount = featureNames.length-1;
		
		//String id = featureNames[0]; //ID
		
		String patientLine;
		String firstName = "FirstName";
		String lastName = "LastName";
		
		int endOfirstName = 0;
		int endOfLastName = 0;
		
		while ((patientLine = dataFile.readLine()) != null) { //Existing patients
			String[] data = patientLine.split("\t");
			Integer patientID = Integer.parseInt(data[0]);
			
			patientFeatures = new Feature[featureCount];
			
			for (int i = 0; i < featureCount; i++) {
				patientFeatures[i] = store.createFeatureFromName(featureNames[i+1]);
				
				if(data[i+1].isEmpty()) {
					patientFeatures[i].setFeatureValueToNull();
				} else {
					double value = Double.parseDouble(data[i+1]);
					patientFeatures[i].setFeatureValue(value);
				}
			}
			
			String patientFirstName = firstName + "-" + endOfirstName;
			String patientLastName = lastName + "-" + endOfLastName;
			
			//System.out.println("The existing patients first name is: " + patientFirstName);
			//System.out.println("The existing patients last name is: " + patientLastName);
			
			Patient newPatient = createNewPatient(patientID, 
									patientFirstName, 
									patientLastName, 
									patientFeatures);
			endOfirstName++;
			endOfLastName++;
			//System.out.println("Created Patient: " + newPatient.getFirstName() + " " + patientLastName);
			addPatientToExistingPatients(newPatient);
		}
		
		for (int i = 0; i < existingPatients.length; i++) {
			System.out.println("Existing patients are:" + existingPatients[i].getFirstName());
			for (int j = 0; j < existingPatients[i].getPatientFeatures().length; j++) {
				//System.out.println(existingPatients[i].getFirstName() + " feature values are:" + existingPatients[i].getPatientFeatures()[j].getFeatureValue());
			}
		}
		
		dataFile.close();
	}

	public Patient createNewPatient(int patientID, 
									String patientFirstName, 
									String patientLastame, 
									Feature[] patientFeatures) {
		return new Patient(patientID, 
							patientFirstName, 
							patientLastame, 
							patientFeatures);
	}
	
	public Patient[] getExistingPatients() {
		return existingPatients;
	}
	
	public void addPatientToExistingPatients(Patient newPatient) { 
		if(isExistingPatientsFull(existingPatients)) {
				existingPatients = addNewPatient(existingPatients);
		}
		
		if(!isPatientAdded(newPatient)) {
			boolean patientAdded = false;
			for(int i = 0; i < existingPatients.length; i++) {
				if(existingPatients[i] == null && !patientAdded) {
					existingPatients[i] = newPatient;
					patientAdded = true;
				} 
			}
			if(!patientAdded) {
				System.out.println("Could not add new patient:" + newPatient.getFirstName() + " " + newPatient.getLastName()); 
			}else {
				System.out.println("Patient already added in system: " + newPatient.getFirstName()  + " " + newPatient.getLastName()); 
			}
		}
	}
	
	public Patient[] addNewPatient(Patient[] patients) { 
		int newSize = patients.length + 100;
		newPatients = new Patient[newSize];
		
		
		for(int i = 0; i < patients.length; i++) {
				newPatients[i] = patients[i];	
		}
		return newPatients;
	}
	
	public boolean isPatientAdded(Patient patient) { //Fix - Use ID?
		
		for(int i = 0; i < existingPatients.length; i++) {
			if(existingPatients[i] != null &&
				existingPatients[i].getFirstName().equals(patient.getFirstName()) && 
				existingPatients[i].getLastName() == patient.getLastName()) {
				return true;
			}
		}
		return false;		
	}
	
	public boolean isExistingPatientsFull(Patient[] patients) { 
		for(int i = 0; i < patients.length; i++) {
			if(patients[i] == null) {
				return false;
			}
		}
		return true;
	}

	public static int getExistingPatientCountFromFile(BufferedReader dataFile) throws IOException {
		patientCount = 0;
		String line;
		if((line = dataFile.readLine()) == null){
			throw new IOException("File is empty. Cannot use: " + line);
		} else {
			while ((line = dataFile.readLine()) != null) {
				patientCount++;
			}
		}
		return patientCount;
	}
	
	public void createPredictionForPatient(String patientFirstName, 
											String patientLastName, 
											String pregnancies, 
											String glucose, 
											String bloodPressure,
											String skinThickness,
											String insulin,
											String bmi,
											String diabetesPedigreeFunction,
											String age,
											String outcome) {
		
		if (patientFirstName == null && 
			patientLastName == null && 
			pregnancies == null && 
			glucose == null &&  
			bloodPressure == null &&
			skinThickness == null &&
            insulin == null &&
			bmi == null &&
			diabetesPedigreeFunction == null &&
		    age == null &&
			outcome == null) {
			
			viewbeticUI.setPredictionMessage("Please enter information");
		} else {
			Feature[] newPatientFeatures = store.createPatientFeatures();
			
			
			//Test - remove after finished
			/*
			newPatientFeatures[0].setFeatureValue(0);
			newPatientFeatures[1].setFeatureValue(137);
			newPatientFeatures[2].setFeatureValue(40);
			newPatientFeatures[3].setFeatureValue(35);
			newPatientFeatures[4].setFeatureValue(168);
			newPatientFeatures[5].setFeatureValue(43.1);
			newPatientFeatures[6].setFeatureValue(2.288);
			newPatientFeatures[7].setFeatureValue(33);
			newPatientFeatures[8].setFeatureValue(0);
			*/
			
			newPatientFeatures[0].setFeatureValue(Double.parseDouble(pregnancies));
			newPatientFeatures[1].setFeatureValue(Double.parseDouble(glucose));
			newPatientFeatures[2].setFeatureValue(Double.parseDouble(bloodPressure));
			newPatientFeatures[3].setFeatureValue(Double.parseDouble(skinThickness));
			newPatientFeatures[4].setFeatureValue(Double.parseDouble(insulin));
			newPatientFeatures[5].setFeatureValue(Double.parseDouble(bmi));
			newPatientFeatures[6].setFeatureValue(Double.parseDouble(diabetesPedigreeFunction));
			newPatientFeatures[7].setFeatureValue(Integer.parseInt(age));
			newPatientFeatures[8].setFeatureValue(0); //New patient does not have diagnosis
			
			Patient newPatient = new Patient(0, /*"TesterF"*/patientFirstName, /*"TesterL"*/patientLastName, newPatientFeatures);
			addPatientToExistingPatients(newPatient);
			Sample[] newPatientSample = {Sample.getSample(1)};
			newPatientSample[0].addToPatientSample(newPatient);
			
			TreeNode node = new TreeNode(newPatientSample);
			
			boolean prediction = forest.makeVote(node);
			
			if (prediction) {
				newPatientFeatures[8].setFeatureValue(1);
				viewbeticUI.setPredictionMessage("Condition: Patient is Diabetic");
			} else {
				newPatientFeatures[8].setFeatureValue(0);
				viewbeticUI.setPredictionMessage("Condition: Patient is not Diabetic");
			}
			
			lastPatient = newPatient;
			//System.out.println("Last Patient is: " + lastPatient.getFirstName() + " " + lastPatient.getLastName());
		}
	}

	public void createFeedbackForPatient() {
		if (lastPatient != null) { 
				String feedback = "Feedback for " + lastPatient.getFirstName() + " " + lastPatient.getLastName() + ":\n";
				
				for(int j = 0; j < lastPatient.getPatientFeatures().length; j++) {
					Feature patientFeature = lastPatient.getPatientFeatures()[j];
					
					if(!patientFeature.getFeatureName().equals("Pregnancies") &&
					   !patientFeature.getFeatureName().equals("Age") &&
					   !patientFeature.getFeatureName().equals("Outcome")) {
						Double averageValue = patientFeature.getFeatureAverage().doubleValue();
						Double featureValue = patientFeature.getFeatureValue().doubleValue();
						
						if(averageValue > featureValue) {
							if (patientFeature.getFeatureName().equals("Glucose")) { 
								feedback += "It is recommended that " + lastPatient.getFirstName() + " " + lastPatient.getLastName() + " focus on blood sugar control \n"; 
							} else if (patientFeature.getFeatureName().equals("BloodPressure")) { 
								feedback += "It is recommended that " + lastPatient.getFirstName() + " " + lastPatient.getLastName() + " reduce their salt consumption \n"; 
							} else if (patientFeature.getFeatureName().equals("SkinThickness")) { 
								feedback += "It is recommended that " + lastPatient.getFirstName() + " " + lastPatient.getLastName() + " start a good skin health routine\n"; 
							} else if (patientFeature.getFeatureName().equals("Insulin")) { 
								feedback += "It is recommended that " + lastPatient.getFirstName() + " " + lastPatient.getLastName() + " check with their doctor for insulin \n"; 
							} else if (patientFeature.getFeatureName().equals("BMI")) { 
								feedback += "It is recommended that " + lastPatient.getFirstName() + " " + lastPatient.getLastName() + " start a balanced diet and regular physical activity \n"; 
							} else if (patientFeature.getFeatureName().equals("DiabetesPedigreeFunction")) 
								feedback += "It is recommended that " + lastPatient.getFirstName() + " " + lastPatient.getLastName() + " get check-ups for early diabetes detection \n"; 
						} else { 
							feedback += "It is recommended that " + lastPatient.getFirstName() + " " + lastPatient.getLastName() + " continue to check their " + patientFeature.getFeatureName() + " with a doctor.\n"; 
						} 
					}
				}
			viewbeticUI.setPredictionMessage(feedback);
		} else {
				viewbeticUI.setPredictionMessage("Patient not found");
		}
	}
	
	public static void main(String[] args) throws IOException{
		
		Home home = new Home();
		viewbeticUI.setMessage("Reading file...");
		home.readFile(args[0]);
		
		if(patientCount == 0) {System.exit(0);}
		//Add file check
		
		viewbeticUI.setMessage("Patient count is: " + patientCount);
		
		viewbeticUI.setMessage("Cleaning samples...");
		Sample[] cleanSamples = new Sample[patientCount];
		for(int i = 0; i < patientCount; i++) {
			Sample patientSample = Sample.getSample(patientCount);
			
			for(int j = 0; j < patientCount; j++) {
				Patient patient = existingPatients[j];
				patientSample.addToPatientSample(patient);
			}
			cleanSamples[i] = cleaner.createCleanSample(patientSample);
		}
		
		viewbeticUI.setMessage("Adding averages...");
		for (int k = 0; k < cleanSamples.length; k++) {
			Patient patient = cleanSamples[k].getPatients()[k];
			for (int l = 0; l < patient.getPatientFeatures().length; l++) {
				Feature patientFeature = patient.getPatientFeatures()[l];
				Double average = cleaner.calculateFeatureAverage(cleanSamples[k], patientFeature);
				patientFeature.setFeatureAverage(average);
			}
		}
		
		//System.out.println("There are " +  cleanSamples.length + " clean samples");
		viewbeticUI.setMessage("Creating the Random Forest...");
		forest = new RandomForest(patientCount); 	
		forest.createForest(cleanSamples); 
		
		viewbeticUI.setMessage("Ready to make prediction"); 
	}
	
	public static final ViewbeticUI viewbeticUI = ViewbeticUI.createUI(new Home());
	static RandomForest forest;
	static FeatureStore store = new FeatureStore();
	public static SampleCleaner cleaner = new SampleCleaner();
	private static String[] featureNames;
	private static Patient[] existingPatients;
	Patient[] newPatients;
	Patient lastPatient;
	private static int patientCount;
	static int featureCount;
	Feature[] patientFeatures;
	public String patientFirstName;
	public String patientLastame;
}
