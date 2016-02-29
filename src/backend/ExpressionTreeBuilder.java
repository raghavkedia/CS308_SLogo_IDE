package backend;

import java.util.Collection;

public interface ExpressionTreeBuilder {
	
	public double executeExpressions(Collection<ExpressionNode> myNodes);
}
