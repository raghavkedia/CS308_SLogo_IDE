package slogo_team09;

import slogo_team09.BinaryCommandNode;
import slogo_team09.Command;
import slogo_team09.ConstantNode;

public class testExpressionTree {

	public testExpressionTree() {

	}
	
	public static void main(String[] args) {
		BinaryCommandNode root = new BinaryCommandNode(Command.SUM);
		root.leftChild = new BinaryCommandNode(Command.LESS);
		BinaryCommandNode leftleftChild = (BinaryCommandNode) root.leftChild;
		root.rightChild = new ConstantNode(1);
		leftleftChild.leftChild = new ConstantNode(2);
		leftleftChild.rightChild = new ConstantNode(1);
		double result = root.execute();
		System.out.println(result);
	}

}
