package home;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.Assert.*;

public class RandomForestTest {
	FeatureStore store;
	RandomForest randomForest;
    DecisionTree[] forest; 
    Sample[] cleanSamples; 
    TreeNode node;
	Feature[] newPatientFeatures;
	Sample newSample; 
	Patient newPatient;
	
	@BeforeEach
	public void setUp() {
		  store = new FeatureStore(); 
		  newPatientFeatures = store.createPatientFeatures();
		  
		  newPatientFeatures[0].setFeatureValue(0);
		  newPatientFeatures[1].setFeatureValue(137);
		  newPatientFeatures[2].setFeatureValue(40);
		  newPatientFeatures[3].setFeatureValue(35);
		  newPatientFeatures[4].setFeatureValue(168);
		  newPatientFeatures[5].setFeatureValue(43.1);
		  newPatientFeatures[6].setFeatureValue(2.288);
		  newPatientFeatures[7].setFeatureValue(33);
		  newPatientFeatures[8].setFeatureValue(0);
		  
		  newPatient = new Patient(1, "Test", "Patient", newPatientFeatures); 
		  
		  newSample = Sample.getSample(1); 
		  newSample.addToPatientSample(newPatient); 
		  cleanSamples = new Sample[] { newSample }; 
		  randomForest = new RandomForest(5);
	}
	
	@Test
	public void testMakeVote() {
		randomForest.createForest(cleanSamples); 
		TreeNode node = new TreeNode(cleanSamples); 
		boolean result = randomForest.makeVote(node);  
		assertFalse(result);
	}
	
	@Test
	public void testCreateForest() {
		randomForest.createForest(cleanSamples); 
		assertEquals(5, randomForest.getForest().length); 
		assertNotNull(randomForest.getForest()[0]);
		assertNotNull(randomForest.getForest()[1]);
		assertNotNull(randomForest.getForest()[2]);
		assertNotNull(randomForest.getForest()[3]);
		assertNotNull(randomForest.getForest()[4]);
	}
	
	@Test
	public void testChooseRandomSample() { 
		Sample randomSample = randomForest.chooseRandomSample(cleanSamples); 
		assertNotNull(randomSample); 
	}
}
