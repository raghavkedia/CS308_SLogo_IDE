package frontend;

import java.util.ArrayList;
import java.util.HashMap;

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
		double[] newInitialCoords = mapCoords(x1, y1);
		double[] newEndCoords = mapCoords(x2, y2);
		Line newLine = new Line(newInitialCoords[0], newInitialCoords[1], newEndCoords[0], newEndCoords[1]);
		newLine.setStroke(myController.getPenColor());
		newLine.setStrokeWidth(myController.getLineThickness());
		switch (myController.getPenPattern()){
			case DASHED:
				newLine.getStrokeDashArray().addAll(25d, 20d, 5d, 20d);
				break;
			case DOTTED:
				newLine.getStrokeDashArray().addAll(2d, 21d);
				break;
			default:
				// do nothing -- solid
		}
		myPane.getChildren().add(newLine);
	}
	
	public void clearChars(){
		for (int i=0; i<myPane.getChildren().size(); i++){
			Node n = myPane.getChildren().get(i);
			for (Portrait p : myPortraits){
				if (p.getMyPortrait() == n){
					myPane.getChildren().remove(n);
					break;
				}
			}
		}
		this.myPortraits.clear();
	}
	
	public void addPortrait(Portrait p){
		this.myPortraits.add(p);


		this.addImage(p.getMyPortrait(), p.getMyChar().getCoordX(), p.getMyChar().getCoordY(), p.getAngle() );

		String charName = p.getMyChar().getName();
		if (p.getMyChar().getPenState() && myPreviousX.keySet().contains(charName)){
			drawLine(myPreviousX.get(charName), myPreviousY.get(charName), p.getMyChar().getCoordX(), p.getMyChar().getCoordY());
		}
		myPreviousX.put(p.getMyChar().getName(), (double) p.getMyChar().getCoordX());
		myPreviousY.put(p.getMyChar().getName(), (double) p.getMyChar().getCoordY());

	}
	
	public void addPortrait(Portrait p, double x, double y, double angle){
		this.myPortraits.add(p);


		this.addImage(p.getMyPortrait(), x, y, angle);

		String charName = p.getMyChar().getName();
		if (p.getMyChar().getPenState() && myPreviousX.keySet().contains(charName)){
			drawLine(myPreviousX.get(charName), myPreviousY.get(charName), p.getMyChar().getCoordX(), p.getMyChar().getCoordY());
		}
		myPreviousX.put(p.getMyChar().getName(), (double) p.getMyChar().getCoordX());
		myPreviousY.put(p.getMyChar().getName(), (double) p.getMyChar().getCoordY());
	}
	
	public void addImage(ImageView img, double x, double y, double angle){
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
	
	public void setLineColor(Color c){myController.setPenColor(c);}
	public void setBackgroundColor(Color c){
		super.setColor(c);
		myPane.setBackground(new Background(new BackgroundFill(super.getColor(), null, null)));
	}
}