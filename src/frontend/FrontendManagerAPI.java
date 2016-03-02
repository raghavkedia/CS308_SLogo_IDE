package frontend;

import javafx.scene.paint.Color;
import javafx.stage.Stage;
import backend.*;
import backend.Character;

public class FrontendManagerAPI {
	private static FrontendManager myManager;
	
	public FrontendManagerAPI(FrontendManager f){
		this.myManager = f;
	}
	
	//CONSOLE
	public static void passConsoleInput(String s){
		myManager.passConsoleInput(s);
    }
	
	public static void displayInConsole(String input){
		myManager.displayInConsole(input);
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
    
    public static void addImage(Character c){
    	myManager.addImage(c);
    }
    
    //DISPLAY
    public static void drawLine(double x1, double y1, double x2, double y2){
    	myManager.drawLine(x1, y1, x2, y2);
    }
	
    public static void changeDisplayBackgroundColor(Color c){
    	myManager.changeDisplayBackgroundColor(c);
    }
    
    //GETTERS, SETTERS, MISC
    public static Stage getMyWindow(){
    	return myManager.getMyWindow();
    }
}
