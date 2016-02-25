package backend;

import backend.BinaryCommandNode;
import backend.Command;
import backend.ConstantNode;

public class testExpressionTree {

	public testExpressionTree() {

	}
	
	public static void main(String[] args) {
		BinaryCommandNode root = new BinaryCommandNode(Command.PRODUCT);
		root.leftChild = new BinaryCommandNode(Command.SUM);
		BinaryCommandNode leftleftChild = (BinaryCommandNode) root.leftChild;
		root.rightChild = new ConstantNode(1);
		ConstantNode c = new ConstantNode(2);
		leftleftChild.leftChild = c;
		leftleftChild.rightChild = new ConstantNode(1);
		Result result = root.execute();
		System.out.println(result.getMyDouble());
	}

}
