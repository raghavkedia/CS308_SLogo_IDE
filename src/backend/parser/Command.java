package backend.parser;

import java.util.ResourceBundle;

public enum Command {
	FORWARD, BACK, LEFT, RIGHT, SETHEADING, SETTOWARDS, 
	SETPOSITION, PENDOWN, PENUP, SHOWTURTLE, HIDETURTLE, 
	HOME, CLEARSCREEN, XCOORDINATE, YCOORDINATE, HEADING, 
	ISPENDOWN, ISSHOWING, SUM, DIFFERENCE, PRODUCT, QUOTIENT, 
	REMAINDER, MINUS, RANDOM, SINE, COSINE, TANGENT, ARCTANGENT, 
	NATURALLOG, POWER, PI, LESSTHAN, GREATERTHAN, EQUAL, NOTEQUAL, 
	AND, OR, NOT, MAKEVARIABLE, REPEAT, DOTIMES, FOR, IF, IFELSE, 
	MAKEUSERINSTRUCTION, VARIABLE, CONSTANT, LISTSTART, LISTEND, 
	USERCOMMAND, ASK, ASKWITH, ID, TELL, TURTLES, CLEARSTAMPS, 
	GETPENCOLOR, GETSHAPE, SETBACKGROUND, SETPALETTE, SETPENCOLOR, 
	SETPENSIZE, SETSHAPE, STAMP;
	
	public static final String DEFAULT_RESOURCE_PACKAGE = "resources/";
	private ResourceBundle myCommandResources = ResourceBundle.getBundle(DEFAULT_RESOURCE_PACKAGE + "CommandArgument");
	
	public int numArgs() {
		return Integer.valueOf(myCommandResources.getString(this.toString()));
	}
}

