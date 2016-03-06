package controller;


import java.util.ArrayList;
import java.util.List;
import java.util.Observer;
import java.util.Properties;

import backend.*;
import backend.data.Character;
import backend.data.Data.PenPattern;
import exceptions.SlogoError;
import frontend.FrontendManager;
import frontend.workspace.WorkSpaceManager;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

public class Controller {
	private FrontendManager myFrontend;
	private InterpreturInterface myBackend;

	private List<FrontendManager> myFrontendList;
//	private ArrayList<InterpreturInterface> myBackendList;
	private int myId;

	private WorkSpaceManager myWSManager;
	private Stage myStage; //Testing, should be deleted later
	
	public Controller(Properties GUIProp, Properties myProp, Stage s){
		myId = 0;
		myBackend = new BackendManager();

		myFrontend = new FrontendManager(GUIProp, myProp, myBackend, this, myId);
		myFrontendList = new ArrayList<FrontendManager>();
		myFrontendList.add(myFrontend);

		myStage = s;
		myWSManager = new WorkSpaceManager(GUIProp, myProp, myBackend, this);

	}
	
	
	//-----------------DEVELOPTING TOOL----------------
	public FrontendManager makeNewFrontend(Properties GUIProp, Properties myProp) {
		FrontendManager newFrontendManager =  new FrontendManager(GUIProp, myProp, myBackend, this, myId);
		myFrontendList.add(newFrontendManager);
		return newFrontendManager;
	}
	
	//FRONTEND METHODS
	
	//WORKSPACE

	public void createWorkSpace() {
		myWSManager.createWorkSpace();
	}
	
	
	//CONSOLE
    public void passConsoleInput(String s){
    	String output = null;
    	try {
			output = myBackend.executeCommand(s, myId);
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
    public void displayInConsole(String input){myFrontend.displayInConsole(input);}
	public void clearConsole() { myFrontend.clearConsole(); }
	public void executeConsole() {myFrontend.executeConsole();}
    
    //VARIABLES
    public void addToVariables(String s){myFrontend.addToVariables(s);}
    public void clearVariablesFrontend(){myFrontend.clearAllVars();}
    
    public void updateVariableValue(String var, String value){
    	try {
			myBackend.executeCommand("make :"+var + " " + value, myId);
		} catch (SlogoError e) {
			// TODO Auto-generated catch block
			myFrontend.showOutput(e.getMessage());
		}
    }
    
    //HISTORY
    public void clearHistoryFrontend(){myFrontend.clearHistory();}
    public void addToHistory(String s){myFrontend.addToHistory(s);}
    
    // DISPLAY
    public void drawLine(double x1, double y1, double x2, double y2){myFrontend.drawLine(x1, y1, x2, y2);}
    public void changeDisplayBackgroundColor(Color c){
    	myFrontend.changeBackgroundColor(c);
    	myBackend.getProperties(myId).setBackgroundColor(c);
    }
    public void changeLineColor(Color c){myFrontend.setLineColor(c);}
    public void addPortrait(Character c){myFrontend.addPortrait(c);}
    public void clearCharactersFromFrontend(){myFrontend.clearCharacters();}
    
    //MISC
    public String getGUIProperty(String s) {
		return myFrontend.getGUIProperty(s);
	}
    
   
    public Stage getMyStage() {
    	return myStage;
    }
    
    //BACKEND
    public void addNewChar(Character c){myBackend.getCharacterList(myId).addCharacter(c);}
    
    public void setPenPattern(PenPattern pattern){myBackend.getProperties(myId).setPenPattern(pattern);}
    public void setPenColor(Color c){myBackend.getProperties(myId).setPenColor(c);}
	public void setLineThickness(double newWidth) {myBackend.getProperties(myId).setPenWidth(newWidth);}
    
	public Color getPenColor(){
		return myBackend.getProperties(myId).getPenColor(); 
	}
	public double getLineThickness(){ return myBackend.getProperties(myId).getPenWidth();}
    public PenPattern getPenPattern(){return myBackend.getProperties(myId).getPenPattern();}
	
    //GETTERS AND SETTERS
    public FrontendManager getFrontendManager() {return this.myFrontend; }
    public WorkSpaceManager getWorkSpaceManager() {return this.myWSManager;}
}
