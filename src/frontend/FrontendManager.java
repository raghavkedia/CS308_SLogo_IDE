package frontend;

import backend.*;
import controller.Controller;
import backend.data.Character;
import exceptions.SlogoError;
import frontend.toobar.ToolbarComponent;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Orientation;
import javafx.scene.*;
import javafx.scene.control.Button;
import javafx.scene.control.Hyperlink;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.control.SplitPane;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.BorderPane;
import javafx.scene.paint.Color;
import javafx.stage.*;
import java.util.*;

public class FrontendManager {
	public static final int SIZE = 700;

    private Scene myScene;
    private BorderPane myRoot;
//    private List<VisualComponent> myComponents;
//	private static InterpreturInterface myBackend;
	
	private Display myDisplay;
	private History myHistory;
	private Variables myVariables;
	private Console myConsole, myOutput;
	private List<Portrait> myPortraits;
	private Portrait currentPortrait; // all commands typed to the console will be executed on this portrait
	private Button myRunButton;
	private Observer myHistoryObserver;
	private Observer myVariablesObserver;
	private Observer myCharactersObserver;
	private Observer myUDCObserver;
	private Controller myController;
	private int myWorkspaceId;
	private UDC myUDC;
	private AllCharactersList myCharactersList;
	
	public FrontendManager(InterpreturInterface backend, Controller c, int id){
		System.out.println(id);
//		myBackend = new BackendManager();
		myController = c;
		myWorkspaceId = id;
		myRoot = new BorderPane();
		myScene = new Scene(myRoot, Color.WHITE);
//		myComponents = new ArrayList<VisualComponent>();
		myRoot.setPrefSize(1000, 500);
		initObserver(backend);
		initComponents();
	}
	
	public void initComponents(){
		myDisplay = ComponentFactory.makeNewDisplay(500, 500, myController);
		
		myConsole = ComponentFactory.makeNewConsole(1000, 150, myController);

		myHistory = ComponentFactory.makeNewHistory(250, 450, myController);
		
		myOutput = ComponentFactory.makeNewConsole(50, 50, myController);
		
		myVariables = ComponentFactory.makeNewVariables(250, 450, myController);
		
		myUDC = ComponentFactory.makeNewUDC(250, 450, myController);
		
		myCharactersList = ComponentFactory.makeNewActiveCharacterList(250, 450, myController);
		
//		myToolbar = ComponentFactory.makeNewToolbar(myGUIProp, myController);
		
		myPortraits = new ArrayList<Portrait>();

		
		myRoot.setCenter(myDisplay.getVisual());
		myRoot.setBottom(myConsole.getVisual());
		

//		myRoot.setLeft(myUDC.getVisual());
//		myRoot.setTop(myToolbar.getVisual());


        //TODO: REFACTOR BELOW
		SplitPane historyAndVars = new SplitPane();
		historyAndVars.setOrientation(Orientation.VERTICAL);
		historyAndVars.getItems().addAll(myHistory.getVisual(), myVariables.getVisual());
		myRoot.setRight(historyAndVars);

		SplitPane UDCandACL = new SplitPane();
		UDCandACL.setOrientation(Orientation.VERTICAL);
		UDCandACL.getItems().addAll(myUDC.getVisual(), myCharactersList.getVisual());
		myRoot.setLeft(UDCandACL);
//		myRoot.setLeft(myVariables.getVisual());              

//		myPortraiteStateOuput = ComponentFactory.makeNewConsole(200, 200, myController);
        SplitPane splitPane1 = new SplitPane();
        splitPane1.setOrientation(Orientation.VERTICAL);
//      splitPane1.setPrefSize(200, 200);

//        splitPane1.getItems().addAll(myOutput.getVisual(), myPortraiteStateOuput.getVisual());
         
        SplitPane splitPane2 = new SplitPane();
        splitPane2.setOrientation(Orientation.HORIZONTAL);
//      splitPane2.setPrefSize(300, 200);

        splitPane2.getItems().addAll(myConsole.getVisual(), myOutput.getVisual());
       
        myRoot.setBottom(splitPane2);
	}
	
	/**
	 * Set up the observers for the backend side of the components.
	 */
	public void initObserver(InterpreturInterface backend){
		myCharactersObserver = new CharacterListObserver(backend.getCharacterList(myWorkspaceId), myController);
		myHistoryObserver = new HistoryListObserver(backend.getCommandHistory(myWorkspaceId), myController);
		myVariablesObserver = new VariableListObserver(backend.getVariablesList(myWorkspaceId), myController);
		myUDCObserver = new UDCObserver(backend.getUserDefinedCommands(myWorkspaceId), myController);
	}
	   
    //METHODS
    
    //HISTORY
    
    public void resetHistoryPointer(){
    	myHistory.resetHistoryPointer();
    }
    
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
    	myVariables.addToVariables(s);
    }
    
    public void clearAllVars(){
    	myVariables.clearAll();
    }
    
    //HISTORY
    public void clearHistory(){
    	myHistory.getMyData().clear();
    }
    
    public void addToHistory(String s){
    	myHistory.getMyData().add(s);
    }
    
    //DISPLAY
    public void drawLine(double x1, double y1, double x2, double y2){
    	myDisplay.drawLine(x1, y1, x2, y2);
    }
    
    public void changeBackgroundColor(Color c){
    	myDisplay.setBackgroundColor(c);
    }
    
    public void setLineColor(Color c){
    	myDisplay.setLineColor(c);
    }
    
    public void addPortrait(Character c){
    	Portrait p = new Portrait(c);
    	myDisplay.addPortrait(p);
//    	myDisplay.addImage(p.getMyPortrait(), c.getCoordX(), c.getCoordY(), c.getMyAngle(), c.getPenState());
//    	myDisplay.addImage(p, c.getCoordX(), c.getCoordY(), c.getMyAngle(), c.getPenState());
//    	myPortraiteStateOuput.setText("my x : " + c.getCoordX() + ", my y : " + c.getCoordY()+ ", myAngle :" +  c.getMyAngle());
    }
    
    public void clearCharacters(){
    	myDisplay.clearChars();
    }
    

    //USER DEFINED COMMANDS
    public void clearUDC(){myUDC.getMyData().clear();}
    public void addToUDC(String s){myUDC.getMyData().add(s);}
    
    //ALL CHARACTER LIST
    public void clearAllChars(){ myCharactersList.clearAll();}
    public void addChar(Character c){ 
//    	String id = "myID: " + c.getName();
//    	String xcord = ", x: " +  c.getCoordX();
//    	String ycord = ", y: " +  c.getCoordY();
//    	String angle = ", angle: " +  c.getMyAngle();
    	String name = c.getName();
    	myCharactersList.addToAllChars(name); 
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
	public Scene getMyScene(){ return this.myScene;}
	public BorderPane getMyBorderPane() {return this.myRoot;}
	public int getId(){ return this.myWorkspaceId; }
	
}