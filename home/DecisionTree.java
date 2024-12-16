package home;

import java.util.Arrays;
import java.util.concurrent.TimeUnit;

public class DecisionTree implements TreeOperator {
	
	private static final ViewbeticUI viewbeticUI = ViewbeticUI.createUI(new Home());
	private TreeNode root;
	private PatientFeatureFinder featureFinder;
	private SampleCleaner cleaner;
	
	public DecisionTree() {
		featureFinder = new PatientFeatureFinder();
		cleaner = new SampleCleaner();
	}
	
	public DecisionTree(TreeNode node) {
		this.root = node;
	}
	
	TreeNode getRoot() {
		return root;
	}
	
	@Override
	public TreeNode createTree(TreeNode node) {
		//System.out.println("Calling create tree");
		if(root == null) {
			root = node;
		}
		
		if(node.getSplitFeature() != null && node.getSplitFeature().isFeatureUsed()) {
			return node;
		}
		
		Double splitValue = null;
		Feature locatedFeature = null;
		Feature patientFeature = null;
		double bestGini = 1.0;
		
		for(int i = 0; i < node.getSample().length; i++) {
			Sample sample = node.getSample()[i];			
			viewbeticUI.setMessage("Sample ID is: " + sample.getSampleId());
			if(sample != null && sample.getPatients() != null && sample.getPatients().length > 0) {
				for(int j = 0; j < sample.getPatients().length; j++) {
					Patient patient = sample.getPatients()[j];
					Feature[] features = patient.getPatientFeatures();					
					for (int k = 0; k < features.length; k++) {
						patientFeature = features[k];
						if(patientFeature != null && !patientFeature.getFeatureName().equals("Outcome")) { 
							
							boolean skipFeatureForZero = cleaner.isFeatureAllZero(node.getSample()[i], patientFeature);
							boolean skipFeatureForSame = cleaner.isFeatureSameValue(node.getSample()[i], patientFeature);
							
							if(!skipFeatureForSame && !skipFeatureForZero){
								Double currentSplitValue = findSplitValue(sample, patientFeature);
								
								if (currentSplitValue != 0) {
									viewbeticUI.setPredictionMessage("Patient feature used?: " + patientFeature.isFeatureUsed());
									
									TreeNode tempSplitNode = splitNode(node, currentSplitValue, patientFeature);
									TreeNode leftNode = tempSplitNode.getLeft();
									TreeNode rightNode = tempSplitNode.getRight();
									int totalOutcomes = 0;
									
									
									for(int m = 0; m < node.getSample().length; m++) {
										for(int n = 0; n < node.getSample()[m].getPatients().length; n++) {
											Feature outcomeFeature = featureFinder.findFeatureByName(node.getSample()[m].getPatients()[n], "Outcome");
											if(outcomeFeature != null && (Integer)outcomeFeature.getFeatureValue() == 1)
											totalOutcomes++;
										}
									}
									
									double weightedGini = calculateWeightedGiniIndex(leftNode, rightNode, totalOutcomes);
									System.out.println("Current Weighted Gini: " + weightedGini);
									
									if(locatedFeature == null || weightedGini < bestGini) {
											splitValue = currentSplitValue;
											locatedFeature = patientFeature;
											bestGini = weightedGini;
											System.out.println("Updated located feature: " + locatedFeature.getFeatureName() + " Split value: " + splitValue);
									}
							} else {
								viewbeticUI.setPredictionMessage("Skipping feature - splitValue 0: " + patientFeature.getFeatureName());
							}
								
							} else {
								viewbeticUI.setPredictionMessage("Skipping feature: " + patientFeature.getFeatureName());
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
			viewbeticUI.setPredictionMessage("Feature marked as used: " + locatedFeature.getFeatureName()); 
			viewbeticUI.setPredictionMessage("Split feature used: " + locatedFeature.getFeatureName() + " with split value: " + splitValue);
			
			TreeNode splitNode = splitNode(node, splitValue, locatedFeature);
			
			viewbeticUI.setPredictionMessage("Creating actual split nodes and splitting on feature: " + locatedFeature.getFeatureName());
			
			viewbeticUI.setPredictionMessage("Left Node samples after split: " + splitNode.getLeft().getSample().length); 
			viewbeticUI.setPredictionMessage("Right Node samples after split: " + splitNode.getRight().getSample().length);
			
			node.setLeft(createTree(splitNode.getLeft()));
			node.setRight(createTree(splitNode.getRight()));
		
		} else {
			viewbeticUI.setPredictionMessage("No more features available");
		}
		return node;
	}

	@Override
	public Double findSplitValue(Sample sample, Feature feature) {
		//System.out.println("Calling findSplitValue");
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
			//System.out.println("Median value: " + medianValue);
		}
		else {
			medianValue = (featureValues[(size / 2) - 1] + featureValues[size / 2]) / 2.0;
			//System.out.println("Median value: " + medianValue);
		}
		return medianValue;
	}

	@Override
	public double calculateGiniIndex(TreeNode node) {
		double noOutcome = 0;
		double yesOutcome = 0;
		
		for(int i = 0; i < node.getSample().length; i++) {
			for(int j = 0; j < node.getSample()[i].getPatients().length; j++) {
				Feature feature = featureFinder.findFeatureByName(node.getSample()[i].getPatients()[j], "Outcome");
				if(feature != null) {
					if ((Integer)feature.getFeatureValue() == 0) {
						noOutcome++;
					}
					else if ((Integer)feature.getFeatureValue() == 1) {
						yesOutcome++;
					}
				} 
			}
		}
		
		double totalOutcome = yesOutcome + noOutcome;
		viewbeticUI.setPredictionMessage("Total outcomes: " + totalOutcome);
		viewbeticUI.setPredictionMessage("Yes outcomes: " + yesOutcome);
		viewbeticUI.setPredictionMessage("No outcomes: " + noOutcome);
		
		if( totalOutcome == 0) {
			return 0;
		}
		
		double noAverage = noOutcome / totalOutcome;
		double yesAverage = yesOutcome / totalOutcome;
		
		double purity = 1 - (noAverage * noAverage + yesAverage * yesAverage);
		
		return purity;
	}

	@Override
	public double calculateWeightedGiniIndex(TreeNode leftNode, TreeNode rightNode, int totalOutcome) {
		//System.out.println("calculateWeightedGiniIndex - Calling Left Gini: ");
		Double leftGini = calculateGiniIndex(leftNode);
		//System.out.println("calculateWeightedGiniIndex - Calling Right Gini: ");
		Double rightGini = calculateGiniIndex(rightNode);
		
		int leftSize = leftNode.getSample().length;
		int rightSize = rightNode.getSample().length;
		
		double totalSamples = leftSize + rightSize;
		
		//System.out.println("calculateWeightedGiniIndex - Left Gini: " + leftGini); 
		//System.out.println("calculateWeightedGiniIndex - Right Gini: " + rightGini); 
		//System.out.println("calculateWeightedGiniIndex - Left Size: " + leftSize); 
		//System.out.println("calculateWeightedGiniIndex - Right Size: " + rightSize); 
		//System.out.println("calculateWeightedGiniIndex - Total Samples: " + totalSamples);
		
		Double weightGini = ((leftSize / totalSamples)) * leftGini  + ((rightSize / totalSamples)) * rightGini;
		return weightGini;
	}

	@Override
	public TreeNode splitNode(TreeNode node, Double splitValue, Feature feature) {
		//System.out.println("splitNode - Calling splitNode: ");
		String featureName = feature.getFeatureName();
		int totalPatients = 0;
		
		for(int i = 0; i < node.getSample().length; i++) {
			totalPatients += node.getSample()[i].getPatients().length;
			//System.out.println("Total patients after splitNode called: " + totalPatients);
		}
		
		Sample leftSample = Sample.getSample(totalPatients);
		Sample rightSample = Sample.getSample(totalPatients);
		
		System.out.println("Left sample Patients:" + leftSample.getPatients().length);
		System.out.println("Right sample Patients:" + rightSample.getPatients().length);
		
		System.out.println("Original Patients Array before splitting:"); 
		for(int i = 0; i < node.getSample().length; i++) {
			for(int j = 0; j < node.getSample()[i].getPatients().length; j++) {
				Patient tempPatient = node.getSample()[i].getPatients()[j];
				if(tempPatient != null) {
					Feature tempFeature = featureFinder.findFeatureByName(tempPatient, featureName);
						if (tempFeature != null) {
							Double featureValue = tempFeature.getFeatureValue().doubleValue();
							if(featureValue <= splitValue) {
								leftSample.addToPatientSample(tempPatient);
								//System.out.println("Adding to left sample: " + tempPatient.getPatientID() + " and Feature Value: " + featureValue);
							} else {
								rightSample.addToPatientSample(tempPatient);
								//System.out.println("Adding to right sample: " + tempPatient.getPatientID()+ " and Feature Value: " + featureValue);
							}
					} 
				}
			}
		}
		
		//System.out.println("Feature: " + featureName + " and Split Value: " + splitValue); 
		//System.out.println("Temp left Sample size: " + leftSample.getPatients().length); 
		//System.out.println("Temp right Sample size: " + rightSample.getPatients().length);

		Sample cleanLeftSample = cleaner.createCleanSample(cleaner.removeEmpty(leftSample));
		Sample cleanRightSample = cleaner.createCleanSample(cleaner.removeEmpty(rightSample));
		
		//System.out.println("Clean Sample size - Left: " + cleanLeftSample.getPatients().length); 
		//System.out.println("Clean Sample size - Right: " + cleanRightSample.getPatients().length);
		
		Sample[] newLeftSample = {cleanLeftSample};
		Sample[] newRightSample = {cleanRightSample};
		
		node.setLeft(new TreeNode(newLeftSample, splitValue));
		node.setRight(new TreeNode(newRightSample, splitValue));
		
		//System.out.println("Final left node Sample size: " + node.getLeft().getSample().length); 
		//System.out.println("final right node Sample size: " + node.getRight().getSample().length);
		
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
			if (result) { 
				viewbeticUI.setPredictionMessage("Result at leaf node: Yes"); 
			} else { 
				viewbeticUI.setPredictionMessage("Result at leaf node: No"); 
			}
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
								double featureValue =(Double)patient.getPatientFeatures()[k].getFeatureValue();
								if(featureValue <= node.getSplitValue()) {
									//System.out.println("Going left at feature " + splitFeatureName);
									treePrediction = makePrediction(node.getLeft());
								} else {
									//System.out.println("Going right at feature " + splitFeatureName);
									treePrediction = makePrediction(node.getRight());
							}
							patientFeature.setIsFeatureUsed(true); 	
						}
					}
				}
			}
			if (result) { 
				viewbeticUI.setPredictionMessage("Result at leaf Node: Yes"); 
			} else { 
				viewbeticUI.setPredictionMessage("Result at Leaf Node: No"); 
			}
			return treePrediction;
		}
	}
	
	@Override
	public TreeNode createNode(Sample[] sample) {
		return new TreeNode(sample);
	}
}