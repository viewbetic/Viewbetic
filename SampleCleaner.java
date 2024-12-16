package home;

public class SampleCleaner implements Cleaner{
	FeatureStore store;
	Sample cleanSample;
	
	public SampleCleaner() {
		store = new FeatureStore();
	}
	
	public SampleCleaner(Sample sample) {
		this.cleanSample = sample;	
	}
	
	@Override
	public Sample createCleanSample(Sample sample) {
		if(isSampleClean(sample)) {
			System.out.println("Sample is already clean");
			cleanSample = sample;
		} else {
			if(isPatientFeatureEmpty(sample)) {
				System.out.println("Patient feature is empty. Adding patient feature");
				cleanSample = addPatientFeature(sample);
			}
			if(isFeatureEmpty(sample)) {
				System.out.println("Feature is empty, adding feature");
				cleanSample = addFeature(sample);
			}
			if(isFeatureValueEmpty(sample)) {
				Feature feature = getFeatureFromSample(sample);
				System.out.println("Feature value is empty. Aadding feature value: " + feature.getFeatureName());
				cleanSample = addFeatureValue(sample, feature);
			}
		}
		//System.out.println("Cleaned Sample size: " + cleanSample.getPatients().length);
		return cleanSample;
	}

	public Feature getFeatureFromSample(Sample sample) {
		Patient[] patients = sample.getPatients();
		Feature feature = null;
		    for (int i = 0; i < patients.length; i++) {
		        if (patients[i] != null && patients[i].getPatientFeatures() != null) {
		            for (int j = 0; j < patients[i].getPatientFeatures().length; j++) {
		                feature = patients[i].getPatientFeatures()[j];
		            }
		        }
		    }
		return feature;
	}

	@Override
	public boolean isSampleClean(Sample sample) {
		if(sample == null) {
			return false;
		}
		
		if(!isPatientFeatureEmpty(sample) &&
		   !isFeatureEmpty(sample)  &&
		   !isFeatureValueEmpty(sample)) {
			return true;
		}
		return false;
	}

	@Override
	public boolean isPatientFeatureEmpty(Sample sample) {
		Patient[] patients = sample.getPatients();
		for(int i = 0; i < patients.length; i++) {
			if(patients[i] == null || patients[i].getPatientFeatures() == null) {
				//System.out.println("Empty patient feature for patient ID: " + patients[i].getPatientID());
				return true;
			}
		}
		return false;
	}
	
	@Override
	public boolean isFeatureEmpty(Sample sample) {
		Patient[] patients = sample.getPatients();
		for(int i = 0; i < patients.length; i++) {
			for(int j = 0; j < patients[i].getPatientFeatures().length; j++) {
				if( patients[i].getPatientFeatures()[j] == null) {
					//System.out.println("Empty feature for patient ID: " + patients[i].getPatientID() + " at feature index: " + j);
					return true;
				}
			}
		}
		return false;
	}
	
	@Override
	public boolean isFeatureValueEmpty(Sample sample) {
		Patient[] patients = sample.getPatients();
		for(int i = 0; i < patients.length; i++) {
			for(int j = 0; j < patients[i].getPatientFeatures().length; j++) {
				if( patients[i].getPatientFeatures()[j].getFeatureValue() == null) {
					//System.out.println("Empty feature value for feature: " + patients[i].getPatientFeatures()[j].getFeatureName() + " in patient ID: " + patients[i].getPatientID());
					return true;
				}
			}
		}
		return false;
	}
	
	@Override
	public boolean isFeatureSameValue(Sample sample, Feature feature) {
		Patient[] patients = sample.getPatients();
		String featureName = feature.getFeatureName();
		Double firstValue = null;
		boolean sameValue = true;
		
		for(int i = 1; i < patients.length; i++) {
			if (patients[i] != null && patients[i].getPatientFeatures() != null) {
				for(int j = 0; j < patients[i].getPatientFeatures().length; j++) {
					Feature patientFeature = patients[i].getPatientFeatures()[j];
					if(patientFeature.getFeatureValue() != null && featureName.equals(patientFeature.getFeatureName())) {
						Double featureValue = patientFeature.getFeatureValue().doubleValue();
						//System.out.println("isFeatureSameValue - Patient " + patients[i].getPatientID() + " Feature " + patientFeature.getFeatureName() + " value: " + featureValue);
						if(firstValue == null){
							firstValue = featureValue;
						} else if(!firstValue.equals(featureValue)) {
							sameValue = false;
						}
					}
				}
			}
		}
		//System.out.println("Is feature " + featureName + " the same value for all patients? " + sameValue);
		return sameValue;
	}
	
	@Override
	public boolean isFeatureAllZero(Sample sample, Feature feature) {
		Patient[] patients = sample.getPatients();
		String featureName = feature.getFeatureName();
		boolean zeroValue = true;
		
		for(int i = 0; i < patients.length; i++) {
			if (patients[i] != null && 
				patients[i].getPatientFeatures() != null) {
				for(int j = 0; j < patients[i].getPatientFeatures().length; j++) {
					Feature patientFeature = patients[i].getPatientFeatures()[j];
					if(patientFeature != null && featureName.equals(patientFeature.getFeatureName())){
						Double featureValue = patientFeature.getFeatureValue().doubleValue();
						if(featureValue != null && featureValue != 0.0) {
							zeroValue = false;
						}
					}
				}
			}
		}
		return zeroValue;
	}	

	@Override
	public Sample addPatientFeature(Sample sample) {
		Patient[] patients = sample.getPatients();
		for (int i = 0; i < patients.length; i++) {
			if(patients[i] != null) {
				if (patients[i].getPatientFeatures() == null) {
					patients[i].setPatientFeatures(store.createPatientFeatures());
				}
			}
		}
		return sample;
	}
	
	@Override
	public Sample addFeature(Sample sample) {
		Patient[] patients = sample.getPatients();
		for (int i = 0; i < patients.length; i++) {
			if (patients[i] != null && patients[i].getPatientFeatures() != null) {
				for (int j = 0; j < patients[i].getPatientFeatures().length; j++) {
					if (patients[i].getPatientFeatures()[j] == null) {
						patients[i].getPatientFeatures()[j] = store.createFeatureFromIndex(j);
					}
				}
			}
		}
		return sample;
	}
	
	@Override
	public Sample addFeatureValue(Sample sample, Feature feature) {
		Patient[] patients = sample.getPatients();
		String featureName = feature.getFeatureName();
	    Double average = calculateFeatureAverage(sample, feature);

	    for (int i = 0; i < patients.length; i++) {
	    	if (patients[i] != null && patients[i].getPatientFeatures() != null) {
		        for (int j = 0; j < patients[i].getPatientFeatures().length; j++) {
		            if (patients[i].getPatientFeatures()[j] == null || patients[i].getPatientFeatures()[j].getFeatureValue() == null) {
		                if (patients[i].getPatientFeatures()[j] != null && patients[i].getPatientFeatures()[j].getFeatureName().equals(featureName)) {
		                	patients[i].getPatientFeatures()[j].setFeatureValue(average);
		                } 
		            }
		        }
	    	}
	    }
	    return sample;
	}
	
	@Override
	public Double calculateFeatureAverage(Sample sample, Feature feature) {
		Double featureAverage;
		Patient[] patients = sample.getPatients();
		String featureName = feature.getFeatureName();
		double featureSum = 0.0;		
		int featureCount = 0;
		
		
		for(int i = 0; i < patients.length; i++) {
			for (int j = 0; j < patients[i].getPatientFeatures().length; j++) {
				Feature patientFeature = patients[i].getPatientFeatures()[j];
				if(patientFeature.getFeatureName().equals(featureName)) {
					featureSum += patientFeature.getFeatureValue().doubleValue();
					featureCount++;
				}
			}
		}
		featureAverage = featureSum / featureCount;
		return featureAverage;
	}
	
	@Override
	public Sample getCleanSample() {
		return cleanSample;
	}

	public Sample removeEmpty(Sample sample) {
		Patient[] patients = sample.getPatients();
		
		int patientCount = 0;
		for (int i = 0; i < patients.length; i++) { 
		}
		
		for(int i = 0; i < patients.length; i++) {
			if(patients[i] != null) {
				patientCount++;
			} 
		}
		
		Sample removed = Sample.getSample(patientCount);
		
		for(int i = 0; i < patients.length; i++) {
			if(patients[i] != null) {
				removed.addToPatientSample(patients[i]);
			}
		}
		return removed;
	}
}
