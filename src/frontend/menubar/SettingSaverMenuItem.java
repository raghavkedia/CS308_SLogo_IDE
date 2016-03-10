package frontend.menubar;

import java.io.File;

import controller.Controller;
import frontend.SettingSaver;
import frontend.GUI.FileSaverWindow;
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
