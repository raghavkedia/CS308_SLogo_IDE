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
	private static InterpreturInterface myBackend;
	
	private Display myDisplay;
	private History myHistory;
	private Variables myVariables;
	private Console myConsole;
	private ToolbarComponent myToolbar;
	private Properties myProp;
	private List<Portrait> myPortraits;
	private Portrait currentPortrait; // all commands typed to the console will be executed on this portrait
	
	FrontendManagerAPI myAPI;
	
	public FrontendManager(Properties myProp){
		myBackend = new BackendManager();
		myRoot = new BorderPane();
		myWindow = new Stage();
		myScene = new Scene(myRoot, 1000, 700, Color.WHITE);
		myComponents = new ArrayList<VisualComponent>();
		myAPI = new FrontendManagerAPI(this);
		
		initComponents();
	}
	
	public void initComponents(){
		myDisplay = ComponentFactory.makeNewDisplay(400, 400);
		myComponents.add(myDisplay);
		myConsole = ComponentFactory.makeNewConsole(200, 200);
		myComponents.add(myConsole);

		myHistory = ComponentFactory.makeNewHistory(200, 200);
		myComponents.add(myHistory);
		myConsole.setHistory(myHistory);
		myHistory.setConsole(myConsole);
		
		myVariables = ComponentFactory.makeNewVariables(200, 200);
		myComponents.add(myVariables);
		myToolbar = ComponentFactory.makeNewToolbar();
		myComponents.add(myToolbar);
		
		myPortraits = new ArrayList<Portrait>();

		
		myRoot.setCenter(myDisplay.getVisual());
		myRoot.setRight(myHistory.getVisual());
		myRoot.setBottom(myConsole.getVisual());
		myRoot.setLeft(myVariables.getVisual());
		myRoot.setTop(myToolbar.getVisual());
		
	}
	

    private void setupKeyboardCommands () {
        myScene.setOnKeyPressed(e -> {
            if (e.getCode() == KeyCode.ENTER) {
            	//BackendManager.executeCommand();
            }
        });
    }
    
    public void passConsoleInput(String s){
    	//call passConsoleInput from Display.java
    	//pass s to backend
    	// get some return result
    	// display that in console.
    }
    
    public void addToVariables(String s){
    	// myVariables.addToVariables(s);
    }
    
    public void updateVariableValue(String var, double value){
    	//backend call
    }
    
    public void drawLine(double x1, double y1, double x2, double y2){
    	myDisplay.drawLine(x1, y1, x2, y2);
    }
	
    public void changeDisplayBackgroundColor(Color c){
    	myDisplay.setBackgroundColor(c);
    }

	public Scene getMyScene(){ return this.myScene;}
}
