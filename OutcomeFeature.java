package home;

public class OutcomeFeature implements Feature {

	Integer outcome;
	public String outcomeFeatureName;
	int outcomeIndex;
	Feature outcomeFeature; 
	double outcomeFeatureAverage;
	double outcomeSum; 
	double outcomeCount; 
	double medianOutcome;
	boolean isFeatureUsed;
	
	public OutcomeFeature() {
		this("Outcome");
	}
	
	public OutcomeFeature(String featureName) {
		this.outcomeFeatureName = featureName;
		this.outcomeIndex = 8;
		this.outcomeFeatureAverage = 0.0;
		this.outcomeSum = 0;
	}

	@Override
	public String getFeatureName() {
		return outcomeFeatureName;
	}

	@Override
	public void setFeatureName(String featureName) {
		outcomeFeatureName = featureName;
	}

	@Override
	public int getFeatureIndex() {
		return outcomeIndex;
	}
	
	@Override
	public Double getFeatureAverage() {
		return outcomeFeatureAverage;
	}
	
	@Override
	public void setFeatureAverage(Double featureAverage) {
		outcomeFeatureAverage = featureAverage;
	}
	
	@Override
	public void setFeatureValue(Double featureValue) {
		this.outcome = featureValue.intValue();
	}
	
	@Override
	public void setFeatureValue(Integer featureValue) {
		this.outcome = featureValue;
	}
	
	@Override
	public Integer getFeatureValue() {
		return outcome;
	}

	@Override
	public Feature getFeature() {
		return outcomeFeature;
	}

	@Override
	public void setFeature(Feature patientFeature) {
		this.outcomeFeature = patientFeature;
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
