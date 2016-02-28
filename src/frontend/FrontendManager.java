package frontend;

import backend.*;
import javafx.scene.*;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Line;
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
	
	public FrontendManager(){
		myBackend = new BackendManager();
		myRoot = new BorderPane();
		myWindow = new Stage();
		myScene = new Scene(myRoot, SIZE, SIZE, Color.WHITE);
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
		
		myRoot.setCenter(myDisplay.getVisual());
		myRoot.setRight(myHistory.getVisual());
		myRoot.setBottom(myConsole.getVisual());
//		updateDisplay();
		
	}
	
    private void setupKeyboardCommands () {
        myScene.setOnKeyPressed(e -> {
            if (e.getCode() == KeyCode.ENTER) {
            	//BackendManager.executeCommand();
            }
        });
    }
	
//	public void updateDisplay(){
//		myRoot.getChildren().clear();
//		for (VisualComponent component : myComponents){
//			myRoot.getChildren().add(component.getVisual());
//		}
//	}
	
	public Scene getMyScene(){ return this.myScene;}
}
