package backend;

import java.util.HashMap;
import java.util.Map;
import java.util.Observable;

public class VariablesList extends Observable{
	
	private Map<String, Integer> variables;
	
	public VariablesList() {
		variables = new HashMap<String, Integer>();
		// TODO Auto-generated constructor stub
	}
	
	public void addVariable(Variable var){
		variables.put(var.getVariableName(), var.getVariableValue());
		setChanged();
		notifyObservers(var);
	}

}
