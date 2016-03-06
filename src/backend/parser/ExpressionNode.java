package backend.parser;

import java.util.List;

import exceptions.SlogoError;

public interface ExpressionNode {
	
	public double execute() throws SlogoError;
	
	public int currentNumChildren();
	
	public Command getMyCommandType();
	
	public String getMyName();
	
	public List<ExpressionNode> getMyChildren();
	
	public void addChild(ExpressionNode n);

}
