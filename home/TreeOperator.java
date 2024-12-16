package home;

public interface TreeOperator {

	public TreeNode createTree(TreeNode root);
	
	public Double findSplitValue(Sample sample, Feature feature);
	
	public double calculateGiniIndex(TreeNode node); 
	
	public double calculateWeightedGiniIndex(TreeNode leftNode, TreeNode rightNode, int totalOutcomes);
	
	public TreeNode splitNode(TreeNode node, Double splitValue, Feature feature);
	
	public TreeNode createNode(Sample[] sample);
}
