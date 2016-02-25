package backend;

public class BackendManager implements InterpreturInterface{
	
	private Parseable myParser;
	private CommandHandlerInterface myCommandHandler;
	private Result myResult;
	private ParsedInput myParsedInput;
	
	public BackendManager() {
		// TODO Auto-generated constructor stub
		myParser = new Parser();
		myCommandHandler = new CommandHandler();
	}
	
	public Result executeCommand(String input){
		//This will send user input to parser and get back a ParsedInput Object
		myParsedInput = myParser.parse(input);
		
		//this will send ParsedInput object to CommandHandler and receive Result object
		myResult = myCommandHandler.handleCommand(myParsedInput);
		
		//this will return Result object
		return myResult;
	}
	
	//Add two functions. One for sending recieved text from Frontend Manager to myParser, One for sending Result Object to Frontend Manager. 

}

///////
