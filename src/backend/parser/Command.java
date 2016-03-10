package backend.parser;

public enum Command {
	Forward, Back, Left, Right, SetHeading, SetTowards, SetPosition, PenDown, PenUp, ShowTurtle, HideTurtle, Home, ClearScreen,
	XCoordinate, YCoordinate, Heading, IsPenDown, IsShowing, Sum, Difference, Product, Quotient, Remainder, Minus, Random, 
	Sine, Cosine, Tangent, ArcTangent, NaturalLog, Power, Pi, LessThan, GreaterThan, Equal, NotEqual, And, Or, Not, Variable,
	Constant, ListStart, ListEnd, MakeVariable, Repeat, MakeUserInstruction, IfElse, DoTimes, For, If, UserCommand, ID, Turtles, 
	Tell, Ask, AskWith, SetBackground, SetPenColor, SetPenSize , SetShape , SetPalette , GetPenColor , GetShape ,Stamp ,ClearStamps;
	
	public int numArgs() {
		switch(this) {
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
		case MakeVariable:
			return 2;
		case Repeat:
			return 2;
		case DoTimes: 
			return 2;
		case For:
			return 2;
		case If:
			return 2;
		case IfElse:
			return 3;
		case MakeUserInstruction:
			return 3;
		case Variable:
			return 0;
		case Constant:
			return 0;
		case ListStart:
			return 0;
		case ListEnd:
			return 0;
		case UserCommand:
			return 0;
		case Ask:
			return 2;
		case AskWith:
			return 2;
		case ID:
			return 0;
		case Tell:
			return 1;
		case Turtles:
			return 0;
		case ClearStamps:
			return 0;
		case GetPenColor:
			return 0;
		case GetShape:
			return 0;
		case SetBackground:
			return 1;
		case SetPalette:
			return 4;
		case SetPenColor:
			return 1;
		case SetPenSize:
			return 1;
		case SetShape:
			return 1;
		case Stamp:
			return 0;
		default:
			break;


		}
		return 0;
	}
}

