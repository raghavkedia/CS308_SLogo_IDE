package backend.data;

import backend.data.Data.PenPattern;
import javafx.scene.paint.Color;

public class Properties {
	
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

	
}
