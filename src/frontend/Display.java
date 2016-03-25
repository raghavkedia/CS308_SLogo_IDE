package frontend;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import controller.Controller;
import exceptions.InvalidCharacterError;
import javafx.scene.Node;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Line;
import backend.data.Data.PenPattern;

public class Display extends VisualComponent{
	private Pane myPane;
	private ArrayList<Portrait> myPortraits;
	private double myHeight;
	private double myWidth;
	private HashMap<String, Double> myPreviousX;
	private HashMap<String, Double> myPreviousY;
	private Controller myController;
	private ArrayList<Line> myLines;

	public Display(double width, double height, Controller control){
		super.setColor(Color.WHITE);
		myPane = new Pane();
		myWidth = width;
		myHeight = height;
		myPane.setBackground(new Background(new BackgroundFill(super.getColor(), null, null)));
		super.setVisual(myPane);
		this.myPortraits = new ArrayList<Portrait>();
		myPreviousX = new HashMap<String, Double>();
		myPreviousY = new HashMap<String, Double>();
		myController = control;
		myLines = new ArrayList<Line>();
		
	}
	
	public Display(double width, double height, Portrait defaultPortrait, Controller control){
		this(width, height, control);
		this.myPortraits.add(defaultPortrait);
	}
	
	/**
	 * This method adds a line from (x1, y1) to (x2, y2) to myPane.
	 * 
	 * @param x1 - starting x coordinate
	 * @param y1 - starting y coordinate
	 * @param x2 - ending x coordinate
	 * @param y2 - ending y coordinate
	 * @throws InvalidCharacterError 
	 */
	public void drawLine(double x1, double y1, double x2, double y2, String charId) throws InvalidCharacterError{
		double[] newInitialCoords = mapCoords(x1, y1);
		double[] newEndCoords = mapCoords(x2, y2);
		Line newLine = new Line(newInitialCoords[0], newInitialCoords[1], newEndCoords[0], newEndCoords[1]);
		newLine.setStroke(myController.getPenColor(charId));
		newLine.setStrokeWidth(myController.getLineThickness(charId));
		switch (myController.getPenPattern(charId)){
			case DASHED:
				newLine.getStrokeDashArray().addAll(25d, 20d, 5d, 20d);
				break;
			case DOTTED:
				newLine.getStrokeDashArray().addAll(2d, 21d);
				break;
			default:
				// do nothing -- solid
		}
		myLines.add(newLine);
		clearLinesDrawn();
		drawAllLines();
	}
	
	/**
	 * Removes all lines from the Display.
	 */
	public void clearAllLines(){
		clearLinesDrawn();
		myLines.clear();
	}
	
	/**
	 * Temporarily removes lines from the Pane object, but not from the myLines list.
	 */
	public void clearLinesDrawn(){
		for (Line l : myLines){
			for (int i=myPane.getChildren().size()-1; i>=0; i--){
				Node n = myPane.getChildren().get(i);
				if (l.equals(n)){
					myPane.getChildren().remove(i);
				}
			}
		}
	}
	
	/**
	 * Draws all lines in the myLines list on the Pane.
	 */
	public void drawAllLines(){
		for (Line l : myLines){
			myPane.getChildren().add(l);
		}
	}
	
	/**
	 * Removes all the character images from the Pane display.
	 */
	public void clearChars(){
		for (Portrait p : myPortraits){
			for (int i=myPane.getChildren().size()-1; i>=0; i--){
				Node n = myPane.getChildren().get(i);
				if (p.getMyPortrait().equals(n)){
					myPane.getChildren().remove(i);
				}
			}
		}
		this.myPortraits.clear();
	}
	
	/**
	 * Adds a portrait to the list, and determines whether or not it will be shown.
	 * @param p
	 * @throws InvalidCharacterError
	 */
	public void addPortrait(Portrait p) throws InvalidCharacterError{
		this.myPortraits.add(p);
		if (p.getMyChar().getVisability()){
			this.addImage(p, p.getMyChar().getCoordX(), p.getMyChar().getCoordY(), p.getMyChar().getMyAngle(), p.getMyChar().getVisability());
		}
			
		String charName = p.getMyChar().getName();
		
		if (p.getMyChar().getPenState() && myPreviousX.keySet().contains(charName)){
			drawLine(myPreviousX.get(charName), myPreviousY.get(charName), p.getMyChar().getCoordX(), p.getMyChar().getCoordY(), p.getMyChar().getName());
		}
		myPreviousX.put(p.getMyChar().getName(), (double) p.getMyChar().getCoordX());
		myPreviousY.put(p.getMyChar().getName(), (double) p.getMyChar().getCoordY());
	}
	
	/**
	 * Physically displays an image of a portrait in the Pane
	 * @param p
	 * @param x
	 * @param y
	 * @param angle
	 * @param visible
	 */
	public void addImage(Portrait p, double x, double y, double angle, boolean visible){
		ImageView img = p.getMyPortrait();
		if (!myPane.getChildren().contains(img)){
			myPane.getChildren().add(img);
		}
		img.setFitHeight(50);
		img.setFitWidth(50);
		
		double[] mappedStart = mapCoords(x, y);
		img.setX(mappedStart[0]-25);
		img.setY(mappedStart[1]-25);
		img.setRotate(angle);
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
		double[] out = new double[] {x+this.myWidth/2, this.myHeight-this.myHeight/2-y};
		return out;
	}
	
    public String getBackgroundRGB(){
    	Color c = super.getColor();
       	String hex = String.format( "#%02X%02X%02X",
                (int)( c.getRed() * 255 ),
                (int)( c.getGreen() * 255 ),
                (int)( c.getBlue() * 255 ) );
    	return hex;
    }
	
//	public void setLineColor(Color c){myController.setPenColor(c);}
	public void setBackgroundColor(Color c){
		super.setColor(c);
		myPane.setBackground(new Background(new BackgroundFill(super.getColor(), null, null)));
	}
}