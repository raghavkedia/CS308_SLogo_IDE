package backend.data;

import java.util.Observable;

import backend.data.Data.PenPattern;
import javafx.beans.InvalidationListener;

import javafx.scene.paint.Color;

public class Properties extends Observable{
	
	private String myBackgroundColor;
	private boolean clearScreen;
	
	public Properties() {
		// TODO Auto-generated constructor stub

		myBackgroundColor = "";
		clearScreen = false;
		
	}
	
	public String getBackgroundColor(){
		return myBackgroundColor;
	}
	
	public void setBackgroundColor(String color){
		myBackgroundColor = color;
	}
	
	public void setClearScreen(boolean c){
		clearScreen = c;
	}
	
	public boolean isClearScreen(){
		return clearScreen;
	}

	public void hasUpdated(){
		setChanged();
		notifyObservers(this);
	}
	
}
