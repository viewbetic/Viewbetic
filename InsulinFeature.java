package home;

public class InsulinFeature implements Feature{

	Double insulin;
	public String insulinFeatureName; 
	int insulinIndex;
	Feature insulinFeature; 
	double insulinFeatureAverage;
	double insulinSum; 
	double insulinCount; 
	double medianInsulin;
	boolean isFeatureUsed;
	
	public InsulinFeature() {
		this("Insulin");
	}
	
	public InsulinFeature(String featureName) {
		this.insulinFeatureName = featureName;
		this.insulinIndex = 4;
		this.insulinFeatureAverage = 0.0;
		this.insulinSum = 0;
	}
	
	@Override
	public String getFeatureName() {
		return insulinFeatureName;
	}

	@Override
	public void setFeatureName(String featureName) {
		this.insulinFeatureName = featureName;
	}
	
	@Override
	public int getFeatureIndex() {
		return insulinIndex;
	}

	@Override
	public Double getFeatureAverage() {
		return insulinFeatureAverage;
	}

	@Override
	public void setFeatureAverage(Double featureAverage) {
		insulinFeatureAverage = featureAverage;
	}

	@Override
	public Double getFeatureValue() {
		return insulin;
	}

	@Override
	public void setFeatureValue(Double featureValue) {
		this.insulin = featureValue;
	}
	
	@Override
	public void setFeatureValue(Integer featureValue) {
		//this.insulin = featureValue;
	}
	
	@Override
	public Feature getFeature() {
		return insulinFeature;
	}

	@Override
	public void setFeature(Feature patientFeature) {
		this.insulinFeature = patientFeature;
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
