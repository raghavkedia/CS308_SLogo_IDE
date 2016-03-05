package backend.parser;

import backend.parser.ExpressionNodeFactory.NodeType;

public class Token {
	private NodeType myNodeType;
	private Command myCommand;
	private double myValue;
	private String myName;
	
	public Token(NodeType myNodeType, Command myCommand, String myName, double myValue) {
		this.myNodeType = myNodeType;
		this.myCommand = myCommand;
		this.myValue = myValue;
		this.myName = myName;
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

	public String getMyName() {
		return myName;
	}

	public void setMyName(String myName) {
		this.myName = myName;
	}
	
	

}
