package exceptions;

public class InvalidQuotientError extends LogicalError{
	
	private static final String errorMessage = "Invalid quotient ";
	
	public InvalidQuotientError() {
		// TODO Auto-generated constructor stub
		super(errorMessage);
	}

}
