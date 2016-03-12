package backend.parser;

import java.util.List;

public interface ExpressionTreeBuilder {
	
	public double executeExpression(List<String> myStrings) throws Exception;
}
