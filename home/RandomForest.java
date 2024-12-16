package home;

import java.util.Random;

public class RandomForest {

	private static final ViewbeticUI viewbeticUI = ViewbeticUI.createUI(new Home());
	private int treeCount;
	DecisionTree[] forest;
	SampleCleaner cleaner;
	
	public RandomForest(int treeCount) {
		this.treeCount = treeCount;
		this.forest = new DecisionTree[treeCount];
		this.cleaner = new SampleCleaner();
	}
	
	public boolean makeVote(TreeNode node) {
		int yesVotes = 0;
		int noVotes = 0;
		
		for(int i = 0; i < forest.length; i++) {
			boolean prediction = forest[i].makePrediction(node);
			if(prediction) {
				yesVotes++;
				viewbeticUI.setPredictionMessage("Tree " + i + " voted Yes");
			} else {
				noVotes++;
				viewbeticUI.setPredictionMessage("Tree " + i + " voted No");
			}
		}
		
		if(yesVotes > noVotes) {
			return true;
		}
		
		return false;
	}
	
	public void createForest(Sample[] cleanSamples) { 
		System.out.println("Calling create forest");
		for(int i = 0; i < treeCount; i++) {
			
			Sample[] nodeSample = new Sample[cleanSamples.length];
			
			for(int j = 0; j < cleanSamples.length; j++) { 
				nodeSample[j] = chooseRandomSample(cleanSamples);
			}
			TreeNode root = new TreeNode(nodeSample);
			
			System.out.println("New tree created");
			DecisionTree tree = new DecisionTree();
			tree.createTree(root);
				
			forest[i] = tree;	
			viewbeticUI.setPredictionMessage("Tree " + i + " created");
		}
	}
	
	public Sample chooseRandomSample(Sample[] sample) {
		Random random = new Random();
		int randomIndex = random.nextInt(sample.length);
		Sample randomSample = sample[randomIndex];
		return randomSample;
	}
	
	public DecisionTree getTree(DecisionTree[] forest, int treeID) {
		if( treeID < 0 || treeID >= forest.length) {
			System.out.println("No tree available");
		}
		return forest[treeID];
	}
	
	public DecisionTree[] getForest() {
		return forest;
	}
}
