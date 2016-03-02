package frontend;

import java.util.ArrayList;

import javafx.scene.image.ImageView;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Line;

public class Display extends VisualComponent{
	private Pane myPane;
	private Color myLineColor;
	private ArrayList<Portrait> myPortraits;

	public Display(double width, double height){
		super.setColor(Color.WHITE);
		myLineColor = Color.BLACK;
		myPane = new Pane();
		myPane.setPrefSize(width, height);
		myPane.setBackground(new Background(new BackgroundFill(super.getColor(), null, null)));
		super.setVisual(myPane);
		this.myPortraits = new ArrayList<Portrait>();
		
		drawLine(0, 0, 100, 100);
	}
	
	public Display(double width, double height, Portrait defaultPortrait){
		super.setColor(Color.WHITE);
		myLineColor = Color.BLACK;
		myPane = new Pane();
		myPane.setPrefSize(width, height);
		myPane.setBackground(new Background(new BackgroundFill(super.getColor(), null, null)));
		super.setVisual(myPane);
		this.myPortraits = new ArrayList<Portrait>();
		this.myPortraits.add(defaultPortrait);
		
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
		double[] newInitialCoords = mapCoords(x1, y1);
		double[] newEndCoords = mapCoords(x2, y2);
		Line newLine = new Line(newInitialCoords[0], newInitialCoords[1], newEndCoords[0], newEndCoords[1]);
		newLine.setStroke(myLineColor);
		myPane.getChildren().add(newLine);
	}
	
	public void clearChars(){
		this.myPortraits.clear();
	}
	
	public void addPortrait(Portrait p){
		this.myPortraits.add(p);
		this.addImage(p.getMyPortrait(), p.getMyChar().getCoordX(), p.getMyChar().getCoordY());
	}
	
	public void addPortrait(Portrait p, double x, double y){
		this.myPortraits.add(p);
		this.addImage(p.getMyPortrait(), x, y);
	}
	
	public void addImage(ImageView img, double x, double y){
		if (!myPane.getChildren().contains(img)){
			myPane.getChildren().add(img);
		}
		img.setFitHeight(50);
		img.setFitWidth(50);
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
	public void setBackgroundColor(Color c){
		super.setColor(c);
		myPane.setBackground(new Background(new BackgroundFill(super.getColor(), null, null)));
	}
}