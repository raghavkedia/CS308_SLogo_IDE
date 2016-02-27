package frontend;

import javafx.scene.Group;
import javafx.scene.paint.Color;
import javafx.scene.shape.*;

public class Display extends VisualComponent{
	private Shape myShape;

	public Display(){
		super(500, 500);
		myShape = new Rectangle(super.getMyWidth(), super.getMyHeight(), Color.WHITE);
	}
	
	public Display(double width, double height){
		super(width, height);
		myShape = new Rectangle(super.getMyWidth(), super.getMyHeight(), Color.WHITE);
	}
	
	
	
}
