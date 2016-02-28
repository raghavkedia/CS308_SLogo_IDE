package frontend;

import javafx.geometry.Orientation;
import javafx.scene.control.ToolBar;

public class ToolbarComponent extends VisualComponent{
	private ToolBar myToolbar;
	
	public ToolbarComponent(){
		myToolbar = new ToolBar();
		myToolbar.setOrientation(Orientation.HORIZONTAL);
		myToolbar.getItems().add(ComponentFactory.makeButton("Define a command", 
				e -> {
					System.out.println("Pressed Define a Command");
				}));
		super.setVisual(myToolbar);
	}
}
