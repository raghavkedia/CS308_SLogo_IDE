package frontend.menubar;

import java.io.File;

import controller.Controller;
import frontend.GUI.FileWindow;
import frontend.GUI.Init.GUIString;
import frontend.setting.SettingSaver;
import javafx.scene.control.MenuItem;
import util.PropertyLoader;

public class SettingSaverMenuItem extends MenuItem {

	public SettingSaverMenuItem(Controller controller) {
		this.setText(controller.getGUIProperty(GUIString.SETTING_MENU_SAVE.getKey()));
		this.setOnAction(
				e ->{
					String title = controller.getGUIProperty(GUIString.SETTING_SAVE_TITLE.getKey());
					String ext = "*" + PropertyLoader.EXTENSION;
	                File file = FileWindow.save(title, "Property Files", ext);
					new SettingSaver(controller.getFrontendManager(), file);
				});
	}
}
