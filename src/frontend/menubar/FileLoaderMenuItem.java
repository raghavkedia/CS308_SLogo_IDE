package frontend.menubar;

import java.io.File;


import controller.Controller;
import frontend.GUI.FileChooserWindow;
import frontend.GUI.Init.GUIString;
import javafx.scene.control.MenuItem;
import javafx.stage.FileChooser;
import javafx.stage.FileChooser.ExtensionFilter;
import util.FileHandler;

public class FileLoaderMenuItem extends MenuItem {

	public FileLoaderMenuItem(Controller controller) {
		this.setText(controller.getGUIProperty(GUIString.FILE_MENU_LOAD.getKey()));
        this.setOnAction(
				e ->{	
					String title = controller.getGUIProperty(GUIString.FILE_LOAD_TITLE.getKey());
					File logoFile = FileChooserWindow.choose(title, "Logo Files (.logo)", "*.logo");
					if (logoFile != null) {
				        try {
							String content = FileHandler.getFileText(logoFile);
							controller.displayInConsole(content);
						} catch (Exception e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
						
					}					
				}
        		);
	}

}
