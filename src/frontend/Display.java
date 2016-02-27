package frontend;

import javafx.scene.Node;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Line;

public class Display extends VisualComponent{
	private Pane myCanvas;

	public Display(double width, double height){
		myCanvas = new Pane();
		myCanvas.setPrefSize(width, height);
		super.setVisual(myCanvas);
		super.setColor(Color.WHITE);
	}
	
	public void drawLine(double x1, double y1, double x2, double y2){
//		GraphicsContext gc = myCanvas.getGraphicsContext2D();
//		gc.beginPath();
//		gc.moveTo(x1, y1);
//		gc.lineTo(x2, y2);
//		gc.stroke();
		
		Line newLine = new Line(x1, y1, x2, y2);
		newLine.setStroke(Color.BLACK);
		myCanvas.getChildren().add(newLine);
	}
	
	public void addImage(ImageView img, double x, double y){
//		GraphicsContext gc = myCanvas.getGraphicsContext2D();
//		gc.drawImage(img, x, y);
		
		if (!myCanvas.getChildren().contains(img)){
			myCanvas.getChildren().add(img);
		}
		img.setX(x);
		img.setY(y);
	}
}