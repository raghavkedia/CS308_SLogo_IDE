// This entire file is part of my masterpiece.
// Raghav Kedia

//For my masterpiece I implemented reflection in our CommandFactory. This class meant to contains
//all the different Math operations. For now it only contains a handful just for demonstration. 

package backend.Commands;

public class MathOperation {

	public MathOperation() {
		// TODO Auto-generated constructor stub
	}
	
	public double sum(double a, double b){
		return a + b;
	}
	
	public double difference(double a, double b){
		return a - b;
	}
	
	public double product(double a, double b){
		return a*b;
	}
	
}
