package home;

public class DiabetesPedigreeFunctionFeature implements Feature {

	Double diabetesPedigreeFunction;
	public String diabetesPedigreeFunctionFeatureName; 
	int diabetesPedigreeFunctionIndex;
	Feature diabetesPedigreeFunctionFeature; 
	double diabetesPedigreeFunctionFeatureAverage;
	double diabetesPedigreeFunctionSum; 
	double diabetesPedigreeFunctionCount; 
	double mediandiabetesPedigreeFunction;
	boolean isFeatureUsed;
	
	public DiabetesPedigreeFunctionFeature() {
		this("DiabetesPedigreeFunction");
	}
	
	public DiabetesPedigreeFunctionFeature(String featureName) {
		this.diabetesPedigreeFunctionFeatureName = featureName;
		this.diabetesPedigreeFunctionIndex = 6;
		this.diabetesPedigreeFunctionFeatureAverage = 0.0;
		this.diabetesPedigreeFunctionSum = 0;
		this.isFeatureUsed = false;
	}

	@Override
	public String getFeatureName() {
		return diabetesPedigreeFunctionFeatureName;
	}

	@Override
	public void setFeatureName(String featureName) {
		this.diabetesPedigreeFunctionFeatureName = featureName;
	}

	@Override
	public int getFeatureIndex() {
		return diabetesPedigreeFunctionIndex;
	}
	
	@Override
	public Double getFeatureAverage() {
		return diabetesPedigreeFunctionFeatureAverage;
	}

	@Override
	public void setFeatureAverage(Double featureAverage) {
		diabetesPedigreeFunctionFeatureAverage = featureAverage;
	}

	@Override
	public Double getFeatureValue() {
		return diabetesPedigreeFunction;
	}

	@Override
	public void setFeatureValue(Double featureValue) {
		this.diabetesPedigreeFunction = featureValue;
	}
	
	@Override
	public void setFeatureValue(Integer featureValue) {
		this.diabetesPedigreeFunction = featureValue.doubleValue();
	}
	
	@Override
	public Feature getFeature() {
		return diabetesPedigreeFunctionFeature;
	}

	@Override
	public void setFeature(Feature patientFeature) {
		this.diabetesPedigreeFunctionFeature = patientFeature;
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
		this.diabetesPedigreeFunction = 0.0; 
	}	
}
