package backend;

import backend.ExpressionNodeFactory.NodeType;

public class Token {
	private NodeType myNodeType;
	private String myCommand;
	private double myValue;
	
	public Token(NodeType myNodeType, String myCommand, double myValue) {
		this.myNodeType = myNodeType;
		this.myCommand = myCommand;
		this.myValue = myValue;
	}

	public NodeType getMyNodeType() {
		return myNodeType;
	}
	
	public String getMyCommand() {
		return myCommand;
	}
	
	public double getValue() {
		return myValue;
	}
	
	

}
