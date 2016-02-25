package backend;

public class BackendManager implements InterpreturInterface{
	
	private Parseable myParser;
	private CommandHandlerInterface myCommandHandler;
	private Result myResult;
	private ParsedInput myParsedInput;
	private Querryable myData;
	private CommandHistory myCommandHistory;
	private VariablesList myVariablesList;
	private CharactersList myCharactersList;
	
	public BackendManager() {
		// TODO Auto-generated constructor stub
		myParser = new Parser();
		myCommandHandler = new CommandHandler();
		myData = new Data();
		myCommandHistory = new CommandHistory();
		myVariablesList = new VariablesList();
		myCharactersList = new CharactersList();
	}
	
	public String executeCommand(String input){
		
		//make the appropriate calls
		
		return "";
	}
	
	public Querryable getData(){
		return myData;
	}
	
	//Add two functions. One for sending recieved text from Frontend Manager to myParser, One for sending Result Object to Frontend Manager. 

}

///////
