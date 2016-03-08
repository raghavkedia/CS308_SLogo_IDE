package backend.data;

import java.util.List;
import java.util.Map;
import java.util.Observable;
import java.util.Observer;

import backend.Getable;
import backend.Setable;
import javafx.scene.image.Image;
import javafx.scene.shape.Line;

public class Character extends Observable implements Getable, Setable{
	
	private double myCoordX, myCoordY;
	private double myAngle;
	private String myName;
	private Image myImage;
	private boolean visable;
	private boolean penDown;
	List<Line> myLinesList;
	
	public Character() {
		myCoordX = 0;
		myCoordY = 0;
		visable = true;
		penDown = true;
	}
	
	public void setCurrCoord(double x, double y) {
		// TODO Auto-generated method stub
		myCoordX = x;
		myCoordY = y;
	}
	
	public void setName(String name) {
		// TODO Auto-generated method stub
		myName = name;
	}
	
	public void hasUpdated(){
		setChanged();
		notifyObservers();
	}

	public void setImage(Image image) {
		// TODO Auto-generated method stub
		myImage = image;
	}

	public void setVisability(boolean visability) {
		// TODO Auto-generated method stub
		visable = visability;
	}

	public void setPenState(boolean penState) {
		// TODO Auto-generated method stub
		penDown = penState;
	}

	public void addLine(Line line) {
		// TODO Auto-generated method stub
		myLinesList.add(line);
	}

	public double getCoordX() {
		// TODO Auto-generated method stub
		return myCoordX;
	}

	public double getCoordY() {
		// TODO Auto-generated method stub
		return myCoordY;
	}

	public String getName() {
		// TODO Auto-generated method stub
		return myName;
	}

	public boolean getVisability() {
		// TODO Auto-generated method stub
		return visable;
	}

	public boolean getPenState() {
		// TODO Auto-generated method stub
		return penDown;
	}

	public List<Line> getLines() {
		// TODO Auto-generated method stub
		return myLinesList;
	}
	
	public void removeLines(){
		myLinesList.clear();
	}

	public double getMyAngle() {
		return myAngle;
	}

	public void setMyAngle(double myAngle) {
		this.myAngle = myAngle;
	}
	
	public Image getMyImage(){
		return this.myImage;
	}


}
