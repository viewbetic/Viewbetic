package home;

public interface Feature {
	public String getFeatureName();
	public void setFeatureName(String featureName);
	
	public int getFeatureIndex();
	
	public Number getFeatureValue();
	
	public void setFeatureValue(Double featureValue);
	public void setFeatureValue(Integer featureValue);
	
	public Number getFeatureAverage();
	public void setFeatureAverage(Double featureAverage);
	
	public Feature getFeature();
	public void setFeature(Feature patientFeature);
	
	public boolean isFeatureUsed();
	public void setIsFeatureUsed(boolean isFeatureUsed);
}
