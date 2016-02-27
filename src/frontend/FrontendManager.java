package frontend;

import backend.*;
import javafx.scene.*;
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
		myHistory = ComponentFactory.makeNewHistory(200, 200);
		myComponents.add(myHistory);
		myConsole = ComponentFactory.makeNewConsole(200, 200);
		myComponents.add(myConsole);
		
		myRoot.setCenter(myDisplay.getVisual());
		myRoot.setRight(myHistory.getVisual());
		myRoot.setBottom(myConsole.getVisual());
//		updateDisplay();
		
	}
	
//	public void updateDisplay(){
//		myRoot.getChildren().clear();
//		for (VisualComponent component : myComponents){
//			myRoot.getChildren().add(component.getVisual());
//		}
//	}
	
	public Scene getMyScene(){ return this.myScene;}
}
