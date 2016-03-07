package frontend.menubar;

import controller.Controller;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuItem;

public class MenubarFactory {

	//File Menu
	public static Menu makeFileMenu(Controller c) {
		return new FileMenu(c);
	}

	public static MenuItem makeFileLoaderMenuItem(Controller c) {
		return new FileLoaderMenuItem(c);
	}
	
	public static MenuItem makeFileSaverMenuItem(Controller c) {
		return new FileSaverMenuItem(c);
	}
	
	//Setting Menu	
	public static Menu makeSettingMenu(Controller c) {
		return new SettingMenu(c);
	}
	
}
