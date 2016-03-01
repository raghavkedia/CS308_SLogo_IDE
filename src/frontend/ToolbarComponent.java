package frontend;

import java.util.Properties;
import frontend.GUI.WebHelp;

import javafx.geometry.Orientation;
import javafx.scene.control.ToolBar;
import javafx.stage.Stage;

import javafx.scene.paint.Color;

public class ToolbarComponent extends VisualComponent{
	private ToolBar myToolbar;
	private Properties myGUIProp;
	
	public ToolbarComponent(Properties prop) {
		myGUIProp = prop;
		myToolbar = new ToolBar();
		myToolbar.setOrientation(Orientation.HORIZONTAL);
		

		myToolbar.getItems().add(ComponentFactory.makeButton("Help", 
				e -> {
					WebHelp webHelp = new WebHelp(myGUIProp);
					try {
						webHelp.start(new Stage());
					} catch (Exception exception) {
						exception.printStackTrace();
					}
				}));
		


		myToolbar.getItems().add(ComponentFactory.makeNewColorPicker());

		super.setVisual(myToolbar);
		
		
	}
}
