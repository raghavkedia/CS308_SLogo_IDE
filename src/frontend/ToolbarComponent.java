package frontend;

import java.io.File;
import java.util.Properties;
import frontend.GUI.WebHelp;

import javafx.geometry.Orientation;
import javafx.scene.control.Button;
import javafx.scene.control.ToolBar;
import javafx.scene.image.Image;
import javafx.stage.FileChooser;
import javafx.stage.FileChooser.ExtensionFilter;
import javafx.stage.Stage;
import backend.*;
import backend.Character;
import controller.Controller;

public class ToolbarComponent extends VisualComponent{
	private ToolBar myToolbar;
	private Properties myGUIProp;
	private Controller myController;
	
	public ToolbarComponent(Properties GUIprop, Controller control) {
		myGUIProp = GUIprop;
		myController = control;
		myToolbar = new ToolBar();
		myToolbar.setOrientation(Orientation.HORIZONTAL);

		myToolbar.getItems().add(ComponentFactory.makeNewColorPicker(myController));
		
		myToolbar.getItems().add(ComponentFactory.makeButton("Add New Image", 
				e ->{
					FileChooser fc = new FileChooser();
					fc.setTitle("Select an Image");
					fc.getExtensionFilters().setAll(new ExtensionFilter("Image Files (.png)", "*.png"));
					File imgFile = fc.showOpenDialog(myController.getMyWindow());
					if (imgFile != null) {
						Character c = new Character();
						c.setName(Integer.toString(c.hashCode()));
						c.setImage(new Image("file://"+imgFile.toString()));
						myController.addNewChar(c);
					}
					
				}));
		
		myToolbar.getItems().add(ComponentFactory.makeNewColorPickerLine(myController));

		super.setVisual(myToolbar);
		
		myToolbar.getItems().add(ComponentFactory.makeButton("RUN", myController));
		myToolbar.getItems().add(ComponentFactory.makeButton("CLEAR", myController));
		myToolbar.getItems().add(ComponentFactory.makeButton("HELP", myController));
	}
}
