package backend;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.ResourceBundle;

public class SimpleSplitParse implements Parseable {

	public SimpleSplitParse() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public ParsedInput parse(String input) {
		// TODO Auto-generated method stub
		return null;
	}
	
	private Collection<String> cleanStrings(Collection<String> myStrings) {
		boolean foundComment = false;
										   
	}

	@Override
	public Collection<String> stringParse(String input) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String runInput(String input, CharactersList myCharactersList, VariablesList myVariablesList, ResourceBundle myResources) {
		// TODO Auto-generated method stub
		Collection<String> myStrings = new ArrayList<String>(Arrays.asList(input.split("\\s+")));
		return null;
	}

}
