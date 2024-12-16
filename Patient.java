package home;

public class Patient{
	
	public Patient(Integer patientID, String patientFirstName, String patientLastName, Feature[] features) {
		this.patientId = patientID;
		this.firstName = patientFirstName;
		this.lastName = patientLastName;
		this.patientFeatures = features;
	}
	
	public Integer getPatientID() {
		return patientId;
	}
	
	public void setPatientID(Integer patientID) {
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
	
	public Feature[] getPatientFeatures() {
		return patientFeatures;
	}
	
	public void setPatientFeatures(Feature[] features) {
		this.patientFeatures = features;
	}
	
	String firstName;
	String lastName;
	Integer patientId;
	Feature[] patientFeatures;	
}
