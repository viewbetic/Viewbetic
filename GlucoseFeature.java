package home;

public class GlucoseFeature implements Feature{

	Double glucose;
	public String glucoseFeatureName; 
	int glucoseIndex;
	Feature glucoseFeature; 
	double glucoseFeatureAverage;
	double glucoseSum; 
	double glucoseCount; 
	double medianGlucose;
	boolean isFeatureUsed;
	
	
	public GlucoseFeature() {
		this("Glucose");
	}
	
	public GlucoseFeature(String featureName) {
		this.glucoseFeatureName = featureName;
		this.glucoseIndex = 1;
		this.glucoseFeatureAverage = 0.0;
		this.glucoseSum = 0;
	}

	@Override
	public String getFeatureName() {
		return glucoseFeatureName;
	}

	@Override
	public void setFeatureName(String featureName) {
		this.glucoseFeatureName = featureName;
	}

	@Override
	public int getFeatureIndex() {
		return glucoseIndex;
	}
	
	@Override
	public Double getFeatureAverage() {
		return glucoseFeatureAverage;
	}

	@Override
	public void setFeatureAverage(Double featureAverage) {
		glucoseFeatureAverage = featureAverage;
	}

	@Override
	public Double getFeatureValue() {
		return glucose;
	}

	@Override
	public void setFeatureValue(Double featureValue) {
		this.glucose = featureValue;
	}
	
	@Override
	public void setFeatureValue(Integer featureValue) {
		//this.glucose = featureValue;
	}
	
	@Override
	public Feature getFeature() {
		return glucoseFeature;
	}

	@Override
	public void setFeature(Feature patientFeature) {
		this.glucoseFeature = patientFeature;
	}

	@Override
	public boolean isFeatureUsed() {
		return isFeatureUsed;
	}

	@Override
	public void setIsFeatureUsed(boolean isFeatureUsed) {
		this.isFeatureUsed = isFeatureUsed;
	}
}
