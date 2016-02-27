package frontend;

import javafx.scene.Group;
import javafx.scene.paint.Color;

abstract class VisualComponent {
	private double myX;
	private double myY;
	private double myWidth;
	private double myHeight;
	private Group myRoot;
	private Color myColor;
	
	public VisualComponent(double width, double height){
		this.myWidth = width;
		this.myHeight = height;
		this.myColor = Color.GREY;
	}
	
	public void bind(double xCor, double yCor){
		this.myX = xCor;
		this.myY = yCor;
	}
	
	public double getMyX() {return myX;}
	public void setMyX(double myX) {this.myX = myX;}
	public double getMyY() {return myY;}
	public void setMyY(double myY) {this.myY = myY;}
	public double getMyWidth() {return myWidth;}
	public void setMyWidth(double myWidth) {this.myWidth = myWidth;}
	public double getMyHeight() {return myHeight;}
	public void setMyHeight(double myHeight) {this.myHeight = myHeight;}
	public void setColor(Color newColor){ this.myColor = newColor; }
}
