package backend;

import java.util.ArrayList;
import java.util.List;
import java.util.Observable;
import java.util.Observer;

public class CommandHistory extends Observable{
	
	private List<String> pastCommands;
	
	public CommandHistory() {
		// TODO Auto-generated constructor stub
		pastCommands = new ArrayList<String>();
	}
	
	public void addCommand(String command){
		pastCommands.add(command);
		setChanged();
		notifyObservers(command);
	}
	
}
