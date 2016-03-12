package frontend;

import backend.*;
import controller.Controller;
import backend.data.Character;
import exceptions.SlogoError;
import frontend.listVisual.AllCharactersList;
import frontend.listVisual.History;
import frontend.listVisual.UDC;
import frontend.listVisual.Variables;
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
		myRoot.setPrefSize(1000, 700);
		initObserver(backend);
		initComponents();
		setBorderPane();
	}
	
	private void initComponents(){
		myDisplay = ComponentFactory.makeNewDisplay(500, 500, myController);
		
		myConsole = ComponentFactory.makeNewConsole(1000, 150, myController);

		myHistory = ComponentFactory.makeNewHistory(250, 450, myController);
		
		myOutput = ComponentFactory.makeNewConsole(50, 50, myController);
		
		myVariables = ComponentFactory.makeNewVariables(250, 450, myController);
		
		myUDC = ComponentFactory.makeNewUDC(250, 450, myController);
		
		myCharactersList = ComponentFactory.makeNewActiveCharacterList(250, 450, myController);
//		myPortraiteStateOuput = ComponentFactory.makeNewConsole(200, 200, myController);				
		myPortraits = new ArrayList<Portrait>();
	}
	
	private void setBorderPane() {
		myRoot.setCenter(myDisplay.getVisual());

		Node combinedRight = combineComponent(Orientation.VERTICAL, myHistory.getVisual(), myVariables.getVisual());     		
		myRoot.setRight(combinedRight);

		Node combinedLeft = combineComponent(Orientation.VERTICAL, myUDC.getVisual(), myCharactersList.getVisual());
		myRoot.setLeft(combinedLeft);           

//        SplitPane splitPane1 = new SplitPane();
//        splitPane1.setOrientation(Orientation.VERTICAL);


//      splitPane1.getItems().addAll(myOutput.getVisual(), myPortraiteStateOuput.getVisual());
        
        Node combindedBottom = combineComponent(Orientation.HORIZONTAL, myConsole.getVisual(), myOutput.getVisual());
        myRoot.setBottom(combindedBottom);
	}
	
	//BorderPane Display Helper Methods
	
	private Node combineComponent(Orientation ori, Node n1, Node n2) {
        SplitPane comb = new SplitPane();
        comb.setOrientation(ori);
//      comb.setPrefSize(300, 200);
        comb.getItems().addAll(n1, n2);
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
    
    //CONSOLE
    public void clearConsole(){
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
    	myDisplay.drawLine(x1, y1, x2, y2, charId);
    }
    
    public void changeBackgroundColor(Color c){
    	myDisplay.setBackgroundColor(c);
    }
    
    
    public void addPortrait(Character c){
    	Portrait p = new Portrait(c);
    	myDisplay.addPortrait(p);
    }
    
    public void clearAllLines(){
    	myDisplay.clearAllLines();
    }
    
    public void clearCharacters(){
    	myDisplay.clearChars();
    }
    

    //USER DEFINED COMMANDS
    public void clearUDC(){myUDC.getMyData().clear();}
    public void addToUDC(String s){myUDC.addToData(s);}
    
    //ALL CHARACTER LIST
    public void clearAllChars(){ myCharactersList.clearAll();}
    public void addChar(Character c){ 
//    	String id = "myID: " + c.getName();
//    	String xcord = ", x: " +  c.getCoordX();
//    	String ycord = ", y: " +  c.getCoordY();
//    	String angle = ", angle: " +  c.getMyAngle();
    	String name = c.getName();
    	myCharactersList.addToData(name); 
   }

    public String getBackgroundRGB(){
    	Color c = myDisplay.getColor();
       	String hex = String.format( "#%02X%02X%02X",
                (int)( c.getRed() * 255 ),
                (int)( c.getGreen() * 255 ),
                (int)( c.getBlue() * 255 ) );
    	return hex;
    }

    
    //GETTERS AND SETTERS	
	public BorderPane getMyBorderPane() {return this.myRoot;}
	public int getId(){ return this.myWorkspaceId; }
	
}