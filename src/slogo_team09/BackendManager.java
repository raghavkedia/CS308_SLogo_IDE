package slogo_team09;

public class BackendManager {
	
	private Parser myParser;
	private CommandHandler myCommandHandler;
	private Result myResult;
	private ParsedInput myParsedInput;
	
	public BackendManager() {
		// TODO Auto-generated constructor stub
		myParser = new Parser();
		myCommandHandler = new CommandHandler();
	}
	
	public Result executeCommand(String input){
		myParsedInput = myParser.parseInput(input);
		myResult = myCommandHandler.handleCommand(myParsedInput);
		return myResult;
	}
	
	//Add two functions. One for sending recieved text from Frontend Manager to myParser, One for sending Result Object to Frontend Manager. 

}
