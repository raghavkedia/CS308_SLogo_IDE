package backend;

import java.util.ResourceBundle;

import exceptions.InvalidQuotientError;

public class BackendManager implements InterpreturInterface{
	
	private Parseable myParser;
	private CommandHandlerInterface myCommandHandler;
	//private Result myResult;
	//private ParsedInput myParsedInput
	private CommandHistory myCommandHistory;
	private VariablesList myVariablesList;
	private CharactersList myCharactersList;
    private ResourceBundle myResources;
    private UserDefinedCommands myUserDefinedCommands;
    public static final String DEFAULT_RESOURCE_PACKAGE = "resources/languages/";
    
	
	public BackendManager() {
		// TODO Auto-generated constructor stub
		myParser = new SimpleSplitParse("English");
		myCommandHandler = new CommandHandler();
		//myData = new Data();
		myCommandHistory = new CommandHistory();
		myVariablesList = new VariablesList();
		myCharactersList = new CharactersList();
		myUserDefinedCommands = new UserDefinedCommands();
		//myResources = ResourceBundle.getBundle(DEFAULT_RESOURCE_PACKAGE + "ErrorMessages"); 
	}
	
	public String executeCommand(String input) throws Exception{
		
		myCommandHistory.addCommand(input);
		
		String output = "error";
		try{
			output = myParser.runInput(input, myCharactersList, myVariablesList, myUserDefinedCommands, myResources);
			
			return output;
		}
		
		catch(Exception e){
			return e.getMessage();
		}
	}
	
	public CharactersList getCharacterList(){
		return myCharactersList;
	}
	public VariablesList getVariablesList(){
		return myVariablesList;
	}
	public CommandHistory getCommandHistory(){
		return myCommandHistory;
	}
	
	/*
	 * Suppose you had a function called divide defined as such: 
		
		public double divide(int a, int b) throws InvalidQuotientError{
			
			if(b == 0){
				throw new InvalidQuotientError(myResources.getBundle("corresponding file").getString("key"));
			}
			else return a / b;
		
		}
	
	*/
	
	//Add two functions. One for sending recieved text from Frontend Manager to myParser, One for sending Result Object to Frontend Manager. 

	
}

///////
