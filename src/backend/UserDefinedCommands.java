package backend;

import java.util.HashMap;
import java.util.Map;

public class UserDefinedCommands {

	private Map<String, String> myUserDefinedCommands;
	
	public UserDefinedCommands() {
		// TODO Auto-generated constructor stub
		myUserDefinedCommands = new HashMap<String, String>();
	}
	
	public Map<String, String> getUserDefinedCommands(){
		return myUserDefinedCommands;
	}
	
	public void addUserDefinedCommand(String id, String command){
		myUserDefinedCommands.put(id, command);
	}
	
	public String getCommand(String id){
		return myUserDefinedCommands.get(id);
	}

}
