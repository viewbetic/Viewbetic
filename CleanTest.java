package home;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.Assert.*;

public class CleanTest {
	
	Sample sampling;
	Clean cleaning;
	Patient patient;
	
	@BeforeEach                                         
    void setUp() {
		sampling = new Sample();
		cleaning = new Clean();
		patient = new Patient(1, null, null, null, null, null, null, null, null, null, null, null);
    }
	
	@Test
	void testIsSampleClean() {
		patient.setPregnancies(0.0);
		patient.setGlucose(0.0);
		patient.setBloodPressure(0.0);
		patient.setskinThickness(0.0);
		patient.setInsulin(0.0);
		patient.setBMI(0.0);
		patient.setDiabetesPedigreeFunction(0.0);
		patient.setAge(0);
		
		Patient[] newSample = {patient};
		cleaning.isFeatureEmpty(newSample);
		assertTrue(cleaning.isSampleClean(newSample));
	}
	
	@Test
	void testIsFeatureEmpty() {
		Patient[] newSample = {patient};
		cleaning.isFeatureEmpty(newSample);
		assertTrue(cleaning.isFeatureEmpty(newSample));
	}
	
	@Test
	void testCalculateFeatureAverage() {
		Patient[] newSample = {patient};
		cleaning.calculateFeatureAverage(newSample);
		assertNotNull("Average should not be null", cleaning.getAgeFeatureAverage());
		//add equals test
	}
	
	@Test
	void testAddFeatureValue() {
		Patient updatedPatient = cleaning.addFeatureValue(patient);
		assertNotNull("Pregnancies should not be null", updatedPatient.getPregnancies());
		assertNotNull("Glucose should not be null", updatedPatient.getGlucose());
		assertNotNull("Blood pressure should not be null", updatedPatient.getBloodPressure());
		assertNotNull("Skin thickness should not be null", updatedPatient.getskinThickness());
		assertNotNull("Insulin should not be null", updatedPatient.getInsulin());
		assertNotNull("BMI should not be null", updatedPatient.getBMI());
		assertNotNull("DPF should not be null", updatedPatient.getDiabetesPedigreeFunction());
		assertNotNull("Age should not be null", updatedPatient.getAge());
	}
}
