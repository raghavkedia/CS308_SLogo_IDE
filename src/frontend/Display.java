package frontend;

import javafx.scene.canvas.Canvas;

public class Display extends VisualComponent{

	public Display(double width, double height){
		super.setVisual(new Canvas(width, height));
	}
	
	public void drawLine(double xCor, double yCor){
		/*get current coordinate of turtle(s)
		draw line from that turtle's current location to new location
		using GraphicsContext gc = canvas.getGraphicsContext2D();
		gc.beginPath()
		gc.moveTo(currentcoord)
		gc.lineTo(newCoord)
		gc.stroke()
		*/
	}
}
