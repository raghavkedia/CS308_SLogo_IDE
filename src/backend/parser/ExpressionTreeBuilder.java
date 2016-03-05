package backend.parser;

import java.util.Collection;

public interface ExpressionTreeBuilder {
	
	public double executeExpressions(Collection<ExpressionNode> myNodes) throws Exception;
}
