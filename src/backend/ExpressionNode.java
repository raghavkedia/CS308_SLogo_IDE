package backend;

public interface ExpressionNode {
	
	public double execute() throws Exception;
	
	public int currentNumChildren();
	
	public Command getMyCommandType();
	
	public void addChild(ExpressionNode n);

}
