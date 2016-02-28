package backend;

public enum Command {
	Forward, Back, Left, Right, SetHeading, SetTowards, SetPosition, PenDown, PenUp, ShowTurtle, HideTurtle, Home, ClearScreen,
	XCoordinate, YCoordinate, Heading, IsPenDown, IsShowing, Sum, Difference, Product, Quotient, Remainder, Minus, Random, 
	Sine, Cosine, Tangent, ArcTangent, NaturalLog, Power, Pi, LessThan, GreaterThan, Equal, NotEqual, And, Or, Not;

	public int numArgs(Command type) {
		switch(type) {
			case Forward:
				return 1;
			case Back:
				return 1;
			case Left:
				return 1;
			case Right:
				return 1;
			case SetHeading:
				return 1;
			case SetTowards:
				return 2;
			case SetPosition:
				return 2;
			case PenDown:
				return 0;
			case PenUp:
				return 0;
			case ShowTurtle:
				return 0;
			case HideTurtle:
				return 0;
			case Home:
				return 0;
			case ClearScreen:
				return 0;
			case XCoordinate:
				return 0;
			case YCoordinate:
				return 0;
			case Heading:
				return 0;
			case IsPenDown:
				return 0;
			case IsShowing:
				return 0;
			case Sum:
				return 2;
			case Difference:
				return 2;
			case Product:
				return 2;
			case Quotient:
				return 2;
			case Remainder:
				return 2;
			case Minus:
				return 1;
			case Random:
				return 1;
			case Sine:
				return 1;
			case Cosine:
				return 1;
			case Tangent:
				return 1;
			case ArcTangent:
				return 1;
			case NaturalLog:
				return 1;
			case Power:
				return 2;
			case Pi:
				return 0;
			case LessThan:
				return 2;
			case GreaterThan:
				return 2;
			case Equal:
				return 2;
			case NotEqual:
				return 2;
			case And:
				return 2;
			case Or:
				return 2;
			case Not:
				return 0;
			default:
				break;
		}
		return 0;
	}
}

