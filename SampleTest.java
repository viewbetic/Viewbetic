package home;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.Assert.*;

public class SampleTest {

	Sample sampling;
	Clean cleaning;
	Patient patient;
	
	@BeforeEach                                         
    void setUp() {
		sampling = new Sample();
		patient = new Patient(1, null, null, null, null, null, null, null, null, null, null, null); //	ID 1
    }
	
	@Test
	void testChooseRandomPatient() {
		Patient[] newSample = {patient}; //fill with one patient
		patient = sampling.chooseRandomPatient(newSample);
		assertNotNull("Patient cannot be null", patient);
	}
	
	@Test
	void testAddToSample() {
		sampling.addToSample(patient);
		Patient[] newSample = sampling.getSample();
		assertNotNull("Sample should not be empty", newSample[0]);
	}
}
