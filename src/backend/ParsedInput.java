package backend;

import java.util.List;

public abstract class ParsedInput {

	private String myCommand;
	private List<String> myArgs;
	public String getMyCommand() {
		return myCommand;
	}
	public void setMyCommand(String myCommand) {
		this.myCommand = myCommand;
	}
	public List<String> getMyArgs() {
		return myArgs;
	}
	public void setMyArgs(List<String> myArgs) {
		this.myArgs = myArgs;
	}

}
