package backend;

import backend.BinaryCommandNode;
import backend.Command;
import backend.ConstantNode;

public class testExpressionTree {

	public testExpressionTree() {

	}
	
	public static void main(String[] args) {
		CommandNode root = new CommandNode(Command.Product);
		CommandNode newChild = new CommandNode(Command.Cosine);
		root.addChild(newChild);
		root.addChild(new ConstantNode(1));
		newChild.addChild(new ConstantNode(20));
		double result = root.execute();
//		BinaryCommandNode leftleftChild = (BinaryCommandNode) root.leftChild;
//		root.rightChild = new ConstantNode(1);
//		ConstantNode c = new ConstantNode(2);
//		leftleftChild.leftChild = c;
//		leftleftChild.rightChild = new ConstantNode(1);
//		Result result = root.execute();
		System.out.println(result);
	}

}
