package backend;

public class Variable {
	
	private String myName;
	private String myValue;
	
	public Variable(String name, String value) {
		// TODO Auto-generated constructor stub
		setVariableName(name);
		setVariableValue(value);
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
