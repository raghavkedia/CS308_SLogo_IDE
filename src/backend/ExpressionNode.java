package backend;

public interface ExpressionNode {
	
	public double execute();
	
	public int currentNumChildren();
	
	public Command getMyCommandType();
	
	public void addChild(ExpressionNode n);

}
