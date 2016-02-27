package frontend;

import javafx.scene.canvas.Canvas;

public class Display extends VisualComponent{

	public Display(double width, double height){
		super.setVisual(new Canvas(width, height));
	}
	
	
}
