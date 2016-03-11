package frontend.menubar;

import java.io.File;


import controller.Controller;
import frontend.GUI.FileChooserWindow;
import javafx.scene.control.MenuItem;
import javafx.stage.FileChooser;
import javafx.stage.FileChooser.ExtensionFilter;
import util.FileHandler;

public class FileLoaderMenuItem extends MenuItem {

	public FileLoaderMenuItem(Controller controller) {
		this.setText("load file");
        this.setOnAction(
				e ->{			
					File logoFile = FileChooserWindow.choose("Select an file", "Logo Files (.logo)", "*.logo");
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
