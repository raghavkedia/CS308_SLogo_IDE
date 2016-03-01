package frontend;

import backend.*;
import javafx.scene.*;
import javafx.scene.control.Hyperlink;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.BorderPane;
import javafx.scene.paint.Color;
import javafx.stage.*;
import java.util.*;

public class FrontendManager {
	public static final int SIZE = 700;
    
    private Scene myScene;
    private BorderPane myRoot;
    private Stage myWindow;
    private List<VisualComponent> myComponents;
	private static BackendManager myBackend;
	
	private Display myDisplay;
	private History myHistory;
	private Variables myVariables;
	private Console myConsole;
	private ToolbarComponent myToolbar;
	private Properties myProp, myGUIProp ;
	private List<Portrait> myPortraits;
	private Portrait currentPortrait; // all commands typed to the console will be executed on this portrait
	
	private Observer myHistoryObserver;
	private Observer myVariablesObserver;
	private Observer myCharactersObserver;
	
	FrontendManagerAPI myAPI;
	
	public FrontendManager(Properties GUIProp, Properties myProp){
		myBackend = new BackendManager();
		myRoot = new BorderPane();
		myWindow = new Stage();
		myScene = new Scene(myRoot, 1000, 700, Color.WHITE);
		myComponents = new ArrayList<VisualComponent>();
		myAPI = new FrontendManagerAPI(this);
		myGUIProp = GUIProp;
		initComponents();
		initObserver();
	}
	
	public void initComponents(){
		myDisplay = ComponentFactory.makeNewDisplay(400, 400);
		myComponents.add(myDisplay);
		myConsole = ComponentFactory.makeNewConsole(200, 200);
		myComponents.add(myConsole);

		myHistory = ComponentFactory.makeNewHistory(200, 200);
		myComponents.add(myHistory);
		
		myVariables = ComponentFactory.makeNewVariables(200, 200);
		myComponents.add(myVariables);
		myToolbar = ComponentFactory.makeNewToolbar(myGUIProp);
		myComponents.add(myToolbar);
		
		myPortraits = new ArrayList<Portrait>();

		
		myRoot.setCenter(myDisplay.getVisual());
		myRoot.setRight(myHistory.getVisual());
		myRoot.setBottom(myConsole.getVisual());
		myRoot.setLeft(myVariables.getVisual());
		myRoot.setTop(myToolbar.getVisual());
	}
	
	/**
	 * Set up the observers for the backend side of the components.
	 */
	public void initObserver(){
//		myCharactersObserver = new CharacterListObserver();
		myHistoryObserver = new HistoryListObserver(myBackend.getCommandHistory());
		myVariablesObserver = new VariableListObserver(myBackend.getVariablesList());
	}
	

    private void setupKeyboardCommands () {
        myScene.setOnKeyPressed(e -> {
            if (e.getCode() == KeyCode.ENTER) {
            	//BackendManager.executeCommand();
            }
        });
    }
    
    //CONSOLE
    public void passConsoleInput(String s){
    	String output = null;
    	try {
			output = myBackend.executeCommand(s);
			myHistory.resetHistoryPointer();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	
    	if (output != null){
    		myConsole.setText(output);
    	}
    }
    
    public void displayInConsole(String input){
    	myConsole.setText(input);
    }
    
    //VARIABLES
    public void addToVariables(String s){
    	 myVariables.addToVariables(s);
    }
    
    public void clearVariables(){
    	myVariables.clearAll();
    }
    
    public void updateVariableValue(String var, double value){
    	//backend call
    }
    
    //HISTORY
    public void clearHistory(){
    	myHistory.getMyData().clear();
    }
    
    public void addToHistory(String s){
    	myHistory.getMyData().add(s);
    }
    
    // DISPLAY
    public void drawLine(double x1, double y1, double x2, double y2){
    	myDisplay.drawLine(x1, y1, x2, y2);
    }
	
    public void changeDisplayBackgroundColor(Color c){
    	myDisplay.setBackgroundColor(c);
    }

	public Scene getMyScene(){ return this.myScene;}

	
}
