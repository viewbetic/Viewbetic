package home;

import java.util.*;

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
	
	public Patient chooseRandomPatient(Patient[] sample) {
		int randomIndex = new Random().nextInt(sample.length);
		Patient randomPatient = sample[randomIndex];
		return randomPatient;
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
