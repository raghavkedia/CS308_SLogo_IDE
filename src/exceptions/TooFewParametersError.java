package exceptions;

public class TooFewParametersError extends InvalidParametersError{
	
	private static final String errorMessage = "Too few parameters ";
	
	public TooFewParametersError() {
		// TODO Auto-generated constructor stub
		super(errorMessage);
	}

}
