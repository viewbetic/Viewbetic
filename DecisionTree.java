package home;

import java.util.Arrays;

public class DecisionTree implements TreeOperator {
	
	//private static final ViewbeticUI viewbeticUI = ViewbeticUI.createUI(new Home());
	private TreeNode root;
	private PatientFeatureFinder featureFinder;
	//FeatureStore featureStore;
	private SampleCleaner cleaner;
	
	public DecisionTree() {
		featureFinder = new PatientFeatureFinder();
		cleaner = new SampleCleaner();
		//featureStore = new FeatureStore();
	}
	
	public DecisionTree(TreeNode node) {
		this.root = node;
	}
	
	@Override
	public TreeNode createTree(TreeNode node) {
		if(root == null) {
			root = node;
		}
		
		if(node.getSplitFeature() != null && node.getSplitFeature().isFeatureUsed()) {
			return node;
		}
		
		Double splitValue = null;
		Feature locatedFeature = null;
		Feature patientFeature = null;
		
		for(int i = 0; i < node.getSample().length; i++) {
			Sample sample = node.getSample()[i];
			//viewbeticUI.setMessage("Sample ID is: " + sample.getSampleId());
			if(sample.getPatients() != null && sample.getPatients().length > 0) {
				for(int j = 0; j < sample.getPatients().length; j++) {
				Feature[] features = sample.getPatients()[j].getPatientFeatures();
					for (int k = 0; k < features.length; k++) {
						if(!features[k].getFeatureName().equals("Outcome") && !features[k].isFeatureUsed()) { 
							patientFeature = features[k];
					
							Double currentSplitValue = findSplitValue(sample, patientFeature);
							TreeNode tempSplitNode = splitNode(node, currentSplitValue, patientFeature);
							TreeNode leftNode = tempSplitNode.getLeft();
							TreeNode rightNode = tempSplitNode.getRight();
							int totalOutcomes = 0;
							
							for(int m = 0; m < node.getSample().length; m++) {
								for(int n = 0; n < node.getSample()[m].getPatients().length; n++) {
									Feature outcomeFeature = featureFinder.findFeatureByName(node.getSample()[m].getPatients()[m], "Outcome");
									totalOutcomes += (Integer)outcomeFeature.getFeatureValue();
								}
							}
							
							double weightedGini = calculateWeightedGiniIndex(leftNode, rightNode, totalOutcomes);
							
							if(locatedFeature == null || weightedGini < calculateWeightedGiniIndex(splitNode(leftNode, splitValue, patientFeature).getLeft(), 
																		 							splitNode(rightNode, splitValue, patientFeature).getRight(), 
																		 							totalOutcomes)) {
									splitValue = currentSplitValue;
									locatedFeature = patientFeature;
							}
						}	
					}
				}
			}
		}
		
		
		if(locatedFeature != null) {
		node.setSplitValue(splitValue);
		node.setSplitFeature(locatedFeature);
		
		locatedFeature.setIsFeatureUsed(true);
		
		TreeNode splitNode = splitNode(node, splitValue, locatedFeature);
		
		node.setLeft(createTree(splitNode.getLeft()));
		node.setRight(createTree(splitNode.getRight()));
		
		} else {
			System.out.println("No more features available");
		}
		return node;
	}

	@Override
	public Double findSplitValue(Sample sample, Feature feature) {
		String featureName = feature.getFeatureName();
		Double[] featureValues = new Double[sample.getPatients().length]; 
		
		int index = 0;
		
		for(int i = 0; i < sample.getPatients().length; i++) {
			Feature featureLocated = featureFinder.findFeatureByName(sample.getPatients()[i], featureName);
			
			if(featureLocated != null) {
				featureValues[index++] = featureLocated.getFeatureValue().doubleValue();
			}
		}
		
		Arrays.sort(featureValues);
		
		int size = featureValues.length;
		Double medianValue;
		
		if(size % 2 == 1) { 
			medianValue = featureValues[size / 2];
		}
		else {
			medianValue = (featureValues[(size / 2) - 1] + featureValues[size / 2]) / 2.0;
		}
		return medianValue;
	}

	@Override
	public double calculateGiniIndex(TreeNode node) {
		double noOutcome = 0;
		double yesOutcome = 0;
		
		PatientFeatureFinder featureFinder = new PatientFeatureFinder();
		
		for(int i = 0; i < node.getSample().length; i++) {
			for(int j = 0; j < node.getSample()[i].getPatients().length; j++) {
				Feature feature = featureFinder.findFeatureByName(node.getSample()[i].getPatients()[j], "Outcome");
				if ((Integer)feature.getFeatureValue() == 0) {
					noOutcome++;
				}
				else if ((Integer)feature.getFeatureValue() == 1) {
					yesOutcome++;
				}
			}
		}
		
		double totalOutcome = yesOutcome + noOutcome;
		double noAverage = noOutcome / totalOutcome;
		double yesAverage = yesOutcome / totalOutcome;
		
		double purity = 1 - (noAverage * noAverage + yesAverage * yesAverage);
		
		return purity;
	}

	@Override
	public double calculateWeightedGiniIndex(TreeNode leftNode, TreeNode rightNode, int totalOutcome) {
		Double leftGini = calculateGiniIndex(leftNode);
		Double rightGini = calculateGiniIndex(rightNode);
		
		int leftSize = leftNode.getSample().length;
		int rightSize = rightNode.getSample().length;
		
		Double weightGini = ((leftSize + leftGini) + (rightSize + rightGini)) / totalOutcome;
		return weightGini;
	}

	@Override
	public TreeNode splitNode(TreeNode node, Double splitValue, Feature feature) {
		String featureName = feature.getFeatureName();
		int totalPatients = 0;
		
		for(int i = 0; i < node.getSample().length; i++) {
			totalPatients += node.getSample()[i].getPatients().length;
		}
		 
		Sample leftSample = new Sample(totalPatients);
		Sample rightSample = new Sample(totalPatients);
		
		for(int i = 0; i < node.getSample().length; i++) {
			for(int j = 0; j < node.getSample()[i].getPatients().length; j++) {
				Patient tempPatient = node.getSample()[i].getPatients()[j];
				
				Feature tempFeature = featureFinder.findFeatureByName(tempPatient, featureName);
				if(tempFeature != null) {
					
					Double featureValue = tempFeature.getFeatureValue().doubleValue();
					if(featureValue <= splitValue) {
						leftSample.addToPatientSample(tempPatient);
					} else if(featureValue > splitValue) {
						rightSample.addToPatientSample(tempPatient);
					}
				}
			}
		}

		Sample leftRemoved = cleaner.removeEmpty(leftSample);
		Sample cleanLeftSample = cleaner.createCleanSample(leftRemoved);

		Sample rightRemoved = cleaner.removeEmpty(rightSample);
		Sample cleanRightSample = cleaner.createCleanSample(rightRemoved);
		
		Sample[] newLeftSample = {cleanLeftSample};
		Sample[] newRightSample = {cleanRightSample};
		
		node.setLeft(new TreeNode(newLeftSample, splitValue));
		node.setRight(new TreeNode(newRightSample, splitValue));
		
		return node;
	}
	
	public boolean makePrediction(TreeNode node) {
		int yesOutcome = 0;
		int noOutcome = 0;
		boolean result = false;
		boolean treePrediction = false;
		
		if(node.getLeft() == null && node.getRight() == null) {
			for(int i = 0; i < node.getSample().length; i++) {
				for(int j = 0; j < node.getSample()[i].getPatients().length; j++) {
					Feature[] features = node.getSample()[i].getPatients()[j].getPatientFeatures();
					for (int k = 0; k < features.length; k++) {
						if(features[k].getFeatureName().equals("Outcome")){
							if((Integer)features[k].getFeatureValue() == 1) {
								yesOutcome++;
							} else {
								noOutcome++;
							}
						}
					}
				}
			}	
			result = yesOutcome > noOutcome;
			return result; 
		} else {
			for (int i = 0; i < node.getSample().length; i++) {
				for(int j = 0; j < node.getSample()[i].getPatients().length; j++) {
					Patient patient = node.getSample()[i].getPatients()[j];
					for (int k = 0; k < patient.getPatientFeatures().length; k++) {
						String featureName = patient.getPatientFeatures()[k].getFeatureName();
						String splitFeatureName = node.getSplitFeature().getFeatureName();
							if(featureName.equals(splitFeatureName) && !node.getSplitFeature().isFeatureUsed()) {
								Feature patientFeature = patient.getPatientFeatures()[k];
								if((Double)patient.getPatientFeatures()[k].getFeatureValue() <= node.getSplitValue()) {
									treePrediction = makePrediction(node.getLeft());
								} else {
									treePrediction = makePrediction(node.getRight());
							}
							patientFeature.setIsFeatureUsed(true); 	
						}
					}
				}
			}
			return treePrediction;
		}
	}
	
	@Override
	public TreeNode createNode(Sample[] sample) {
		return new TreeNode(sample);
	}
}