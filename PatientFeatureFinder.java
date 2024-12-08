package home;

public class PatientFeatureFinder implements FeatureFinder {

	String featureName;
	Number featureValue;
	int featureIndex;
	Double featureAverage;
	Feature patientFeatureFinder;
	boolean isFeatureLocated;
	
	@Override
	public String getFeatureName() {
		return featureName;
	}

	@Override
	public void setFeatureName(String featureName) {
		this.featureName = featureName;
	}

	@Override
	public int getFeatureIndex() {
		return featureIndex;
	}

	@Override
	public Number getFeatureValue() {
		return featureValue;
	}

	@Override
	public void setFeatureValue(Double featureValue) {
		this.featureValue = featureValue;
	}
	
	@Override
	public void setFeatureValue(Integer featureValue) {
		this.featureValue = featureValue;
	}
	@Override
	public Number getFeatureAverage() {
		return featureAverage;
	}

	@Override
	public void setFeatureAverage(Double featureAverage) {
		this.featureAverage = featureAverage;
	}

	@Override
	public Feature getFeature() {
		return patientFeatureFinder;
	}

	@Override
	public void setFeature(Feature patientFeature) {
		this.patientFeatureFinder = patientFeature;
	}

	@Override
	public Feature findFeatureByName(Patient patient, String feature) {
		Feature[] patientFeatures = patient.getPatientFeatures();
		
		for (int i = 0; i < patientFeatures.length; i++) {
			if(patientFeatures[i] != null && patientFeatures[i].getFeatureName().equals(feature)) {
				return patientFeatures[i];
			}
		}
		return null;
	}

	@Override
	public boolean isFeatureUsed() {
		return isFeatureLocated;
	}

	@Override
	public void setIsFeatureUsed(boolean isFeatureUsed) {
		this.isFeatureLocated = isFeatureUsed;
	}

}
