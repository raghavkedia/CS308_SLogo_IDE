package backend;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;

public class SimpleSplitParse implements Parseable {

	public SimpleSplitParse() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public ParsedInput parse(String input) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Collection<String> stringParse(String input) {
		Collection<String> myStrings = new ArrayList<String>(Arrays.asList(input.split("\\s+")));
		return null;
	}
	
	private Collection<String> cleanStrings(Collection<String> myStrings) {
		boolean foundComment = false;
										   
	}

}
