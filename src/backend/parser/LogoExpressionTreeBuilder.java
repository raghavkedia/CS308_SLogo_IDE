package backend.parser;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.ResourceBundle;
import java.util.Set;
import java.util.Stack;
import java.util.TreeSet;

import exceptions.SlogoError;
import exceptions.TooFewParametersError;

public class LogoExpressionTreeBuilder implements ExpressionTreeBuilder {
	public static final String DEFAULT_RESOURCE_PACKAGE = "resources/";
	private ResourceBundle myErrorResources;
	
	public LogoExpressionTreeBuilder() {
		myErrorResources = ResourceBundle.getBundle(DEFAULT_RESOURCE_PACKAGE + "ErrorMessages");
	}
	
	public double executeExpressions(Collection<ExpressionNode> myNodes) throws SlogoError {
		List<ExpressionNode> myNodeCopies = new ArrayList<ExpressionNode>(myNodes);
		Stack<ExpressionNode> myStack = new Stack<ExpressionNode>();
		ExpressionNode curr = myNodeCopies.get(0);
		List<ExpressionNode> toExecute = new ArrayList<ExpressionNode>();
		double result = 0;
		while (!myNodeCopies.isEmpty() || !myStack.empty()) {
			myNodeCopies.remove(curr);
			if (isSatisfied(curr)) {
				if (myStack.isEmpty()) {
					if (!toExecute.contains(curr)) {
						toExecute.add(curr);
					}
					if(!myNodeCopies.isEmpty()) {
						curr = myNodeCopies.get(0);
					}
				}
				else {
					ExpressionNode parent = myStack.pop();
					parent.addChild(curr);
					curr = parent;
				}
			}
			else {
				myStack.push(curr);
				if(!myNodeCopies.isEmpty()){
					curr = myNodeCopies.get(0);
				}
				else if (myNodeCopies.isEmpty() && !myStack.isEmpty()) {
					throw new TooFewParametersError(myErrorResources.getString("TooFewParameters"));
				}
			}
		}
		if (!toExecute.contains(curr)) {
			toExecute.add(curr);
		}
		for (ExpressionNode node : toExecute) {
			result = node.execute();
		}
		return result;
	}

	private boolean isSatisfied(ExpressionNode node) {
		return node.currentNumChildren() >= node.getMyCommandType().numArgs();
	}
}