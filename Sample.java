package home;

import java.util.*;

public class Sample {
	
	private Patient[] sample;
	private int sampleIndex;
	
	public Sample() {
		sample = new Patient[Home.getPatientCount()];
		sampleIndex = 0;
	}
	
	public Patient chooseRandomPatient(Patient[] sample) {
		int randomIndex = new Random().nextInt(sample.length);
		Patient randomPatient = sample[randomIndex];
		return randomPatient;
	}
	
	
	public void addToSample(Patient patient) {
		if(sampleIndex < sample.length) {
			sample[sampleIndex++] = patient;
		} else {
			System.out.println("Sample is full");
		}		
	}
	
	public Patient[] getSample() {
		return sample;
	}
}
