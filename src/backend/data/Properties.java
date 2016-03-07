package backend.data;

import backend.data.Data.PenPattern;
import javafx.scene.paint.Color;

public class Properties {
	
	private Color myBackgroundColor;
	
	
	public Properties() {
		// TODO Auto-generated constructor stub

		myBackgroundColor = Color.WHITE;
		
	}
	
	public Color getBackgroundColor(){
		return myBackgroundColor;
	}
	
	public void setBackgroundColor(Color color){
		myBackgroundColor = color;
	}


	
}
