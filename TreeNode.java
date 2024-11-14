package home;

public class TreeNode{
	
	TreeNode left;
	TreeNode right;
	Double splitValue;
	Patient[][] sample;
	
	TreeNode(Patient[][] sample){
		this.sample = sample;
	}
	
	TreeNode(Patient[][] sample, Double splitValue){
		this.sample = sample;
		this.splitValue = splitValue;
	}
	
	public TreeNode(TreeNode root) {
		
	}

	public Patient[][] getSample() {
		return sample;
	}
	
	public Double getSplitValue() {
		return splitValue;
	}
	
	public void setSplitValue(Double splitValue) {
		this.splitValue = splitValue;
	}

	
	
}
