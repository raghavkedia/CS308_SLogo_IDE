package backend;

import java.util.HashMap;
import java.util.Map;
import java.util.Observable;
import java.util.Observer;

public class VariablesList extends Observable{
	
	private Map<String, Variable> variables;
	
	public VariablesList() {
		variables = new HashMap<String, Variable>();
		// TODO Auto-generated constructor stub
	}
	
	public void addVariable(Variable var){
		variables.put(var.getVariableName(), var);
	}
	
	public void hasUpdated(){
		setChanged();
		notifyObservers();
	}
	
	public Map<String, Variable> getVariables(){
		return variables;
	}
	
	public Variable getVariable(String key){
		return variables.get(key);
	}

}
