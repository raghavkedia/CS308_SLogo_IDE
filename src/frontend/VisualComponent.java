package frontend;

import javafx.scene.Node;
import javafx.scene.paint.Color;

/**
 * Wrapper class for a javaFX Node object.
 * @author richardliu
 *
 */
abstract class VisualComponent {
	private Node myVisual;
	private Color myColor;
	
	public void setColor(Color newColor){ this.myColor = newColor; }
	public Color getColor(){ return this.myColor; }
	public void setVisual(Node newVis){this.myVisual = newVis;}
	public Node getVisual(){return this.myVisual;}
}
