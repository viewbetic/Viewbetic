package home;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.Assert.*;

public class DecisionTreeTest {

	private DecisionTree tree;
	private Feature splitFeature;
	private TreeNode root;
	private Patient[] patients;
	private PatientFeatureFinder finder;
	private Patient patient1;
	private Patient patient2;
	private Patient patient3;
	private Sample[] samples;
	
	@BeforeEach
	public void setUp() {
		tree = new DecisionTree();
		
		splitFeature = new GlucoseFeature();
		
		Feature[] patientFeature1 = new Feature[] { new GlucoseFeature(), new OutcomeFeature() };
		Feature[] patientFeature2 = new Feature[] { new GlucoseFeature(), new OutcomeFeature() };
		Feature[] patientFeature3 = new Feature[] { new GlucoseFeature(), new OutcomeFeature() };
		
		patientFeature1[0].setFeatureValue(119.0);
		patientFeature2[0].setFeatureValue(125.0);
		patientFeature3[0].setFeatureValue(124.0);
		
		patientFeature1[1].setFeatureValue(0);
		patientFeature2[1].setFeatureValue(0);
		patientFeature3[1].setFeatureValue(0);
		
		patient1 = new Patient(1, "Test", "Patient1", patientFeature1);
		patient2 = new Patient(2, "Test", "Patient2", patientFeature2);
		patient3 = new Patient(3, "Test", "Patient3", patientFeature3);
		
		patients = new Patient[] {patient1, patient2, patient3};
		
		samples = new Sample[1];
		
		samples[0] = new Sample(patients.length);
		
		samples[0].addToPatientSample(patient1);
		samples[0].addToPatientSample(patient2);
		samples[0].addToPatientSample(patient3);
		
		root = new TreeNode(samples);
		
		finder = new PatientFeatureFinder();
	}
	
	@Test
	public void testCreateTree() {
		TreeNode newNode = tree.createTree(root);
		
		assertNotNull(newNode.getLeft());
		assertNotNull(newNode.getRight());
	
		Feature updatedFeature = newNode.getSplitFeature();
		
		assertTrue(updatedFeature.isFeatureUsed());
		
		Double splitV = 124.0;
		
		assertEquals(splitV, newNode.getSplitValue());
	}
	
	@Test
	public void testFindSplitValue() {
		Double medianValue = 124.0;
		
		Sample testSample = samples[0];
		
		Double splitValue = tree.findSplitValue(testSample, splitFeature);
		
		assertEquals(splitValue, medianValue);
	}
	
	@Test
	public void testSplitNode() {
		Double splitValue = 120.0;
		
		TreeNode splitN = tree.splitNode(root, splitValue, splitFeature);
		
		assertNotNull(splitN.getLeft());
		assertNotNull(splitN.getRight());
		
		Sample leftSample = splitN.getLeft().getSample()[0];
		
		for(int i = 0; i < leftSample.getPatients().length; i++) {
			Feature glucoseFeature = finder.findFeatureByName(leftSample.getPatients()[i], "Glucose");
			assertNotNull(glucoseFeature);
			assertTrue((Double)glucoseFeature.getFeatureValue() <= splitValue);
		}
		
		Sample rightSample = splitN.getRight().getSample()[0];
		
		for(int i = 0; i < rightSample.getPatients().length; i++) {
			Feature glucoseFeature = finder.findFeatureByName(rightSample.getPatients()[i], "Glucose");
			assertNotNull(glucoseFeature);
			assertTrue((Double)glucoseFeature.getFeatureValue() > splitValue);
		}
	}
}
