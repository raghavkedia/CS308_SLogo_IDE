package frontend;

import javafx.scene.paint.Color;

public class FrontendManagerAPI {
	private static FrontendManager myManager;
	
	public FrontendManagerAPI(FrontendManager f){
		this.myManager = f;
	}
	
	public static void passConsoleInput(String s){
    	//call passConsoleInput from Display.java
    	//pass s to backend
    	// get some return result
    	// display that in console.
		myManager.passConsoleInput(s);
    }
    
    public static void addToVariables(String s){
    	// myVariables.addToVariables(s);
    	myManager.addToVariables(s);
    }
    
    public static void updateVariableValue(String var, double value){
    	//backend call
    	myManager.updateVariableValue(var, value);
    }
    
    public static void drawLine(double x1, double y1, double x2, double y2){
    	myManager.drawLine(x1, y1, x2, y2);
    }
	
    public static void changeDisplayBackgroundColor(Color c){
    	myManager.changeDisplayBackgroundColor(c);
    }
}
