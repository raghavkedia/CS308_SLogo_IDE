// This entire file is part of my masterpiece.
// Jiangzhen Yu

package frontend;

import backend.*;
import controller.Controller;
import backend.data.Character;
import exceptions.InvalidCharacterError;
import exceptions.SlogoError;
import frontend.GUI.Init.Dimension;
import frontend.listVisual.AllCharactersList;
import frontend.listVisual.History;
import frontend.listVisual.UDC;
import frontend.listVisual.Variables;
import frontend.observer.CharacterListObserver;
import frontend.observer.HistoryListObserver;
import frontend.observer.PropertiesObserver;
import frontend.observer.UDCObserver;
import frontend.observer.VariableListObserver;
import javafx.geometry.Orientation;
import javafx.scene.Node;
import javafx.scene.control.SplitPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.paint.Color;
import java.util.*;

public class FrontendManager {

    private BorderPane myRoot;
	
	private Display myDisplay;
	private History myHistory;
	private Variables myVariables;
	private Console myConsole, myOutput;
	private List<Portrait> myPortraits;
	private Portrait currentPortrait; // all commands typed to the console will be executed on this portrait
	private Observer myHistoryObserver;
	private Observer myVariablesObserver;
	private Observer myCharactersObserver;
	private Observer myUDCObserver;
	private Observer myPropertiesObserver;
	private Controller myController;
	private int myWorkspaceId;
	private UDC myUDC;
	private AllCharactersList myCharactersList;
	
	public FrontendManager(InterpreturInterface backend, Controller c, int id){
		myController = c;
		myWorkspaceId = id;
		myRoot = new BorderPane();
		initObserver(backend);
		initComponents();
		setBorderPane();
	}
	
	private void initComponents(){
		myDisplay = ComponentFactory.makeNewDisplay(Dimension.DISPLAY.getHeight(), Dimension.DISPLAY.getWidth(), myController);
		myConsole = ComponentFactory.makeNewConsole(Dimension.CONSOLE.getHeight(), Dimension.CONSOLE.getWidth(), myController);
		myHistory = ComponentFactory.makeNewHistory(Dimension.HISTORY.getHeight(), Dimension.HISTORY.getWidth(),myController);
		myOutput = ComponentFactory.makeNewConsole(Dimension.OUTPUT.getHeight(), Dimension.OUTPUT.getWidth(), myController);
		myVariables = ComponentFactory.makeNewVariables(Dimension.VARIABLE.getHeight(), Dimension.VARIABLE.getWidth(),myController);
		myUDC = ComponentFactory.makeNewUDC(Dimension.UDC.getHeight(), Dimension.UDC.getWidth(), myController);
		myCharactersList = ComponentFactory.makeNewActiveCharacterList(Dimension.CHARACTERLIST.getHeight(), Dimension.CHARACTERLIST.getWidth(), myController);				
		myPortraits = new ArrayList<Portrait>();
	}
	
	private void setBorderPane() {
		myRoot.setCenter(myDisplay.getVisual());

		Node combinedRight = combineComponent(Orientation.VERTICAL, myHistory.getVisual(), myVariables.getVisual());     		
		myRoot.setRight(combinedRight);

		Node combinedLeft = combineComponent(Orientation.VERTICAL, myUDC.getVisual(), myCharactersList.getVisual());
		myRoot.setLeft(combinedLeft);           

        Node combindedBottom = combineComponent(Orientation.HORIZONTAL, myConsole.getVisual(), myOutput.getVisual());
        myRoot.setBottom(combindedBottom);
	}
	
	/**
	 * BorderPane Display Helper Methods
	 * @param node: node Var-args that deal with an indeterminate number of javaFx nodes. 
	 */
	
	private Node combineComponent(Orientation ori, Node...node) {
        SplitPane comb = new SplitPane();
        comb.setOrientation(ori);
        comb.getItems().addAll(node);
        return comb;
	}
	
	
	/**
	 * Set up the observers for the backend side of the components.
	 */
	public void initObserver(InterpreturInterface backend){
		myCharactersObserver = new CharacterListObserver(backend.getCharacterList(myWorkspaceId), myController);
		myHistoryObserver = new HistoryListObserver(backend.getCommandHistory(myWorkspaceId), myController);
		myVariablesObserver = new VariableListObserver(backend.getVariablesList(myWorkspaceId), myController);
		myUDCObserver = new UDCObserver(backend.getUserDefinedCommands(myWorkspaceId), myController);
		myPropertiesObserver = new PropertiesObserver(backend.getProperties(myWorkspaceId), myController);
	}
	
    //METHODS
    
    
    //OUTPUT
    
    public void showOutput(String s){
    	myOutput.setText(s);
    }
    
    public void displayInConsole(String s){
    	myConsole.setText(s);
    }
    
    //CONSOLE & OUTPUT
    public void clearOutput(){
    	myOutput.clear();
    }
    
    public void executeConsole(){
    	myConsole.executeInput();
    }
    
    public String getConsoleText() { 
    	return myConsole.getText();
    }
    
    //VARIABLES
    public void addToVariables(String s){
    	myVariables.addToData(s);
    }
    
    public void clearAllVars(){
    	myVariables.clearAll();
    }
    
    //HISTORY
    public void clearHistory(){
    	myHistory.clearAll();
    }
    
    public void addToHistory(String s){
    	myHistory.addToData(s);
    }
    
    //DISPLAY
    public void drawLine(double x1, double y1, double x2, double y2, String charId){
    	try {
			myDisplay.drawLine(x1, y1, x2, y2, charId);
		} catch (InvalidCharacterError e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }
    
    public void changeBackgroundColor(Color c){
    	myDisplay.setBackgroundColor(c);
    }
    
    
    public void addPortrait(Character c) throws InvalidCharacterError{
    	Portrait p = new Portrait(c);
    	myDisplay.addPortrait(p);
    }
    
    public void clearAllLines(){
    	myDisplay.clearAllLines();
    }
    
    public void clearCharacters(){
    	myDisplay.clearChars();
    }
    
    public String getBackgroundRGB(){
    	return myDisplay.getBackgroundRGB();
    }
    

    //USER DEFINED COMMANDS
    public void clearUDC(){myUDC.clearAll();}
    public void addToUDC(String s){myUDC.addToData(s);}
    
    //ALL CHARACTER LIST
    public void clearAllChars(){ myCharactersList.clearAll();}
    
    public void addChar(Character c){ 
    	myCharactersList.addToData(c); 
   }

    public int getNumChar() {
    	return myCharactersList.size();
    }
    
    public Map<Integer, Character> getCharMap() {
    	return  myCharactersList.getCharMap();
    }
    
    //GETTERS AND SETTERS	
	public BorderPane getMyBorderPane() {return this.myRoot;}
	public int getId(){ return this.myWorkspaceId; }
	
}