package backend.parser;

import java.util.List;

public interface ExpressionTreeBuilder {
	
	public TreeResult executeExpression(List<String> myStrings) throws Exception;
}
