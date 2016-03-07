package frontend.menubar;


import controller.Controller;

import javafx.scene.control.Menu;
import javafx.scene.control.MenuItem;


public class FileMenu extends Menu {
	public FileMenu(Controller controller) {
		this.setText("file");
        MenuItem loadFile = MenubarFactory.makeFileLoaderMenuItem(controller);
        
        MenuItem saveFile = MenubarFactory.makeFileSaverMenuItem(controller);

        this.getItems().addAll(loadFile, saveFile);
	}
}
