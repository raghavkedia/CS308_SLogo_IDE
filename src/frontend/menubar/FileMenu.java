package frontend.menubar;

import controller.Controller;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuItem;

public class FileMenu extends Menu {
	public FileMenu(Controller c) {
		this.setText("File");
        MenuItem loadFile = new MenuItem("load file");
        loadFile.setOnAction(new EventHandler<ActionEvent>() {
                public void handle(ActionEvent t) {     
                }
            });        
     
        this.getItems().addAll(loadFile);
	}
}
