package backend;

import java.util.Collection;

public interface Parseable {
	
	public ParsedInput parse(String input);
	public Collection<String> stringParse(String input);
	
}
