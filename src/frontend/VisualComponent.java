package frontend;

import javafx.scene.paint.Color;

public class VisualComponent {
	private double myX;
	private double myY;
	private double myWidth;
	private double myHeight;
	private Color myColor;
	
	public VisualComponent(double w, double h){
		this.myWidth = w;
		this.myHeight = h;
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
