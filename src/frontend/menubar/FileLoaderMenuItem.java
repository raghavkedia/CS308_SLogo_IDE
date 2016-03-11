package frontend.menubar;

import java.io.File;


import controller.Controller;
import frontend.GUI.FileWindow;
import frontend.GUI.Init.GUIString;
import javafx.scene.control.MenuItem;
import util.FileHandler;

public class FileLoaderMenuItem extends MenuItem {

	public FileLoaderMenuItem(Controller controller) {
		this.setText(controller.getGUIProperty(GUIString.FILE_MENU_LOAD.getKey()));
        this.setOnAction(
				e ->{	
					String title = controller.getGUIProperty(GUIString.FILE_LOAD_TITLE.getKey());
					File logoFile = FileWindow.choose(title, "Logo Files (.logo)", "*.logo");
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
