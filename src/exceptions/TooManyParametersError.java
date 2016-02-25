package exceptions;

public class TooManyParametersError extends InvalidParametersError{
	
	private static final String errorMessage = "Too few parameters ";
	
	public TooManyParametersError() {
		// TODO Auto-generated constructor stub
		super(errorMessage);
	}

}
