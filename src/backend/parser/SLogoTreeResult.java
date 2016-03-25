package backend.parser;

public class SLogoTreeResult implements TreeResult{
	private ExpressionNode myTree;
	private double myResult;
	
	public SLogoTreeResult(ExpressionNode myTree, double myResult) {
		this.myTree = myTree;
		this.myResult = myResult;
	}
	
	@Override
	public ExpressionNode getMyTree() {
		return myTree;
	}

	@Override
	public double getMyResult() {
		return myResult;
	}

}
