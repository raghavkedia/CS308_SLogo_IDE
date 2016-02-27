package frontend;

import backend.*;
import javafx.scene.*;
import javafx.scene.paint.Color;
import javafx.stage.*;
import java.util.*;

public class FrontendManager {
	public static final int SIZE = 700;
    
    private Scene myScene;
    private Group myRoot;
    private Stage myWindow;
    private List<VisualComponent> myComponents;
	private InterpreturInterface myBackend;
	
	public FrontendManager(){
		myBackend = new BackendManager();
		myRoot = new Group();
		myWindow = new Stage();
		myScene = new Scene(myRoot, SIZE, SIZE, Color.WHITE);
		myComponents = new ArrayList<VisualComponent>();
	}
	
	public void updateDisplay(){
		myRoot = new Group();
		for (VisualComponent component : myComponents){
			
		}
	}
	
	public Scene getMyScene(){ return this.myScene; }
}
