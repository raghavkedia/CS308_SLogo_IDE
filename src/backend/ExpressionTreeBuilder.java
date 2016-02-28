package backend;

import java.util.List;

public interface ExpressionTreeBuilder {
	
	public double executeExpressions(List<ExpressionNode> myNodes);
}
