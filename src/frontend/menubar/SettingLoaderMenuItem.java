package frontend.menubar;


import java.io.File;
import java.util.Properties;

import controller.Controller;
import frontend.GUI.FileChooserWindow;
import frontend.setting.SettingSaver;
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
							Properties settingProp = PropertyLoader.load(propFile);
//							controller.displayInConsole(content);
						} catch (Exception e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
						
					}	
				});
	}
}