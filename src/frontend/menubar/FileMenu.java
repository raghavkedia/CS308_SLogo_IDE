package frontend.menubar;

import java.io.File;
import java.util.Scanner;

import controller.Controller;

import javafx.scene.control.Menu;
import javafx.scene.control.MenuItem;

import javafx.stage.FileChooser;
import javafx.stage.FileChooser.ExtensionFilter;
import util.FileHandler;

public class FileMenu extends Menu {
	public FileMenu(Controller controller) {
//		this.setText(c.getGUIProperty("file_menu"));
//      MenuItem loadFile = new MenuItem(c.getGUIProperty("file_menu_load"));
		this.setText("file");
        MenuItem loadFile = new MenuItem("load file");
        loadFile.setOnAction(
				e ->{			
					FileChooser fc = new FileChooser();
					fc.setTitle("Select an file");
					fc.getExtensionFilters().setAll(new ExtensionFilter("Logo Files (.logo)", "*.logo"));
					File logoFile = fc.showOpenDialog(controller.getMyStage());
					if (logoFile != null) {
				        try {
							String content = new Scanner(logoFile).useDelimiter("\\Z").next();
							controller.displayInConsole(content);
						} catch (Exception e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
						
					}					
				}
        		);
        this.getItems().addAll(loadFile);
        
        MenuItem saveFile = new MenuItem("save file");
        saveFile.setOnAction(
				e ->{
					FileHandler fileHandler = new FileHandler();
					String content = controller.getConsoleText();
					try {
						fileHandler.saveFile("Saved.logo", content);
					} catch (Exception e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				}
        		);
        this.getItems().addAll(saveFile);
	}
}
