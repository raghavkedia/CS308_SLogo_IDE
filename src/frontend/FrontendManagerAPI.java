package frontend;

import javafx.scene.paint.Color;

public class FrontendManagerAPI {
	private static FrontendManager myManager;
	
	public FrontendManagerAPI(FrontendManager f){
		myManager = f;
	}
	
	//CONSOLE
	public static void passConsoleInput(String s){
		myManager.passConsoleInput(s);
    }
	
	public static void clearConsole() {
		myManager.clearConsole();
	}
	
	public static void displayInConsole(String input){
		myManager.displayInConsole(input);
	}
	
	public static void executeConsole() {
		myManager.executeConsole();
	}
    
	//VARIABLES
    public static void addToVariables(String s){
    	myManager.addToVariables(s);
    }
    
    public static void clearVariables(){
    	myManager.clearVariables();
    }
    
    public static void updateVariableValue(String var, double value){
    	myManager.updateVariableValue(var, value);
    }
    
    //HISTORY
    
    public static void clearHistory(){
    	myManager.clearHistory();
    }
    
    public static void addToHistory(String s){
    	myManager.addToHistory(s);
    }
    
    //DISPLAY
    public static void drawLine(double x1, double y1, double x2, double y2){
    	myManager.drawLine(x1, y1, x2, y2);
    }
	
    public static void changeDisplayBackgroundColor(Color c){
    	myManager.changeDisplayBackgroundColor(c);
    }
    
    public static String getGUIProperty(String key) {
    	return myManager.getGUIProperty(key);
    }
}
