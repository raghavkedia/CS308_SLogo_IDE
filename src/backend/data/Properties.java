package backend.data;

import backend.data.Data.PenPattern;
import javafx.scene.paint.Color;

public class Properties {
	
	private Color myBackgroundColor;
	private Color myPenColor;
	private boolean penDown;
	private PenPattern myPenPattern;
	private double myPenWidth;
	
	public Properties() {
		// TODO Auto-generated constructor stub
		myPenPattern = PenPattern.SOLID;
		penDown = true;
		myPenWidth = 3.0;
		myBackgroundColor = Color.WHITE;
		myPenColor = Color.BLACK;
	}
	
	
	public void setPenPattern(PenPattern pattern){
		myPenPattern = pattern;
	}
	
	public PenPattern getPenPattern(){
		return myPenPattern;
	}
	
	public boolean getPenDown(){
		return penDown;
	}
	
	public void setPenDown(boolean penStatus){
		penDown = penStatus;
	}
	
	public Color getBackgroundColor(){
		return myBackgroundColor;
	}
	
	public void setBackgroundColor(Color color){
		myBackgroundColor = color;
	}
	
	public Color getPenColor(){
		return myPenColor;
	}
	
	public void setPenColor(Color color){
		myPenColor = color;
	}
	
	public double getPenWidth(){
		return myPenWidth;
	}
	
	public void setPenWidth(double width){
		myPenWidth = width;
	}

	
}
