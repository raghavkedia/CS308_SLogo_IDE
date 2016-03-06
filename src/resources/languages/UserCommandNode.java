package resources.languages;

import java.util.ArrayList;
import java.util.List;

import backend.data.Variable;
import backend.parser.Command;
import backend.parser.ExpressionNode;
import exceptions.SlogoError;

public class UserCommandNode implements ExpressionNode {
	private List<Variable> myParameters;
	private ExpressionNode myTree;
	List<ExpressionNode> myChildren;
	String myName;
	
	public UserCommandNode(String myName) {
		myParameters = new ArrayList<Variable>();
		myChildren = new ArrayList<ExpressionNode>();
		this.myName = myName;
	}

	@Override
	public double execute() throws SlogoError {
		return myTree.execute();
	}

	@Override
	public int currentNumChildren() {
		
		return 0;
	}

	@Override
	public Command getMyCommandType() {
		return Command.UserCommand;
	}

	@Override
	public String getMyName() {
		return myName;
	}

	@Override
	public List<ExpressionNode> getMyChildren() {
		return null;
	}

	@Override
	public void addChild(ExpressionNode n) {
		myChildren.add(n);
	}
	
	public void addParameter(Variable v) {
		myParameters.add(v);
	}
	
	public void setMyTree(ExpressionNode n) {
		this.myTree = n;
	}

}
