package backend;

public class Parser implements ParserInterface{
	
	private ParsedInput myParsedInput;
	
	public Parser() {
		// TODO Auto-generated constructor stub
	}
	
	//return ParsedInput Object
	public ParsedInput parseInput(String input){
		//for now, in the future we may not need an actual instance variable of ParsedInput
		return myParsedInput;
	}
}
