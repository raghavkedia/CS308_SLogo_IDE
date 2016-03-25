package backend.parser;

import java.util.List;

public class SLogoExpressionTrees implements ExpressionTrees {
	private List<TreeResult> myExpressionTree;
	private String input;
	public SLogoExpressionTrees(List<TreeResult> myExpressionTrees, String input) {
		this.myExpressionTree = myExpressionTrees;
		this.input = input;
	}

	@Override
	public List<TreeResult> getExpressionTrees() {
		return myExpressionTree;
	}

	@Override
	public String getString() {
		return input;
	}

}
