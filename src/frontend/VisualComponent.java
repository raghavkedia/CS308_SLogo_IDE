package frontend;

import javafx.scene.Node;
import javafx.scene.paint.Color;

abstract class VisualComponent {
	private Node myVisual;
	private Color myColor;
	
	public VisualComponent(){
		this.myColor = Color.GREY;
	}
	
	public void setColor(Color newColor){ this.myColor = newColor; }
	public void setVisual(Node newVis){this.myVisual = newVis;}
}
