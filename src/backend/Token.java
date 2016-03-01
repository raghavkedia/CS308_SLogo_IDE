package backend;

import backend.ExpressionNodeFactory.NodeType;

public class Token {
	private NodeType myNodeType;
	private Command myCommand;
	private double myValue;
	
	public Token(NodeType myNodeType, Command myCommand, double myValue) {
		this.myNodeType = myNodeType;
		this.myCommand = myCommand;
		this.myValue = myValue;
	}

	public NodeType getMyNodeType() {
		return myNodeType;
	}
	
	public Command getMyCommand() {
		return myCommand;
	}
	
	public double getValue() {
		return myValue;
	}
	
	

}
