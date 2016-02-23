package slogo_team09;

public class CommandHandler implements CommandHandlerInterface{
	
	private Result myResult;
	
	public CommandHandler() {
		// TODO Auto-generated constructor stub
	}
	
	//returns Result object;
	public Result handleCommand(ParsedInput parsedInput){
		//for now, in the future we may not need an actual instance variable of Result
		return myResult;
	}
}
