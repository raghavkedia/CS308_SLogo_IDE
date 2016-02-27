package frontend;

import backend.*;
import javafx.scene.*;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.stage.*;
import java.util.*;

public class FrontendManager {
	public static final int SIZE = 700;
    
    private Scene myScene;
    private StackPane myRoot;
    private Stage myWindow;
    private List<VisualComponent> myComponents;
	private InterpreturInterface myBackend;
	
	public FrontendManager(){
		myBackend = new BackendManager();
		myRoot = new StackPane();
		myWindow = new Stage();
		myScene = new Scene(myRoot, SIZE, SIZE, Color.WHITE);
		myComponents = new ArrayList<VisualComponent>();
	}
	
	public void updateDisplay(){
		myRoot = new StackPane();
		for (VisualComponent component : myComponents){
			myRoot.getChildren().add(component.getVisual());
		}
	}
	
	public Scene getMyScene(){ return this.myScene;}
}
