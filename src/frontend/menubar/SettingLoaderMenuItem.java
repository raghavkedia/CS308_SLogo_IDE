package frontend.menubar;

import java.io.File;
import controller.Controller;
import frontend.GUI.FileWindow;
import frontend.GUI.Init.GUIString;
import frontend.setting.SettingLoader;
import javafx.scene.control.MenuItem;
import util.PropertyLoader;

public class SettingLoaderMenuItem  extends MenuItem {

	public SettingLoaderMenuItem(Controller controller) {
		this.setText(controller.getGUIProperty(GUIString.SETTING_MENU_LOAD.getKey()));
		this.setOnAction(
				e ->{
					String title = controller.getGUIProperty(GUIString.SETTING_LOAD_TITLE.getKey());
					File propFile = FileWindow.choose(title, "Property Files", "*" + PropertyLoader.EXTENSION);
					if (propFile != null) {
				        try {
						    new SettingLoader(controller.getFrontendManager(), propFile);
						} catch (Exception e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
						
					}	
				});
	}
}