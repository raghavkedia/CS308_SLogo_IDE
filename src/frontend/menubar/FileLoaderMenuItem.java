package frontend.menubar;

import java.io.File;
import java.util.Scanner;

import controller.Controller;
import javafx.scene.control.MenuItem;
import javafx.stage.FileChooser;
import javafx.stage.FileChooser.ExtensionFilter;

public class FileLoaderMenuItem extends MenuItem {

	public FileLoaderMenuItem(Controller controller) {
		this.setText("load file");
        this.setOnAction(
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
	}

}
