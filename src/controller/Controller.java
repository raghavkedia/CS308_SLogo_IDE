package controller;

import java.util.Properties;

import backend.*;
import backend.Character;
import exceptions.SlogoError;
import frontend.FrontendManager;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

public class Controller {
	private FrontendManager myFrontend;
	private InterpreturInterface myBackend;
	
	public Controller(Properties GUIProp, Properties myProp, Stage s){
		myBackend = new BackendManager();
		myFrontend = new FrontendManager(GUIProp, myProp, s, myBackend, this);
	}
	
	public FrontendManager getNewFrontend(Properties GUIProp, Properties myProp, Stage s) {
		return new FrontendManager(GUIProp, myProp, s, myBackend, this);
	}
	//FRONTEND METHODS
	
	//CONSOLE
    public void passConsoleInput(String s){
    	String output = null;
    	try {
			output = myBackend.executeCommand(s);
			myFrontend.resetHistoryPointer();
		} catch (SlogoError e) {
			// TODO Auto-generated catch block
			output = e.getMessage();
			//e.printStackTrace();
		}
    	
    	if (output != null){
    		myFrontend.showOutput(output);
    	}
    }
    
    public void displayInConsole(String input){
    	myFrontend.displayInConsole(input);
    }
    
	public void clearConsole() { myFrontend.clearConsole(); }
	
	public void executeConsole() {myFrontend.executeConsole();}
    
    //VARIABLES
    public void addToVariables(String s){
    	 myFrontend.addToVariables(s);
    }
    
    public void clearVariablesFrontend(){
    	myFrontend.clearAllVars();
    }
    
    public void updateVariableValue(String var, String value){
    	try {
			myBackend.executeCommand("make :"+var + " " + value);
		} catch (SlogoError e) {
			// TODO Auto-generated catch block
			myFrontend.showOutput(e.getMessage());
		}
    }
    
    //HISTORY
    public void clearHistoryFrontend(){
    	myFrontend.clearHistory();
    }
    
    public void addToHistory(String s){
    	myFrontend.addToHistory(s);
    }
    
    // DISPLAY
    public void drawLine(double x1, double y1, double x2, double y2){
    	myFrontend.drawLine(x1, y1, x2, y2);
    }
	
    public void changeDisplayBackgroundColor(Color c){
    	myFrontend.changeBackgroundColor(c);
    }
    
    public void changeLineColor(Color c){
    	myFrontend.setLineColor(c);
    }
    
    public void addPortrait(Character c){
    	myFrontend.addPortrait(c);
    }

    public void clearCharactersFromFrontend(){
    	myFrontend.clearCharacters();
    }
    
    //MISC
    public String getGUIProperty(String s) {
		return myFrontend.getGUIProperty(s);
	}
    
    public Stage getMyWindow(){
    	return myFrontend.getMyWindow();
    }
    
    //BACKEND
    public void addNewChar(Character c){
    	myBackend.getCharacterList().addCharacter(c);
    }
    
    //GETTERS AND SETTERS
    public FrontendManager getFrontendManager() {return this.myFrontend; }
}
