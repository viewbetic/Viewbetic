package home;

public class TreeNode{
	
	TreeNode left;
	TreeNode right;
	Feature splitFeature;
	Double splitValue;
	Sample[] samples;
	
	//public TreeNode(){
	//	
	//}
	
	public TreeNode(TreeNode root) {
		this.samples = root.getSample();
	}
	
	TreeNode(Sample[] sample){
		this.samples = sample;
	}
	
	TreeNode(Sample[] sample, Double splitValue){
		this(sample);
		this.splitValue = splitValue;
	}
	
	public TreeNode getLeft() {
		return left;
	}

	public TreeNode getRight() {
		return right;
	}
	
	public void setLeft(TreeNode left) {
		this.left = left;
	}

	public void setRight(TreeNode right) {
		this.right = right;
	}
	
	public Sample[] getSample() {
		return samples;
	}
	
	public Double getSplitValue() {
		return splitValue;
	}
	
	public void setSplitValue(Double splitValue) {
		this.splitValue = splitValue;
	}
	
	public Feature getSplitFeature() {
		return splitFeature;
	}
	
	public void setSplitFeature(Feature splitFeature) {
		this.splitFeature = splitFeature;
	}
}
