package frontend;

import frontend.GUI.WebHelp;
import javafx.geometry.Orientation;
import javafx.scene.control.ToolBar;
import javafx.stage.Stage;

public class ToolbarComponent extends VisualComponent{
	private ToolBar myToolbar;
	
	public ToolbarComponent(){
		myToolbar = new ToolBar();
		myToolbar.setOrientation(Orientation.HORIZONTAL);
		myToolbar.getItems().add(ComponentFactory.makeButton("Define a command", 
				e -> {
					System.out.println("Pressed Define a Command");
				}));
		myToolbar.getItems().add(ComponentFactory.makeButton("Help", 
				e -> {
					WebHelp webHelp = new WebHelp("http://cs.duke.edu/courses/compsci308/spring16/assign/03_slogo/commands.php");
					try {
						webHelp.start(new Stage());
					} catch (Exception exception) {
						exception.printStackTrace();
					}
				}));
		
		super.setVisual(myToolbar);
		
		
	}
}
