package frontend.menubar;

import java.io.File;
import java.io.FileWriter;
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
					String content = controller.getConsoleText();
					try {
			            FileChooser fileChooser = new FileChooser();
			            fileChooser.getExtensionFilters().addAll(
			                    new ExtensionFilter("Slogo Files", "*.logo"));
						File file = fileChooser.showSaveDialog(controller.getMyStage());
			              if(file != null){
			                  FileHandler.saveFile(content, file);
			              }
					} catch (Exception e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				}
        		);
        this.getItems().addAll(saveFile);
	}
}
