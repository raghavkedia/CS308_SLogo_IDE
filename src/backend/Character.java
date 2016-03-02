package backend;

import java.util.List;
import java.util.Map;
import java.util.Observable;
import java.util.Observer;

import javafx.scene.image.Image;

public class Character extends Observable implements Getable, Setable{
	
	private int myCoordX, myCoordY;
	private double myAngle;
	private String myName;
	private Image myImage;
	private boolean visable;
	private boolean penDown;
	List<List<int[]>> myLinesList;
	
	public Character() {
		myCoordX = 0;
		myCoordY = 0;
		visable = true;
		penDown = true;
	}
	
	public void setCurrCoord(int x, int y) {
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

	public void addLine(List<int[]> line) {
		// TODO Auto-generated method stub
		myLinesList.add(line);
	}

	public int getCoordX() {
		// TODO Auto-generated method stub
		return myCoordX;
	}

	public int getCoordY() {
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

	public List<List<int[]>> getLines() {
		// TODO Auto-generated method stub
		return myLinesList;
	}

	public double getMyAngle() {
		return myAngle;
	}

	public void setMyAngle(double myAngle) {
		this.myAngle = myAngle;
	}


}
