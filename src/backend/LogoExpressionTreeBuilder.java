package backend;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Stack;

public class LogoExpressionTreeBuilder implements ExpressionTreeBuilder {

	public LogoExpressionTreeBuilder() {
	}
	
	public double executeExpressions(Collection<ExpressionNode> myNodes) {
		List<ExpressionNode> myNodeCopies = new ArrayList<ExpressionNode>(myNodes);
		checkForBrackets(myNodeCopies);
		Stack<ExpressionNode> myStack = new Stack<ExpressionNode>();
		ExpressionNode curr = myNodeCopies.get(0);
		double result = 0;
		while (!myNodeCopies.isEmpty()) {
			myNodeCopies.remove(curr);
			if (isSatisfied(curr)) {
				if (myStack.isEmpty()) {
					result = curr.execute();
					curr = myNodeCopies.get(0);
				}
				else {
					ExpressionNode parent = myStack.pop();
					parent.addChild(curr);
					curr = parent;
				}
			}
			else {
				myStack.push(curr);
				curr = myNodeCopies.get(0);
			}
		}
		result = curr.execute();
		return result;
	}
	
	private void checkForBrackets(List<ExpressionNode> myNodes) {
		List<ExpressionNode> toRemove = new ArrayList<ExpressionNode>();	
		boolean unfinished = true;
		boolean foundForwardBracket = false;
		boolean foundBackwardBracket = false;
		while (unfinished) {
			unfinished = false;
			for (ExpressionNode node : myNodes) {
				if (node instanceof ForwardBracketNode) {
					foundForwardBracket = true;
					int index =  myNodes.indexOf(node);
					//check for errors with indexing
					for (int k = index -1; k < myNodes.size(); k++) {
						ExpressionNode child = myNodes.get(k);
						if (!(child instanceof BackBracketNode)) {
							node.addChild(child);
							toRemove.add(child);
						}
						else {
							unfinished = true;
							foundBackwardBracket = true;
							toRemove.add(child);
							break;
						}
					}
				}
				if (foundForwardBracket != foundBackwardBracket) {
					//throw exception
				}
			}
			myNodes.removeAll(toRemove);
		}
		
	}

	private boolean isSatisfied(ExpressionNode node) {
		return node.currentNumChildren() == node.getMyCommandType().numArgs();
	}
}