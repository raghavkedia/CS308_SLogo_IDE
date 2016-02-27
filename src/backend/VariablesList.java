package backend;

import java.util.HashMap;
import java.util.Map;
import java.util.Observable;

public class VariablesList extends Observable{
	
	private Map<String, Variable> variables;
	
	public VariablesList() {
		variables = new HashMap<String, Variable>();
		// TODO Auto-generated constructor stub
	}
	
	public void addVariable(Variable var){
		variables.put(var.getVariableName(), var);
		setChanged();
		notifyObservers(var);
	}
	
	public Map<String, Variable> getVariables(){
		return variables;
	}
	
	public Variable getVariable(String key){
		return variables.get(key);
	}
}
