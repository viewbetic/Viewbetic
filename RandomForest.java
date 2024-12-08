package home;

public class RandomForest {

	private int treeCount;
	private int sampleSize;
	DecisionTree[] forest;
	SampleCleaner cleaner;
	
	public RandomForest(int treeCount, int sampleSize) {
		this.treeCount = treeCount;
		this.sampleSize = sampleSize;
		forest = new DecisionTree[treeCount];
		cleaner = new SampleCleaner();
	}
	
	public boolean makeVote(TreeNode node) {
		int yesVotes = 0;
		int noVotes = 0;
		
		for(int i = 0; i < forest.length; i++) {
			boolean prediction = forest[i].makePrediction(node);
			if(prediction) {
				yesVotes++;
			} else {
				noVotes++;
			}
		}
		
		if(yesVotes > noVotes) {
			return true;
		}
		
		return false;
	}
	
	public void createForest(Patient[] patients) { //Fix
		for(int i = 0; i < treeCount; i++) {
			
			Sample[] cleanSamples = new Sample[sampleSize];
			for(int j = 0; j < sampleSize; j++) { 
				Sample randomSample = new Sample(patients.length);
				
				for(int k = 0; k < sampleSize; k++) { 
					Patient sampledPatient = randomSample.chooseRandomPatient(patients);
					randomSample.addToPatientSample(sampledPatient);
				}
				
				cleanSamples[j] = cleaner.createCleanSample(randomSample);
			}
			TreeNode t = new TreeNode(cleanSamples);
				
			DecisionTree tree = new DecisionTree();
			tree.createTree(t);
				
			forest[i] = tree;	
		}
	}
}
