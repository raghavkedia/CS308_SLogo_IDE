package frontend;

import backend.*;
import javafx.scene.*;
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
	private InterpreturInterface myBackend;
	
	private Display myDisplay;
	private History myHistory;
	private Variables myVariables;
	private Console myConsole;
	private ToolbarComponent myToolbar;
	private List<Portrait> myPortraits;
	private Portrait currentPortrait; // all commands typed to the console will be executed on this portrait
	
	public FrontendManager(){
		myBackend = new BackendManager();
		myRoot = new BorderPane();
		myWindow = new Stage();
		myScene = new Scene(myRoot, 1000, 700, Color.WHITE);
		myComponents = new ArrayList<VisualComponent>();
		
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
	


	public Scene getMyScene(){ return this.myScene;}
}
