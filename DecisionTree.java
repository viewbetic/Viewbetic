package home;

import java.util.Arrays;

public class DecisionTree {
	
	private TreeNode root;
	Double[] pregnanciesFeatures;
	Double[] glucoseFeatures;
	Double[] bloodPressureFeatures;
	Double[] skinThicknessFeatures;
	Double[] insulinFeatures;
	Double[] bmiFeatures; 
	Double[] diabetesPedigreeFunctionFeatures;
	Integer[] ageFeatures;
	Double[] outcomeFeatures;
	
	public DecisionTree() {
		pregnanciesFeatures = new Double[Home.getPatientCount()];
		glucoseFeatures = new Double[Home.getPatientCount()];
		bloodPressureFeatures = new Double[Home.getPatientCount()];
		skinThicknessFeatures = new Double[Home.getPatientCount()];
		insulinFeatures = new Double[Home.getPatientCount()];
		bmiFeatures = new Double[Home.getPatientCount()];
		diabetesPedigreeFunctionFeatures  = new Double[Home.getPatientCount()];
		ageFeatures = new Integer[Home.getPatientCount()];
		outcomeFeatures = new Double[Home.getPatientCount()];
	}
	
	public DecisionTree(TreeNode root) {
		this.root = root;
	}

	public TreeNode createTree(TreeNode root) { // creating the tree
		
		if(root == null) {
			return null;
		}
		
		TreeNode r = new TreeNode(root);
		
		Double splitValue = findSplitValue(root);
		
		root.setSplitValue(splitValue);
		
		root.left = splitNode(root, splitValue);
		root.right = splitNode(root, splitValue);
		
		return root;
	}

	public Double findSplitValue(TreeNode node) {
	
		Patient[][] sample = node.getSample();
		
		int patientCount = 0;

		for(int i = 0; i < sample.length; i++) {
			patientCount += sample[i].length;
			System.out.println(patientCount);
		}
		
		Patient[] patients = new Patient[patientCount];
		
		int patientIndex = 0;
		for(int i = 0; i < sample.length; i++) {
			for(int j = 0; j < sample[i].length; j++) {
				patients[patientIndex++] = sample[i][j];
			}
		}
		
		sortPatientsByBMI(patients); //Test
		
        //This is not finished
      
		return null;
}

	public TreeNode splitNode(TreeNode node, Double value) {
		
		return node;
	}
	
	private Double[] sortPatientsByBMI(Patient[] patients) { //Test
		
		Double[] bmiValues = new Double[patients.length];
		
		for(int i = 0; i < patients.length; i++) {
			bmiValues[i] = patients[i].getBMI();
		}
		Arrays.sort(bmiValues);
		
		return bmiValues;
	}

	private void sortPatients(Patient[] patients) { //Not working
		
		Arrays.sort(patients);
	}
	
	public void getFeature(TreeNode root) {
		Patient[] patients;
		
		for(int i = 0; i < root.getSample().length; i++) {
			patients = root.getSample()[i];
			for(int j = 0; j < patients.length; j++) { //getting the data from patients
				pregnanciesFeatures[j] = patients[j].getPregnancies();
				glucoseFeatures[j] = patients[j].getGlucose();
				bloodPressureFeatures[j] = patients[j].getBloodPressure();
				skinThicknessFeatures[j] = patients[j].getskinThickness();
				insulinFeatures[j] = patients[j].getInsulin();
				bmiFeatures[j] = patients[j].getBMI();
				diabetesPedigreeFunctionFeatures[j] = patients[j].getDiabetesPedigreeFunction();
				ageFeatures[j] = patients[j].getAge();
				outcomeFeatures[j] = patients[j].getOutcome();
			}
		}
	}
	
	public double calculateGiniIndex(TreeNode node) {
		double noOutcome = 0;
		double yesOutcome = 0;
		
		for(int i = 0; i < node.getSample().length; i++) {
			for( int j = 0; j < node.getSample()[i].length; j++) {
				if (node.getSample()[i][j].getOutcome() == 0) {
					noOutcome++;
				}
				else if (node.getSample()[i][j].getOutcome() == 1) {
					yesOutcome++;
				}
			}
		}
		double totalOutcome = yesOutcome + noOutcome;
		double noAverage = noOutcome / totalOutcome;
		double yesAverage = yesOutcome / totalOutcome;
		
		double impurity = 1 - (noAverage * noAverage + yesAverage * yesAverage);
		
		return impurity;
	}

	public TreeNode createCleanNode(Patient[][] cleanRandomSample) {
		root = new TreeNode(cleanRandomSample);
		return root;
	}
	
	public TreeNode getRoot() {
		return root;
	}
	
	public Double[] getPregnanciesFeatures() {
		return pregnanciesFeatures;
	}
	
	public void setPregnanciesFeatures(Double[] patientPregnanciesFeatures) {
		this.pregnanciesFeatures = patientPregnanciesFeatures;
	}
	
	
	public Double[] getBloodPressureFeatures() {
		return bloodPressureFeatures;
	}
	
	public void setBloodPressureFeatures(Double[] patientBloodPressureFeature) {
		this.bloodPressureFeatures = patientBloodPressureFeature;
	}
	
	
	public Double[] getskinThicknessFeatures() {
		return skinThicknessFeatures;
	}
	
	public void setskinThicknessFeatures(Double[] patientskinThickness) {
		this.skinThicknessFeatures = patientskinThickness;
	}
	
	
	public Double[] getInsulinFeatures() {
		return insulinFeatures;
	}
	
	public void setInsulinFeatures(Double[] patientInsulinFeature) {
		this.insulinFeatures = patientInsulinFeature;
	}
	
	
	public Double[] getBMIFeatures() {
		return bmiFeatures;
	}
	
	public void setBMIFeatures(Double[] patientBMIFeature) {
		this.bmiFeatures = patientBMIFeature;
	}
	
	
	public Double[] getDiabetesPedigreeFunctionFeatures() {
		return diabetesPedigreeFunctionFeatures;
	}
	
	public void setDiabetesPedigreeFunctionFeatures(Double[] patientDiabetesPedigreeFunctionFeature) {
		this.diabetesPedigreeFunctionFeatures = patientDiabetesPedigreeFunctionFeature;
	}
	
	
	public Integer[] getAgeFeatures() {
		return ageFeatures;
	}
	
	public void setAgeFeatures(Integer[] patientAgeFeatures) {
		this.ageFeatures = patientAgeFeatures;
	}
	
	public Double[] getGlucoseFeatures() {
		return glucoseFeatures;
	}
	
	public void setGlucoseFeatures(Double[] patientGlucoseFeatures) {
		this.glucoseFeatures = patientGlucoseFeatures;
	}
	
	
	public Double[] getOutcomeFeatures() {
		return outcomeFeatures;
	}
	
	public void setOutcomeFeatures(Double[] patientOutcomes) {
		this.outcomeFeatures = patientOutcomes;
	}
}
