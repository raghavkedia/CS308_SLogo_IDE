package frontend.menubar;

import java.io.File;

import controller.Controller;
import frontend.GUI.FileSaverWindow;
import frontend.setting.SettingSaver;
import javafx.scene.control.MenuItem;
import util.PropertyLoader;

public class SettingSaverMenuItem extends MenuItem {

	public SettingSaverMenuItem(Controller controller) {
		this.setText("save current setting");
		this.setOnAction(
				e ->{
	                File file = FileSaverWindow.save("", "Property Files", "*" + PropertyLoader.EXTENSION);
					new SettingSaver(controller.getFrontendManager(), file);
				});
	}
}
