package frontend;

import java.util.Properties;
import frontend.GUI.WebHelp;

import javafx.geometry.Orientation;
import javafx.scene.control.ToolBar;
import javafx.stage.Stage;

public class ToolbarComponent extends VisualComponent{
	private ToolBar myToolbar;
	private Properties myGUIProp;
	
	public ToolbarComponent(Properties GUIprop) {
		myGUIProp = GUIprop;
		myToolbar = new ToolBar();
		myToolbar.setOrientation(Orientation.HORIZONTAL);
		

		
		


		myToolbar.getItems().add(ComponentFactory.makeNewColorPicker());

		super.setVisual(myToolbar);
		
		myToolbar.getItems().add(ComponentFactory.makeButton("CLEAR"));
		myToolbar.getItems().add(ComponentFactory.makeButton("HELP"));
		myToolbar.getItems().add(ComponentFactory.makeButton("RUN"));
	}
}
