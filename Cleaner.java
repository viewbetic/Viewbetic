package home;

public interface Cleaner {
	
	public Sample createCleanSample(Sample sample);
	
	public boolean isSampleClean(Sample sample);
	
	public boolean isPatientFeatureEmpty(Sample sample);
	
	public boolean isFeatureEmpty(Sample sample);
	
	public boolean isFeatureValueEmpty(Sample sample);
	
	public Double calculateFeatureAverage(Sample sample, Feature feature);

	public Sample addPatientFeature(Sample sample);
	
	public Sample addFeature(Sample sample);
	
	public Sample addFeatureValue(Sample sample, Feature feature);
	
	//public Sample removePatientFeature(Sample sample);
	
	//public Sample removeFeature(Sample sample);
	
	//public Sample removeFeatureValue(Sample sample, Feature feature);
	
	public Sample getCleanSample();
}
