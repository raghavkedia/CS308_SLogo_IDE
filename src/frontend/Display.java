package frontend;

import javafx.scene.image.ImageView;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Line;

public class Display extends VisualComponent{
	private Pane myPane;
	private Color myLineColor;

	public Display(double width, double height){
		super.setColor(Color.GREY);
		myLineColor = Color.BLACK;
		myPane = new Pane();
		myPane.setPrefSize(width, height);
		myPane.setBackground(new Background(new BackgroundFill(super.getColor(), null, null)));
		super.setVisual(myPane);
		
		drawLine(0, 0, 100, 100);
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
		newLine.setStroke(myLineColor);
		myPane.getChildren().add(newLine);
	}
	
	public void addImage(ImageView img, double x, double y){
		if (!myPane.getChildren().contains(img)){
			myPane.getChildren().add(img);
		}
		img.setX(x);
		img.setY(y);
	}
	
	@Override
	public void setColor(Color c){
		super.setColor(c);
		myPane.setBackground(new Background(new BackgroundFill(super.getColor(), null, null)));
	}
	
	/**
	 * Maps coordinates x,y in Slogo space to JavaFX space.
	 * @param x 
	 * @param y
	 * @return
	 */
	public double[] mapCoords(double x, double y){
		double[] out = new double[] {x+this.myPane.getWidth()/2, y+this.myPane.getHeight()/2};
		return out;
	}
	
	public void setLineColor(Color c){this.myLineColor = c;}
}