package home;

public class SkinThicknessFeature implements Feature{

	Double skinThickness;
	public String skinThicknessFeatureName;
	int skinThicknessIndex;
	Feature skinThicknessFeature; 
	double skinThicknessFeatureAverage;
	double skinThicknessSum;
	double skinThicknessCount; 
	double medianSkinThickness;
	boolean isFeatureUsed;
	
	public SkinThicknessFeature() {
		this("SkinThickness");
	}
	
	public SkinThicknessFeature(String featureName) {
		this.skinThicknessFeatureName = featureName;
		this.skinThicknessIndex = 3;
		this.skinThicknessFeatureAverage = 0.0;
		this.skinThicknessSum = 0;
		this.isFeatureUsed = false;
	}

	@Override
	public String getFeatureName() {
		return skinThicknessFeatureName;
	}

	@Override
	public void setFeatureName(String featureName) {
		this.skinThicknessFeatureName = featureName;
	}

	@Override
	public int getFeatureIndex() {
		return skinThicknessIndex;
	}
	
	@Override
	public Double getFeatureAverage() {
		return skinThicknessFeatureAverage;
	}

	@Override
	public void setFeatureAverage(Double featureAverage) {
		skinThicknessFeatureAverage = featureAverage;
	}

	@Override
	public Double getFeatureValue() {
		return skinThickness;
	}

	@Override
	public void setFeatureValue(Double featureValue) {
		if( featureValue == null) {
			featureValue = 0.0;
		} else {
			this.skinThickness = featureValue;
		}
	}
	
	@Override
	public void setFeatureValue(Integer featureValue) {
		if( featureValue == null) {
			featureValue = 0;
		} else {
			this.skinThickness = featureValue.doubleValue();
		}
	}
	
	@Override
	public Feature getFeature() {
		return skinThicknessFeature;
	}

	@Override
	public void setFeature(Feature patientFeature) {
		this.skinThicknessFeature = patientFeature;
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
		this.skinThickness = 0.0; 
	}
}
