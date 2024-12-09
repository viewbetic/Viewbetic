package home;

import java.util.Random;

public class RandomForest {

	private int treeCount;
	DecisionTree[] forest;
	SampleCleaner cleaner;
	
	public RandomForest(int treeCount) {
		this.treeCount = treeCount;
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
	
	public void createForest(Sample[] cleanSamples) { 
		for(int i = 0; i < treeCount; i++) {
			
			Sample[] nodeSample = new Sample[cleanSamples.length];
			
			for(int j = 0; j < cleanSamples.length; j++) { 
				Sample randomSample = chooseRandomSample(cleanSamples);
				nodeSample[j] = randomSample;
			}
			TreeNode t = new TreeNode(nodeSample);
				
			DecisionTree tree = new DecisionTree();
			tree.createTree(t);
				
			forest[i] = tree;	
		}
	}
	
	public Sample chooseRandomSample(Sample[] sample) {
		int randomIndex = new Random().nextInt(sample.length);
		Sample randomSample = sample[randomIndex];
		return randomSample;
	}
}
