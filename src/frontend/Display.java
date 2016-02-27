package frontend;

import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Line;

public class Display extends VisualComponent{
	private Pane myPane;

	public Display(double width, double height){
		super.setColor(Color.GREY);
		myPane = new Pane();
		myPane.setPrefSize(width, height);
		super.setVisual(myPane);
		
//		drawLine(0, 0, 100, 100);
	}
	
	/**
	 * This method adds a line from (x1, y1) to (x2, y2) to myPane.
	 * 
	 * @param x1 - starting x coordinate
	 * @param y1 - starting y coordinate
	 * @param x2 - ending x coordinate
	 * @param y2 - ending y coordinate
	 */
	public void drawLine(double x1, double y1, double x2, double y2){
		Line newLine = new Line(x1, y1, x2, y2);
		newLine.setStroke(Color.BLACK);
		myPane.getChildren().add(newLine);
	}
	
	public void addImage(ImageView img, double x, double y){
		if (!myPane.getChildren().contains(img)){
			myPane.getChildren().add(img);
		}
		img.setX(x);
		img.setY(y);
	}
}