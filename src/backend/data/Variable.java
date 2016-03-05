package backend.data;

import java.util.Observable;

public class Variable extends Observable{
	
	private String myName;
	private String myValue;
	
	public Variable(String name, String value) {
		// TODO Auto-generated constructor stub
		setVariableName(name);
		setVariableValue(value);
	}
	
	public void hasUpdated(){
		setChanged();
		notifyObservers();
	}

	public String getVariableName() {
		return myName;
	}

	public void setVariableName(String myName) {
		this.myName = myName;
	}

	public String getVariableValue() {
		return myValue;
	}

	public void setVariableValue(String myValue) {
		this.myValue = myValue;
	}

}
