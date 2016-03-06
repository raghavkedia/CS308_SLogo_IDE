package backend.data;

import java.util.HashMap;
import java.util.Map;
import java.util.Observable;

import backend.parser.ExpressionNode;

public class UserDefinedCommands extends Observable{

	private Map<String, ExpressionNode> myUserDefinedCommands;
	
	public UserDefinedCommands() {
		// TODO Auto-generated constructor stub
		myUserDefinedCommands = new HashMap<String, ExpressionNode>();
	}
	
	public Map<String, ExpressionNode> getUserDefinedCommands(){
		return myUserDefinedCommands;
	}
	
	public void addUserDefinedCommand(String id, ExpressionNode command){
		myUserDefinedCommands.put(id, command);
		setChanged();
		notifyObservers(this);
	}
	
	public ExpressionNode getCommand(String id){
		return myUserDefinedCommands.get(id);
	}
	
	public void removeCommand(String id){
		myUserDefinedCommands.remove(id);
	}
	
}
