package frontend;

import javafx.scene.Group;
import javafx.scene.Scene;

public class Display extends VisualComponent{
	private Group myRoot;
	private Scene myScene;

	Display(){
		super.setMyHeight(500);
		super.setMyWidth(500);
		super.setMyX(10);
		super.setMyY(10);
		this.myRoot = new Group();
	}
	
	Display(double height, double width){
		super.setMyHeight(height);
		super.setMyWidth(width);
		this.myRoot = new Group();
	}

}
