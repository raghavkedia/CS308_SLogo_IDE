package backend;

import java.util.ResourceBundle;

public class BackendManager implements InterpreturInterface{
	
	private Parseable myParser;
	private CommandHandlerInterface myCommandHandler;
	//private Result myResult;
	//private ParsedInput myParsedInput;
	//private Querryable myData;
	private CommandHistory myCommandHistory;
	private VariablesList myVariablesList;
	private CharactersList myCharactersList;
    private ResourceBundle myResources;
    public static final String DEFAULT_RESOURCE_PACKAGE = "resources/languages/";
	
	public BackendManager() {
		// TODO Auto-generated constructor stub
		myParser = new SimpleSplitParse();
		myCommandHandler = new CommandHandler();
		//myData = new Data();
		myCommandHistory = new CommandHistory();
		myVariablesList = new VariablesList();
		myCharactersList = new CharactersList();
		//myResources = ResourceBundle.getBundle(DEFAULT_RESOURCE_PACKAGE + "ErrorMessages"); 
	}
	
	public String executeCommand(String input){
		
		myParser.runInput(input, myCharactersList, myVariablesList, myCommandHistory, myResources);
		
		return "";
	}
	
	public Querryable getData(){
		return myData;
	}
	
	//Add two functions. One for sending recieved text from Frontend Manager to myParser, One for sending Result Object to Frontend Manager. 

}

///////
