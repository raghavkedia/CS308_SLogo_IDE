package backend;

import java.util.HashMap;
import java.util.Map;
import java.util.Observable;

public class UserDefinedCommands extends Observable{

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
		setChanged();
		notifyObservers(this);
	}
	
	public String getCommand(String id){
		return myUserDefinedCommands.get(id);
	}

}
