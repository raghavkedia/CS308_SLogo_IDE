package frontend.toobar;

import java.util.Properties;

import frontend.VisualComponent;


import javafx.geometry.Orientation;

import javafx.scene.control.ToolBar;


public class ToolbarComponent extends VisualComponent{
	private ToolBar myToolbar;
	private Properties myGUIProp;
	
	public ToolbarComponent(Properties GUIprop) {
		myGUIProp = GUIprop;
		myToolbar = new ToolBar();
		myToolbar.setOrientation(Orientation.HORIZONTAL);

		myToolbar.getItems().add(new BGColPicker());
		
		myToolbar.getItems().add(new AddImageButton()); 
				
		
		myToolbar.getItems().add(new PenColButton());
		myToolbar.getItems().add(new RunButton());
		myToolbar.getItems().add(new HelpButton());
		myToolbar.getItems().add(new ClearButton());
		super.setVisual(myToolbar);
	}
}
