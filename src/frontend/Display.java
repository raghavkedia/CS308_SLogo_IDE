package frontend;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import controller.Controller;
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
		myPane.setMinSize(width, height);
		myPane.setPrefSize(width, height);
		myPane.setMaxSize(width, height);
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
		super.setColor(Color.WHITE);
		myPane = new Pane();
		myPane.setMinSize(width, height);
		myPane.setPrefSize(width, height);
		myPane.setMaxSize(width, height);
		myWidth = width;
		myHeight = height;
		myPane.setBackground(new Background(new BackgroundFill(super.getColor(), null, null)));
		super.setVisual(myPane);
		this.myPortraits = new ArrayList<Portrait>();
		myPreviousX = new HashMap<String, Double>();
		myPreviousY = new HashMap<String, Double>();
		this.myPortraits.add(defaultPortrait);
		myController = control;
		myLines = new ArrayList<Line>();
	}
	
	/**
	 * This method adds a line from (x1, y1) to (x2, y2) to myPane.
	 * 
	 * @param x1 - starting x coordinate
	 * @param y1 - starting y coordinate
	 * @param x2 - ending x coordinate
	 * @param y2 - ending y coordinate
	 */
	public void drawLine(double x1, double y1, double x2, double y2, String charId){
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
	
	public void clearLinesDrawn(){
		Line test = new Line();
		for (int i=myPane.getChildren().size()-1; i>=0; i--){
			if (myPane.getChildren().get(i).getClass().equals(test.getClass())){
				myPane.getChildren().remove(i);
			}
		}
	}
	
	public void drawAllLines(){
		for (Line l : myLines){
			myPane.getChildren().add(l);
		}
	}
	
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
	
	public void addPortrait(Portrait p){
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
	
//	public void setLineColor(Color c){myController.setPenColor(c);}
	public void setBackgroundColor(Color c){
		super.setColor(c);
		myPane.setBackground(new Background(new BackgroundFill(super.getColor(), null, null)));
	}
}