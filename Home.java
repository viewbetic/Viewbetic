package home;

import java.io.IOException;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;

public class Home {
	
	public Patient[] readFile(BufferedReader dataFile, int patientCount) throws IOException {
		
		existingPatients = new Patient[patientCount];
		int patientIndex = 0;
		
		String featureLine = dataFile.readLine(); //features
		features = featureLine.split("\t");
		
		setPatientIDFeatureName(features[0]);
		setPregnanciesFeatureName(features[1]);
		setGlucoseFeatureName(features[2]);
		setBloodPressureFeatureName(features[3]);
		setSkinThicknessFeatureName(features[4]);
		setInsulinFeatureName(features[5]);
		setBMIFeatureName(features[6]);
		setDiabetesPedigreeFunctionFeatureName(features[7]);
		setAgeFeatureName(features[8]);
		setOutcomeFeatureName(features[9]);
		
		String patientLine;

		while ((patientLine = dataFile.readLine()) != null) { //patients
			String[] data = patientLine.split("\t");
			Integer patientID = Integer.parseInt(data[0]);
			Double pregnancies = Double.parseDouble(data[1]);
			Double glucose = Double.parseDouble(data[2]);
			Double bloodPressure = Double.parseDouble(data[3]);
			Double skinThickness = Double.parseDouble(data[4]);
			Double insulin = Double.parseDouble(data[5]);
			Double bmi = Double.parseDouble(data[6]);
			Double diabetesPedigreeFunction = Double.parseDouble(data[7]);
			Integer age = Integer.parseInt(data[8]);
			Double outcome = Double.parseDouble(data[9]);
			Patient p = new Patient(patientID, 
									patientFirstName, 
									patientLastame, 
									pregnancies,
									glucose, 
									bloodPressure, 
									skinThickness, 
									insulin, 
									bmi,
									diabetesPedigreeFunction, 
									age, 
									outcome);

			existingPatients[patientIndex++] = p;
		}
		
		return existingPatients;
	}

	private int isFileEmpty(BufferedReader dataFile) throws IOException {
		String line;
		if((line = dataFile.readLine()) == null){
			throw new IOException("File is empty");
		} else {
			while ((line = dataFile.readLine()) != null) {
				patientCount++;
			}
		}
		return patientCount;
	}
	
	public static void main(String[] args) throws IOException{
		
		BufferedReader dataFile = new BufferedReader(new FileReader(args[0]));
		BufferedWriter logFile = new BufferedWriter(new FileWriter(args[1]));
		
		Home h = new Home();
		
		//Get the number of patients in the file
		//If the file is empty, exit the program
		patientCount = h.isFileEmpty(dataFile);
		if(patientCount == 0) {logFile.close(); System.exit(0); }
		dataFile.close();
		
		dataFile = new BufferedReader(new FileReader(args[0]));
		
		//Read all existing patients from file
		patientList = h.readFile(dataFile, patientCount);
		
		//Create samples from existing patients
		Sample s;
		
		Clean c = new Clean();
		
		TreeNode t;
		
		DecisionTree tree = new DecisionTree();
		
		int testSize = 10;
		int sampleNumber = 0;
		Patient[][] cleanRandomSample = new Patient[testSize][];	
		
		for(int i = 0; i < testSize; i++) { //test 10 samples
			sampleNumber++;
			s = new Sample(); 
			
			for(int j = 0; j < patientCount; j++) {
				Patient sampledPatient = s.chooseRandomPatient(patientList);
				s.addToSample(sampledPatient);
				//logFile.write("Sample patient add complete. ID is: " + sampledPatient.getId() + "\n");
				
			}
			
			Patient[] randomSample = s.getSample();
			
			//for(int k = 0; k < randomSample.length; k++) {
				//logFile.write("Random sample ID is: " + randomSample[k].getId() + "\n");
			//}
			
			c.cleanSample(randomSample);

		//}

			//for(int m = 0; m < testSize; m++) { //test length with only 10 patients 
				cleanRandomSample[i] = randomSample;
				logFile.write("New clean sample. Sample number is:" + sampleNumber + "\n");
				
			//}
			//logFile.write("Second clean sample patient is: "+ cleanRandomSample[1][j].getId()+ "\n");
		}
		
		for(int n = 0; n < testSize; n++) { //test length with only 10 patients 
			//logFile.write("First clean sample patient is: "+ cleanRandomSample[0][n].getId()+ "\n");
		}
		for(int p = 0; p < testSize; p++) { //test length with only 10 patients 
			//logFile.write("Second clean sample patient is: "+ cleanRandomSample[1][p].getId()+ "\n");
		}
		
		t = new TreeNode(cleanRandomSample);
		//tree.getFeature(t);
		tree.findSplitValue(t);
		
		/**
		for(int i = 0; i < tree.getAgeFeatures().length; i++) {
			logFile.write("Ages: " + tree.getAgeFeatures()[i].doubleValue() + "\n");
			logFile.write("New Feature" + "\n");
		}
		**/
		
		//tree.createTree(t);
		
		//tree.addCleanSample(cleanRandomSample);
		
		logFile.close();
	}
	
	public static int getPatientCount() {
		return patientCount;
	}
	
	private String getPatientIDFeatureName() {
		return patientIDFeatureName;	
	}
	
	private void setPatientIDFeatureName(String patientIDFeatureName) {
		this.patientIDFeatureName = patientIDFeatureName;
	}
	
	private String setPregnanciesFeatureName() {
		return pregnanciesFeatureName;
	}
	
	private void setPregnanciesFeatureName(String pregnanciesFeatureName) {
		this.pregnanciesFeatureName = pregnanciesFeatureName;
	}
	
	private String getBloodPressureFeatureName() {
		return bloodPressureFeatureName;
	}
	
	private void setBloodPressureFeatureName(String bloodPressureFeatureName) {
		this.bloodPressureFeatureName = bloodPressureFeatureName;
	}
	
	private String getGlucoseFeatureName() {
		return glucoseFeatureName;
	}
	
	private void setGlucoseFeatureName(String glucoseFeatureName) {
		this.glucoseFeatureName = glucoseFeatureName;
	}
	
	private String getSkinThicknessFeatureName() {
		return skinThicknessFeatureName;
	}
	
	private void setSkinThicknessFeatureName(String skinThicknessFeatureName) {
		this.skinThicknessFeatureName = skinThicknessFeatureName;
	}
	
	private String getInsulinFeatureName() {
		return insulinFeatureName;
	}
	
	private void setInsulinFeatureName(String insulinFeatureName) {
		this.insulinFeatureName = insulinFeatureName;
	}
	
	private String getBMIFeatureName() {
		return bmiFeatureName;
	}
	
	private void setBMIFeatureName(String bmiFeatureName) {
		this.bmiFeatureName = bmiFeatureName;
	}
	
	private String getDiabetesPedigreeFunctionFeatureName() {
		return diabetesPedigreeFunctionFeatureName;
	}
	
	private void setDiabetesPedigreeFunctionFeatureName(String diabetesPedigreeFunctionFeatureName) {
		this.diabetesPedigreeFunctionFeatureName = diabetesPedigreeFunctionFeatureName;
	}
	private String getOutcomeFeatureName() {
		return outcomeFeatureName;
	}
	private void setOutcomeFeatureName(String outcomeFeatureName) {
		this.outcomeFeatureName = outcomeFeatureName;
	}
	private void setAgeFeatureName(String ageFeatureName) {
		this.ageFeatureName = ageFeatureName;
	}
	private String getAgeFeatureName() {
		return ageFeatureName;
	}
	
	public static String[] featuresList;
	public static String[] features;
	static Patient[] existingPatients; 
	static Patient[] patientList;
	public static int patientCount;
	public static String patientFirstName;
	public static String patientLastame;
	public String patientIDFeatureName;
	public String pregnanciesFeatureName;
	public String glucoseFeatureName;
	public String bloodPressureFeatureName;
	public String skinThicknessFeatureName;
	public String insulinFeatureName;
	public String bmiFeatureName;
	public String diabetesPedigreeFunctionFeatureName;
	public String ageFeatureName;
	public String outcomeFeatureName;
	
}
