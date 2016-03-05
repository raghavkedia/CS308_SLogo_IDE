package backend.data;

import java.util.HashMap;
import java.util.Map;
import java.util.Observable;
import java.util.Observer;

public class VariablesList extends Observable implements Observer{
	
	private Map<String, Variable> variables;
	
	public VariablesList() {
		variables = new HashMap<String, Variable>();
		// TODO Auto-generated constructor stub
	}
	
	public void addVariable(Variable var){
		variables.put(var.getVariableName(), var);
		var.addObserver(this);
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

	@Override
	public void update(Observable o, Object arg) {
		// TODO Auto-generated method stub
		hasUpdated();
	}

}
