package frontend.toobar;

import java.util.Properties;
import controller.Controller;
import frontend.VisualComponent;


import javafx.geometry.Orientation;

import javafx.scene.control.ToolBar;


public class ToolbarComponent extends VisualComponent{
	private ToolBar myToolbar;
	private Properties myGUIProp;
	private Controller myController;
	
	public ToolbarComponent(Properties GUIprop, Controller control) {
		myGUIProp = GUIprop;
		myController = control;
		myToolbar = new ToolBar();
		myToolbar.setOrientation(Orientation.HORIZONTAL);

		myToolbar.getItems().add(new BGColPicker(myController));
		
		myToolbar.getItems().add(new AddImageButton(myController)); 
				
		
		myToolbar.getItems().add(new PenColButton(myController));
		myToolbar.getItems().add(new RunButton(myController));
		myToolbar.getItems().add(new HelpButton(myController));
		myToolbar.getItems().add(new ClearButton(myController));
		myToolbar.getItems().add(new PenPropertiesButton(myController));
		super.setVisual(myToolbar);
	}
}
