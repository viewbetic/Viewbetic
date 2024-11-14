package home;

public class Clean {
	
	Patient[] cleanSample;
	double pregnanciesFeatureAverage;
	double glucoseFeatureAverage;
	double bloodPressureFeatureAverage;
	double skinThicknessFeatureAverage;
	double insulinFeatureAverage;
	double bmiFeatureAverage;
	double diabetesPedigreeFunctionFeatureAverage;
	int ageFeatureAverage;
	
	public Clean() {
		pregnanciesFeatureAverage = 0.0;
		glucoseFeatureAverage = 0.0;
		bloodPressureFeatureAverage = 0.0;
		skinThicknessFeatureAverage = 0.0;
		insulinFeatureAverage = 0.0;
		bmiFeatureAverage = 0.0;
		diabetesPedigreeFunctionFeatureAverage = 0.0;
		ageFeatureAverage = 0;
	}
	
	public Patient[] cleanSample(Patient[] sample) {
		for(int i = 0; i < sample.length; i++) {
			if(isFeatureEmpty(sample)) {
				calculateFeatureAverage(sample);
				addFeatureValue(sample[i]);
			}
		}
		cleanSample = sample;
		return cleanSample;
	}
	
	public boolean isSampleClean(Patient[] sample) {
		if(!isFeatureEmpty(sample) == true) {
			return true;
		}
		return false;
	}
	
	public boolean isFeatureEmpty(Patient[] sample) {		
		for(int i = 0; i < sample.length; i++) {
			if(sample[i].getPregnancies() == null) {
				return true; 
			}
			if(sample[i].getBloodPressure() == null) {
				return true;
			}
			if(sample[i].getBMI() == null) {
				return true;
			}
			if(sample[i].getGlucose() == null) {
				return true;
			}
			if(sample[i].getDiabetesPedigreeFunction() == null) {
				return true;
			}
			if(sample[i].getInsulin() == null) {
				return true;
			}
		}
		return false;
	}
	
	public void calculateFeatureAverage(Patient[] sample) {
		double pregnanciesCount = 0;
		double glucoseCount = 0;
		double bloodPressureCount = 0;
		double skinThicknessCount = 0;
		double insulinCount = 0;
		double bmiCount = 0;
		double diabetesPedigreeFunctionCount = 0;
		int ageCount = 0;
		
		double pregnanciesSum = 0;
		double glucoseSum = 0;
		double bloodPressureSum = 0;
		double skinThicknessSum = 0;
		double insulinSum = 0;
		double bmiSum = 0;
		double diabetesPedigreeFunctionSum = 0;
		int ageSum = 0;
		
		for(int i = 0; i < sample.length; i++) {
			if(sample[i].getPregnancies() != null) {
				pregnanciesSum += sample[i].getPregnancies();
				pregnanciesCount++;
			}
			pregnanciesFeatureAverage = pregnanciesSum / pregnanciesCount;
			
			if(sample[i].getGlucose() != null) {
				glucoseSum += sample[i].getGlucose();
				glucoseCount++;
			}
			glucoseFeatureAverage = glucoseSum / glucoseCount;
			
			if(sample[i].getBloodPressure() != null) {
				bloodPressureSum += sample[i].getBloodPressure();
				bloodPressureCount++;
			}
			bloodPressureFeatureAverage = bloodPressureSum / bloodPressureCount;
			
			if(sample[i].getskinThickness() != null) {
				skinThicknessSum += sample[i].getskinThickness();
				skinThicknessCount++;
			}
			skinThicknessFeatureAverage = skinThicknessSum / skinThicknessCount;
			
			if(sample[i].getInsulin() != null) {
				insulinSum += sample[i].getInsulin();
				insulinCount++;
			}
			insulinFeatureAverage = insulinSum / insulinCount;
			
			if(sample[i].getBMI() != null) {
				bmiSum += sample[i].getBMI();
				bmiCount++;
			}
			bmiFeatureAverage = bmiSum / bmiCount;
			
			if(sample[i].getDiabetesPedigreeFunction() != null) {
				diabetesPedigreeFunctionSum += sample[i].getDiabetesPedigreeFunction();
				diabetesPedigreeFunctionCount++;
			}
			diabetesPedigreeFunctionFeatureAverage = diabetesPedigreeFunctionSum / diabetesPedigreeFunctionCount;
			
			if(sample[i].getAge() != null) {
				ageSum += sample[i].getAge();
				ageCount++;
			}
			ageFeatureAverage = ageSum / ageCount;
		}
	}
	
	public Patient addFeatureValue(Patient patient) {
		if(patient.getPregnancies() == null) {
			patient.setPregnancies(pregnanciesFeatureAverage);
		}
		if(patient.getGlucose() == null) {
			patient.setGlucose(glucoseFeatureAverage);
		}
		if(patient.getBloodPressure() == null) {
			patient.setBloodPressure(bloodPressureFeatureAverage);
		}
		if(patient.getskinThickness() == null) {
			patient.setskinThickness(skinThicknessFeatureAverage);
		}
		if(patient.getInsulin() == null) {
			patient.setInsulin(insulinFeatureAverage);
		}
		if(patient.getBMI() == null) {
			patient.setBMI(bmiFeatureAverage);
		}
		if(patient.getDiabetesPedigreeFunction() == null) {
			patient.setDiabetesPedigreeFunction(diabetesPedigreeFunctionFeatureAverage);
		}
		if(patient.getAge() == null) {
			patient.setAge(ageFeatureAverage);
		}
		return patient;
	}
	
	public Patient[] getCleanSample() {
		return cleanSample;
	}
	
	public Double getPregnanciesFeatureAverage() {
		return pregnanciesFeatureAverage;
	}
	
	public void setPregnanciesFeatureAverage(Double pregnanciesFeatureAverage) {
		this.pregnanciesFeatureAverage = pregnanciesFeatureAverage;
	}
	
	
	public Double getBloodPressureFeatureAverage() {
		return bloodPressureFeatureAverage;
	}
	
	public void setBloodPressureFeatureAverage(Double bloodPressureFeatureAverage) {
		this.bloodPressureFeatureAverage = bloodPressureFeatureAverage;
	}
	
	
	public Double getSkinThicknessFeatureAverage() {
		return skinThicknessFeatureAverage;
	}
	
	public void setSkinThicknessFeatureAverage(Double skinThicknessFeatureAverage) {
		this.skinThicknessFeatureAverage = skinThicknessFeatureAverage;
	}
	
	
	public Double getInsulinFeatureAverage() {
		return insulinFeatureAverage;
	}
	
	public void setInsulinFeatureAverage(Double insulinFeatureAverage) {
		this.insulinFeatureAverage = insulinFeatureAverage;
	}
	
	
	public Double getBMIFeatureAverage() {
		return bmiFeatureAverage;
	}
	
	public void setBMIFeatureAverage(Double bmiFeatureAverage) {
		this.bmiFeatureAverage = bmiFeatureAverage;
	}
	
	
	public Double getDiabetesPedigreeFunctionFeatureAverage() {
		return diabetesPedigreeFunctionFeatureAverage;
	}
	
	public void setDiabetesPedigreeFunctionFeatureAverage(Double diabetesPedigreeFunctionFeatureAverage) {
		this.diabetesPedigreeFunctionFeatureAverage = diabetesPedigreeFunctionFeatureAverage;
	}
	
	public Integer getAgeFeatureAverage() {
		return ageFeatureAverage;
	}
	
	public void setAgeFeatureAverage(Integer ageFeatureAverage) {
		this.ageFeatureAverage = ageFeatureAverage;
	}
	
	public Double getGlucoseFeatureAverage() {
		return glucoseFeatureAverage;
	}
	
	public void setGlucoseFeatureAverage(Double glucoseFeatureAverage) {
		this.glucoseFeatureAverage = glucoseFeatureAverage;
	}
	
}
