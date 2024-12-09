package home;

public class Sample {
	
	private Patient[] patients;
	private int sampleID;
	private static int newSampleID = 1; 
	private int sampleIndex;
	
	public Sample(int sampleSize) {
		patients = new Patient[sampleSize];
		sampleIndex = 0;
		this.sampleID = newSampleID++; //Fix
	}
	
	public Integer getSampleId() {
		return sampleID;
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
