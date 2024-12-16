package home;

public class BloodPressureFeature implements Feature{

	Double bloodPressure;
	public String bloodPressureFeatureName;
	public int bloodPressureIndex;
	Feature bloodPressureFeature; 
	double bloodPressureFeatureAverage;
	double bloodPressureSum; 
	double bloodPressureCount; 
	double medianBloodPressure;
	boolean isFeatureUsed;
	
	public BloodPressureFeature() {
		this("BloodPressure");
	}
	
	public BloodPressureFeature(String featureName) {
		this.bloodPressureFeatureName = featureName;
		this.bloodPressureIndex = 2;
		this.bloodPressureFeatureAverage = 0.0;
		this.bloodPressureSum = 0;
		this.isFeatureUsed = false;
	}

	@Override
	public String getFeatureName() {
		return bloodPressureFeatureName;
	}

	@Override
	public void setFeatureName(String featureName) {
		this.bloodPressureFeatureName = featureName;
	}
	
	@Override
	public int getFeatureIndex() {
		return bloodPressureIndex;
	}
	
	@Override
	public Double getFeatureAverage() {
		return bloodPressureFeatureAverage;
	}

	@Override
	public void setFeatureAverage(Double featureAverage) {
		this.bloodPressureFeatureAverage = featureAverage;
	}

	@Override
	public Double getFeatureValue() {
		return bloodPressure;
	}

	@Override
	public void setFeatureValue(Double featureValue) {
		if( featureValue == null) {
			featureValue = 0.0;
		} else {
			this.bloodPressure = featureValue;
		}
	}
	
	@Override
	public void setFeatureValue(Integer featureValue) {
		if( featureValue == null) {
			featureValue = 0;
		} else {
			this.bloodPressure = featureValue.doubleValue();
		}
	}
	
	@Override
	public Feature getFeature() {
		return bloodPressureFeature;
	}

	@Override
	public void setFeature(Feature patientFeature) {
		this.bloodPressureFeature = patientFeature;
	}

	@Override
	public boolean isFeatureUsed() {
		return isFeatureUsed;
	}

	@Override
	public void setIsFeatureUsed(boolean isFeatureUsed) {
		this.isFeatureUsed = isFeatureUsed;
	}

	@Override
	public void setFeatureValueToNull() { 
		this.bloodPressure = 0.0; 
	}		
}
