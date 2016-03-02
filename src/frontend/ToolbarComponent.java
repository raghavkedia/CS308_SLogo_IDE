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

public class ToolbarComponent extends VisualComponent{
	private ToolBar myToolbar;
	private Properties myGUIProp;
	
	public ToolbarComponent(Properties GUIprop) {
		myGUIProp = GUIprop;
		myToolbar = new ToolBar();
		myToolbar.setOrientation(Orientation.HORIZONTAL);
		

		
		


		myToolbar.getItems().add(ComponentFactory.makeNewColorPicker());
		
		myToolbar.getItems().add(ComponentFactory.makeButton("Add New Image", 
				e ->{
					FileChooser fc = new FileChooser();
					fc.setTitle("Select an Image");
					fc.getExtensionFilters().setAll(new ExtensionFilter("Image Files (.png)", "*.png"));
					File imgFile = fc.showOpenDialog(FrontendManagerAPI.getMyWindow());
					if (imgFile != null) {
						Character c = new Character();
						c.setName("test");
						c.setImage(new Image("file://"+imgFile.toString()));
						FrontendManagerAPI.addNewChar(c);
					}
					
				}));

		super.setVisual(myToolbar);
		
		myToolbar.getItems().add(ComponentFactory.makeButton("RUN"));
		myToolbar.getItems().add(ComponentFactory.makeButton("CLEAR"));
		myToolbar.getItems().add(ComponentFactory.makeButton("HELP"));
	}
}
