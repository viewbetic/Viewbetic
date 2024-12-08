package home;

public class AgeFeature implements Feature {

	Integer age;
	public String ageFeatureName;
	int ageIndex;
	Feature ageFeature; 
	double ageFeatureAverage;
	double ageSum; 
	double ageCount; 
	double medianAge;
	boolean isFeatureUsed;
	
	public AgeFeature() {
		this("Age");
	}
	
	public AgeFeature(String featureName) {
		this.ageFeatureName = featureName;
		this.ageIndex = 7;
		this.ageFeatureAverage = 0.0;
		this.ageSum = 0;
		this.isFeatureUsed = false;
	}

	@Override
	public String getFeatureName() {
		return ageFeatureName;
	}
	
	@Override
	public int getFeatureIndex() {
		return ageIndex;
	}
	
	@Override
	public void setFeatureName(String featureName) {
		ageFeatureName = featureName;
	}

	@Override
	public Double getFeatureAverage() {
		return ageFeatureAverage;
	}
	
	@Override
	public void setFeatureAverage(Double featureAverage) {
		ageFeatureAverage = featureAverage;
	}
	
	@Override
	public void setFeatureValue(Double featureValue) {
		this.age = featureValue.intValue();
	}
	
	@Override
	public void setFeatureValue(Integer featureValue) {
		this.age = featureValue;
	}
	
	@Override
	public Integer getFeatureValue() {
		return age;
	}
	
	@Override
	public Feature getFeature() {
		return ageFeature;
	}

	@Override
	public void setFeature(Feature patientFeature) {
		this.ageFeature = patientFeature;
	}

	@Override
	public boolean isFeatureUsed() {
		return isFeatureUsed;
	}
	
	public void setIsFeatureUsed(boolean isFeatureUsed) {
		this.isFeatureUsed = isFeatureUsed;
	}
}
