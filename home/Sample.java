package home;

public class Sample {
	
	private static Sample newSample = null;
	private Patient[] patients;
	private int sampleID;
	private static int newSampleID = 1; 
	private int sampleIndex;
	
	private Sample(int sampleSize) {
		patients = new Patient[sampleSize];
		sampleIndex = 0;
		this.sampleID = newSampleID++; 
	}
	
	public Integer getSampleId() {
		return sampleID;
	}
	
	public static Sample getSample(int sampleSize) {
		newSample = new Sample(sampleSize);
		return newSample;
	}
	
	public void addToPatientSample(Patient patient) {
		if(sampleIndex < patients.length) {
			patients[sampleIndex++] = patient;
		} 
	}
	
	public Patient[] getPatients() {
		return patients;
	}
}
