package frontend;

import javafx.scene.Group;
import javafx.scene.Scene;

public class Display extends VisualComponent{
	private Group myRoot;
	private Scene myScene;

	Display(){
		super(500, 500);
		this.myRoot = new Group();
	}
	
	Display(double width, double height){
		super(width, height);
		this.myRoot = new Group();
	}

}
