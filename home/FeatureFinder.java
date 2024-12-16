package home;

public interface FeatureFinder extends Feature {
	Feature findFeatureByName(Patient patient, String feature);
}
