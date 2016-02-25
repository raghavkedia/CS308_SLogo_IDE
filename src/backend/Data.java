package backend;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Data implements Querryable{
	
	private List<String> pastCommands;
	private Map<String, Integer> variables;
	private List<Character> myCharacters;
	
	public Data() {
		pastCommands = new ArrayList<String>();
		variables = new HashMap<String, Integer>();
		myCharacters = new ArrayList<Character>();
	}

	public List<String> getCommandHistory() {
		// TODO Auto-generated method stub
		return pastCommands;
	}

	public Map<String, Integer> getVariables() {
		// TODO Auto-generated method stub
		return variables;
	}

	public List<Character> getAllCharacters() {
		// TODO Auto-generated method stub
		return myCharacters;
	}
	
	public void addCharacter(Character c){
		myCharacters.add(c);
	}
	
}
