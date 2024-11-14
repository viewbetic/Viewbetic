package home;

public class Patient{
	
	public Patient(Integer patientID, 
					String firstName,
					String lastName, 
					Double pregnancies, 
					Double glucose, 
					Double bloodPressure, 
					Double skinThickness, 
					Double insulin,
					Double bmi, 
					Double diabetesPedigreeFunction, 
					Integer age, 
					Double outcome) {
		this.patientId = patientID;
		this.firstName = firstName;
		this.lastName = lastName;
		this.pregnancies = pregnancies;
		this.glucose = glucose;
		this.bloodPressure = bloodPressure;
		this.skinThickness = skinThickness;
		this.insulin = insulin;
		this.bmi = bmi;
		this.diabetesPedigreeFunction = diabetesPedigreeFunction;
		this.age = age;
		this.outcome = outcome;
		
	}
	
	public Integer getId() {
		return patientId;
	}
	
	public void setID(Integer patientID) {
		this.patientId = patientID;
	}
	
	public String getFirstName() {
		return firstName;
	}
	
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	
	public String getLastName() {
		return lastName;
	}
	
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	
	public Double getPregnancies() {
		return pregnancies;
	}
	
	public void setPregnancies(Double patientPregnancies) {
		this.pregnancies = patientPregnancies;
	}
	
	public Double getGlucose() {
		return glucose;
	}
	
	public void setGlucose(Double patientGlucose) {
		this.glucose = patientGlucose;
	}
	
	public Double getBloodPressure() {
		return bloodPressure;
	}
	
	public void setBloodPressure(Double patientBloodPressure) {
		this.bloodPressure = patientBloodPressure;
	}
	
	
	public Double getskinThickness() {
		return skinThickness;
	}
	
	public void setskinThickness(Double patientskinThickness) {
		this.skinThickness = patientskinThickness;
	}
	
	
	public Double getInsulin() {
		return insulin;
	}
	
	public void setInsulin(Double patientInsulin) {
		this.insulin = patientInsulin;
	}
	
	
	public Double getBMI() {
		return bmi;
	}
	
	public void setBMI(Double patientBMI) {
		this.bmi = patientBMI;
	}
	
	
	public Double getDiabetesPedigreeFunction() {
		return diabetesPedigreeFunction;
	}
	
	public void setDiabetesPedigreeFunction(Double patientDiabetesPedigreeFunction) {
		this.diabetesPedigreeFunction = patientDiabetesPedigreeFunction;
	}
	
	
	public Integer getAge() {
		return age;
	}
	
	public void setAge(Integer patientAge) {
		this.age = patientAge;
	}
	
	public Double getOutcome() {
		return outcome;
	}
	
	public void setOutcome(Double patientOutcome) {
		this.outcome = patientOutcome;
	}
	
	String firstName;
	String lastName;
	Integer patientId;
	Double pregnancies;
	Double glucose;
	Double bloodPressure;
	Double skinThickness;
	Double insulin;
	Double bmi;
	Double diabetesPedigreeFunction;
	Integer age;
	Double outcome;
}
