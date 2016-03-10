package frontend.menubar;


import java.io.File;

import controller.Controller;
import frontend.SettingSaver;
import frontend.GUI.FileChooserWindow;
import javafx.scene.control.MenuItem;
import util.FileHandler;
import util.PropertyLoader;

public class SettingLoaderMenuItem  extends MenuItem {

	public SettingLoaderMenuItem(Controller controller) {
		this.setText("load existing setting");
		this.setOnAction(
				e ->{
					File propFile = FileChooserWindow.choose("Select an file", "Property Files", "*" + PropertyLoader.EXTENSION);
					if (propFile != null) {
				        try {
//							String content = FileHandler.getFileText(propFile);
//							controller.displayInConsole(content);
						} catch (Exception e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
						
					}	
				});
	}
}