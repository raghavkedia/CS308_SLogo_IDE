package frontend.menubar;

import controller.Controller;
import frontend.GUI.Init.GUIString;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuItem;

public class SettingMenu extends Menu{
	public SettingMenu(Controller controller) {
		this.setText(controller.getGUIProperty(GUIString.SETTING_MENU.getKey()));
        MenuItem loadSetting = MenubarFactory.makeSettingLoaderMenuItem(controller);
        MenuItem saveSetting = MenubarFactory.makeSettingSaverMenuItem(controller);
        this.getItems().addAll(loadSetting, saveSetting);
	}
}
