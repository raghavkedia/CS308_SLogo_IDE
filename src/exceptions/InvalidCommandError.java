package exceptions;

public class InvalidCommandError extends SyntaxError{
	
	private static final String errorMessage = "Invalid Command: ";
	
	public InvalidCommandError() {
		// TODO Auto-generated constructor stub
		super(errorMessage);
	}
	

//	public InvalidCommandException() {
//		// TODO Auto-generated constructor stub
//	}

}
