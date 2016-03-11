package backend.data;

import java.util.ArrayList;
import java.util.List;
import java.util.Observable;

import backend.Getable;
import backend.Setable;
import backend.data.Data.PenPattern;
import javafx.scene.shape.Line;

public class Character extends Observable implements Getable, Setable{
	
	private double myCoordX, myCoordY;
	private double myAngle;
	private String myName;
	private String myImagePath;
	private boolean visable;
	private String myPenColor;
	private boolean penDown;
	private PenPattern myPenPattern;
	private double myPenWidth;
	private int myColorIndex;
	private int myShapeIndex;
	List<Line> myLinesList;
	List<double[]> myStamps;
	
	public Character() {
		myCoordX = 0;
		myCoordY = 0;
		visable = true;
		myPenPattern = PenPattern.SOLID;
		penDown = true;
		myPenWidth = 3.0;
		//do we need to do something about initial pen color
		myStamps = new ArrayList<double[]>();
		myShapeIndex = 1;
	}
	
	public int getColorIndex(){
		return myColorIndex;
	}
	public void setColorIndex(int index){
		myColorIndex = index;
	}
	public int getShapeIndex(){
		return myShapeIndex;
	}
	public void setShapeIndex(int index){
		myShapeIndex = index;
	}
	public List<double[]> getStamps(){
		return myStamps;
	}
	
	public void addStamp(double x, double y){
		double[] stampCoord = {x, y};
		myStamps.add(stampCoord);
	}
	
	public double clearStamps(){
		double val = (myStamps.size() == 0) ? 0 : 1;
		myStamps.clear();
		return val;
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

	public void setImagePath(String path) {
		// TODO Auto-generated method stub
		myImagePath = path;
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
	
	public String getMyImagePath(){
		return this.myImagePath;
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
	
	public String getPenColor(){
		return myPenColor;
	}
	
	public void setPenColor(String color){
		myPenColor = color;
	}
	
	public double getPenWidth(){
		return myPenWidth;
	}
	
	public void setPenWidth(double width){
		myPenWidth = width;
	}

}
