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

		myToolbar.getItems().add(ToolbarFactory.BGColPicker(myController));		
		myToolbar.getItems().add(ToolbarFactory.makeAddImageButton(myController)); 	
//		myToolbar.getItems().add(ToolbarFactory.makePenColPicker(myController));
		myToolbar.getItems().add(ToolbarFactory.makeRunButton(myController));
		myToolbar.getItems().add(ToolbarFactory.makeClearButton(myController));
		myToolbar.getItems().add(ToolbarFactory.makeHelpButton(myController));
		myToolbar.getItems().add(ToolbarFactory.makeCreateWorkSpaceButton(myController));
		myToolbar.getItems().add(new PenPropertiesButton(myController));
		
		super.setVisual(myToolbar);
	}
}
