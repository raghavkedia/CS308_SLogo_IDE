package backend.parser;

import exceptions.SlogoError;

public interface ExpressionNode {
	
	public double execute() throws SlogoError;
	
	public int currentNumChildren();
	
	public Command getMyCommandType();
	
	public void addChild(ExpressionNode n);

}
