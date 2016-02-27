package backend;

public class Variable {
	
	private String myName;
	private Integer myValue;
	
	public Variable(String name, Integer value) {
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

	public Integer getVariableValue() {
		return myValue;
	}

	public void setVariableValue(Integer myValue) {
		this.myValue = myValue;
	}

}
