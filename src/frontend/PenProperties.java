package frontend;

import javafx.scene.paint.Color;

public class PenProperties {
	private boolean isDown;
	private double myThickness;
	private Color myColor;
	//pattern
	
	public PenProperties(){
		isDown = true;
		myThickness = 10;
		myColor = Color.BLACK;
	}
	
	public boolean isDown(){ return isDown; }
	public double thickness(){ return myThickness; }
	public Color color(){ return myColor; }
	
	public void setIsDown(boolean down){ isDown = down;}
	public void setThickness(double thickness){ myThickness = thickness; }
	public void setColor(Color c){ myColor = c; }
}
