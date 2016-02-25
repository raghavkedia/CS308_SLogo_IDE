package backend;

import backend.BinaryCommandNode;
import backend.Command;
import backend.ConstantNode;

public class testExpressionTree {

	public testExpressionTree() {

	}
	
	public static void main(String[] args) {
		BinaryCommandNode root = new BinaryCommandNode(Command.SUM);
		root.leftChild = new BinaryCommandNode(Command.SUM);
		BinaryCommandNode leftleftChild = (BinaryCommandNode) root.leftChild;
		root.rightChild = new ConstantNode(1);
		leftleftChild.leftChild = new ConstantNode(2);
		leftleftChild.rightChild = new ConstantNode(1);
		Result result = root.execute();
		System.out.println(result.getMyDouble());
	}

}
