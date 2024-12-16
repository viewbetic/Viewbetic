package home;

public class FeatureStore {

	String featureName;
	private static Feature[] features;
	
	public FeatureStore() {
		features = new Feature[9];
	}
	
	public FeatureStore(String featureName) {
		this.featureName = featureName;
	}
	
	Feature createFeatureFromName(String featureName) throws IllegalArgumentException {
		if(featureName.equals("Pregnancies")){
			return new PregnanciesFeature(featureName);
		} else if(featureName.equals("Glucose")) {
			return new GlucoseFeature(featureName);
		} else if(featureName.equals("BloodPressure")) {
			return new BloodPressureFeature(featureName);
		} else if(featureName.equals("SkinThickness")) {
			return new SkinThicknessFeature(featureName);
		} else if(featureName.equals("Insulin")) {
			return new InsulinFeature(featureName);
		} else if(featureName.equals("BMI")) {
			return new BMIFeature(featureName);
		} else if(featureName.equals("DiabetesPedigreeFunction")) {
			return new DiabetesPedigreeFunctionFeature(featureName);
		} else if(featureName.equals("Age")) {
			return new AgeFeature(featureName);
		} else if(featureName.equals("Outcome")) {
			return new OutcomeFeature(featureName);
		}
		throw new IllegalArgumentException("No feature available for: " + featureName);
	}
	
	Feature createFeatureFromIndex(int featureIndex) {
		if(featureIndex == 1) {
			return new PregnanciesFeature("Pregnancies");
		} else if (featureIndex == 2){
			return new GlucoseFeature("Glucose");
		} else if (featureIndex == 3){
			return new BloodPressureFeature("BloodPressure");
		} else if (featureIndex == 4){
			return new SkinThicknessFeature("SkinThickness");
		} else if (featureIndex == 5){
			return new InsulinFeature("Insulin");
		} else if (featureIndex == 6){
			return new BMIFeature("BMI");
		} else if (featureIndex == 7){
			return new DiabetesPedigreeFunctionFeature("DiabetesPedigreeFunction");
		} else if (featureIndex == 8) {
			return new AgeFeature("Age");
		}
		throw new IllegalArgumentException("No feature available for: "  + featureIndex);
	}

	public Feature[] createPatientFeatures() {
		
		features[0] = new PregnanciesFeature("Pregnancies");
		features[1] = new GlucoseFeature("Glucose");
		features[2] = new BloodPressureFeature("BloodPressure");
		features[3] = new SkinThicknessFeature("SkinThickness");
		features[4] = new InsulinFeature("Insulin");
		features[5] = new BMIFeature("BMI");
		features[6] = new DiabetesPedigreeFunctionFeature("DiabetesPedigreeFunction");
		features[7] = new AgeFeature("Age");
		features[8] = new OutcomeFeature("Outcome");
		
		return features;
	}
	
	public Feature[] getFeatures() {
		return features;
	}
}
