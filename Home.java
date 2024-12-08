package home;

import java.io.IOException;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;

public class Home {
	
	public Patient[] readFile(BufferedReader dataFile, int patientCount) throws IOException {
		existingPatients = new Patient[patientCount]; //Fix
		
		int patientIndex = 0;
		
		String featureLine = dataFile.readLine(); //Features
		
		featureNames = featureLine.split("\t");

		int featureCount = featureNames.length-1;
		
		//String id = featureNames[0]; //ID name
		
		String patientLine;
		
		while ((patientLine = dataFile.readLine()) != null) { //Existing patients
			String[] data = patientLine.split("\t");
			Integer patientID = Integer.parseInt(data[0]);
			
			patientFeatures = new Feature[featureCount];
			
			for (int i = 0; i < featureCount; i++) {
				patientFeatures[i] = store.createFeatureFromName(featureNames[i+1]);
				double value = Double.parseDouble(data[i+1]);
				patientFeatures[i].setFeatureValue(value);
			}
			
			Patient p = new Patient(patientID, 
									patientFirstName, 
									patientLastame, 
									patientFeatures);

			existingPatients[patientIndex++] = p;
		}
		
		return existingPatients;
	}

	private int getExistingPatientCount(BufferedReader dataFile) throws IOException {
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
											String patientLastame, 
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
			patientLastame == null && 
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
			newPatientFeatures[0].setFeatureValue(Double.parseDouble(pregnancies));
			newPatientFeatures[1].setFeatureValue(Double.parseDouble(glucose));
			newPatientFeatures[2].setFeatureValue(Double.parseDouble(bloodPressure));
			newPatientFeatures[3].setFeatureValue(Double.parseDouble(skinThickness));
			newPatientFeatures[4].setFeatureValue(Double.parseDouble(insulin));
			newPatientFeatures[5].setFeatureValue(Double.parseDouble(bmi));
			newPatientFeatures[6].setFeatureValue(Double.parseDouble(diabetesPedigreeFunction));
			newPatientFeatures[7].setFeatureValue(Integer.parseInt(age));
			newPatientFeatures[8].setFeatureValue(0); //New patient does not have diagnosis
			
			//Fix - Patient should be added to existing patients
			//After prediction and isFeatureUsed is updated
			Patient newPatient = new Patient(0, patientFirstName, patientLastame, newPatientFeatures);
			Sample[] newPatientSample = new Sample[1];
			newPatientSample[0] = new Sample(1);
			newPatientSample[0].addToPatientSample(newPatient);
			
			TreeNode node = new TreeNode(newPatientSample);
			
			boolean prediction = forest.makeVote(node);
			
			if (prediction == false) {
				viewbeticUI.setPredictionMessage("Condition: Patient is not Diabetic");
			} else {
				viewbeticUI.setPredictionMessage("Condition: Patient is Diabetic");
			} 
		}
	}

	public void createFeedbackForPatient() {
		//Compare entered data to an average patient
	}
	
	public static void main(String[] args) throws IOException{
		
		BufferedReader dataFile = new BufferedReader(new FileReader(args[0]));
		BufferedWriter logFile = new BufferedWriter(new FileWriter(args[1]));
		
		Home home = new Home();
		
		//Get the number of patients in the file
		//If the file is empty, exit the program
		patientCount = home.getExistingPatientCount(dataFile);
		if(patientCount == 0) {logFile.close(); System.exit(0); }
		dataFile.close();
		
		dataFile = new BufferedReader(new FileReader(args[0]));
		
		//Read all existing patients from file
		patients = home.readFile(dataFile, patientCount);

		viewbeticUI.setMessage("Creating the Random Forest...");
		forest = new RandomForest(patientCount, patientCount); 
		forest.createForest(patients); 
		
		viewbeticUI.setMessage("Ready to make prediction"); 
		
		logFile.close();
	}
	
	private static final ViewbeticUI viewbeticUI = ViewbeticUI.createUI(new Home());
	static RandomForest forest;
	static FeatureStore store = new FeatureStore();
	//public static Feature[] features;
	public static String[] featureNames;
	static Patient[] existingPatients; 
	static Patient[] newPatients;
	static Patient[] patients;
	public static int patientCount;
	public static int featureCount;
	Feature[] patientFeatures;
	public String patientFirstName;
	public String patientLastame;
}
