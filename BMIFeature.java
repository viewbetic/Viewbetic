package home;

public class BMIFeature implements Feature{

	Double bmi;
	public String bmiFeatureName;
	int bmiIndex;
	Feature bmiFeature; 
	double bmiFeatureAverage;
	double bmiSum; 
	double bmiCount; 
	double medianBMI;
	boolean isFeatureUsed;
	
	public BMIFeature() {
		this("BMI");
	}
	
	public BMIFeature(String featureName) {
		this.bmiFeatureName = featureName;
		this.bmiIndex = 5;
		this.bmiFeatureAverage = 0.0;
		this.bmiSum = 0;
	}

	@Override
	public String getFeatureName() {
		return bmiFeatureName;
	}

	@Override
	public void setFeatureName(String featureName) {
		this.bmiFeatureName = featureName;
	}
	
	@Override
	public int getFeatureIndex() {
		return bmiIndex;
	}
	
	@Override
	public Double getFeatureAverage() {
		return bmiFeatureAverage;
	}

	@Override
	public void setFeatureAverage(Double featureAverage) {
		bmiFeatureAverage = featureAverage;
	}

	@Override
	public Double getFeatureValue() {
		return bmi;
	}

	@Override
	public void setFeatureValue(Double featureValue) {
		this.bmi = featureValue;
	}
	
	@Override
	public void setFeatureValue(Integer featureValue) {
		//this.bmi = featureValue;
	}
	
	@Override
	public Feature getFeature() {
		return bmiFeature;
	}

	@Override
	public void setFeature(Feature patientFeature) {
		this.bmiFeature = patientFeature;
		
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
