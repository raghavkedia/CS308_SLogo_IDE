package backend;

public class TurtleUnaryCommandNode implements ExpressionNode {
	Command type;
	ExpressionNode child;
	Result result;
	
	public TurtleUnaryCommandNode(Command type) {
		this.type = type;
		result = new GeneralResult();
	}

	@Override
	public Result execute() {
		double value = child.execute().getMyDouble();
		//add in other stuff for turtle?
		switch(type) {
			case FORWARD:
				result.setMyDouble(value);
				return result;
			case BACK:
				result.setMyDouble(value);
				return result;
			case LEFT:
				result.setMyDouble(value);
				return result;
			case RIGHT:
				result.setMyDouble(value);
				return result;
			default:
				break;
		}
		return result;
	}

}
