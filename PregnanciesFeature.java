package home;

public class PregnanciesFeature implements Feature {

	Double pregnancies;
	public String pregnanciesFeatureName;
	int pregnanciesIndex;
	Feature pregnanciesFeature; 
	double pregnanciesFeatureAverage;
	double pregnanciesSum; 
	double pregnanciesCount; 
	double medianPregnancies;
	boolean isFeatureUsed;
	
	public PregnanciesFeature() {
		this("Pregnancies");
	}
	
	public PregnanciesFeature(String featureName) {
		this.pregnanciesFeatureName = featureName;
		this.pregnanciesIndex = 0;
		this.pregnanciesFeatureAverage = 0.0;
		this.pregnanciesSum = 0;
		this.isFeatureUsed = false;
	}

	@Override
	public String getFeatureName() {
		return pregnanciesFeatureName;
	}

	@Override
	public void setFeatureName(String featureName) {
		this.pregnanciesFeatureName = featureName;
	}

	@Override
	public int getFeatureIndex() {
		return pregnanciesIndex;
	}
	
	@Override
	public Double getFeatureAverage() {
		return pregnanciesFeatureAverage;
	}

	@Override
	public void setFeatureAverage(Double featureAverage) {
		pregnanciesFeatureAverage = featureAverage;
	}

	@Override
	public Double getFeatureValue() {
		return pregnancies;
	}

	@Override
	public void setFeatureValue(Double featureValue) {
		if( featureValue == null) {
			featureValue = 0.0;
		} else {
			this.pregnancies = featureValue;
		}
	}
	
	@Override
	public void setFeatureValue(Integer featureValue) {
		if( featureValue == null) {
			featureValue = 0;
		} else {
			this.pregnancies = featureValue.doubleValue();
		}
	}
	
	@Override
	public Feature getFeature() {
		return pregnanciesFeature;
	}

	@Override
	public void setFeature(Feature patientFeature) {
		this.pregnanciesFeature = patientFeature;
		
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
		this.pregnancies = 0.0; 
	}	
}
