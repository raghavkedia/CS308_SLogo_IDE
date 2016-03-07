package backend.data;

import backend.data.Data.PenPattern;
import javafx.scene.paint.Color;

public class Properties {
	
	private String myBackgroundColor;
	
	
	public Properties() {
		// TODO Auto-generated constructor stub

		myBackgroundColor = "";
		
	}
	
	public String getBackgroundColor(){
		return myBackgroundColor;
	}
	
	public void setBackgroundColor(String color){
		myBackgroundColor = color;
	}


	
}
