package frontend;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;

import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.geometry.Orientation;
import javafx.scene.control.ColorPicker;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ToolBar;
import javafx.scene.paint.Color;

public class ToolbarComponent extends VisualComponent{
	private ToolBar myToolbar;
	
	public ToolbarComponent(){
		myToolbar = new ToolBar();
		myToolbar.setOrientation(Orientation.HORIZONTAL);
		
		
		myToolbar.getItems().add(ComponentFactory.makeNewColorPicker());
		super.setVisual(myToolbar);
	}
}
