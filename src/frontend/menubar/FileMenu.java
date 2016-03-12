package frontend.menubar;


import controller.Controller;
import frontend.GUI.Init.GUIString;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuItem;


public class FileMenu extends Menu {
	public FileMenu(Controller controller) {
		this.setText(controller.getGUIProperty(GUIString.FILE_MENU.getKey()));
        MenuItem loadFile = MenubarFactory.makeFileLoaderMenuItem(controller);
        
        MenuItem saveFile = MenubarFactory.makeFileSaverMenuItem(controller);

        this.getItems().addAll(loadFile, saveFile);
	}
}
