package frontend.GUI;

import java.io.IOException;
import java.util.Properties;

import controller.Controller;
import frontend.ComponentFactory;
import frontend.FrontendManager;
<<<<<<< HEAD

=======
import frontend.menubar.MenubarComponent;
import frontend.toobar.ToolbarComponent;
import frontend.workspace.WorkSpaceManager;
>>>>>>> d0a3f95c59932a96011d84b0630684def693a5ae
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.event.ActionEvent;
import javafx.stage.Stage;
import util.PropertyLoader;

public class Init {
	private static final String LANG_PATH = "languages/";
	private static final String LANG_OPTION = LANG_PATH + "languageOption";
	private static final String LANG_DEFAULT = "default_lang";
	private static final String BUTTON_NAME = "start_button";
	private static final String GUI_FILE_NAME = "GUI";
	private static final String TITLE = "title";
	private static final int SCENE_HEIGHT = 500;
	private static final int SCENE_WIDTH = 300;
	private Stage myStage;
	private Scene myScene;
    private VBox myBox;
	private ComboBox<String> myComboBox;
	private Button myStartButton;
	private Properties myGUIProp; 
	
	public Init(Stage s) throws IOException {
		myStage = s;
		myGUIProp = PropertyLoader.load(GUI_FILE_NAME);
		s.setTitle(myGUIProp.getProperty(TITLE));
		myBox = new VBox();
		myBox.setAlignment(Pos.CENTER);
		myScene = new Scene(myBox, SCENE_HEIGHT, SCENE_WIDTH);
		
		myComboBox = makeLanguageBox();	
		myBox.getChildren().add(myComboBox);
		myStartButton = makeStartButton();
		myBox.getChildren().add(myStartButton);
		display();
	}
	
	private void display() {
		myStage.setScene(myScene);
		myStage.centerOnScreen();
	}

	
    private ComboBox<String> makeLanguageBox() throws IOException {
    	ComboBox<String> comboBox = new ComboBox<String>();

		Properties prop = PropertyLoader.load(LANG_OPTION);   		
		for(String key : prop.stringPropertyNames()) {
			  String value = prop.getProperty(key);
			  comboBox.getItems().add(value);
		}
		comboBox.setValue(prop.getProperty(LANG_DEFAULT));
    	return comboBox;
    }
    

    private Button makeStartButton() {
    	Button startButton = new Button(myGUIProp.getProperty(BUTTON_NAME));
		startButton.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				try {
<<<<<<< HEAD
					Properties prop = PropertyLoader.load(LANG_PATH  + myComboBox.getValue());
//					FrontendManager fm = new FrontendManager(myGUIProp, prop, myStage);
					Controller theControl = new Controller(myGUIProp, prop, myStage);
					myStage.setScene(theControl.getFrontendManager().getMyScene());
=======
					initMainScene();
					display();
//					myStage.setResizable(false);
>>>>>>> d0a3f95c59932a96011d84b0630684def693a5ae
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		});
    	return startButton;
    }
    
    private void initMainScene() throws IOException {
    	Properties prop = PropertyLoader.load(LANG_PATH  + myComboBox.getValue());
		Controller theControl = new Controller(myGUIProp, prop, myStage);
        myBox = new VBox();
		myScene = new Scene(myBox, 1000, 800);
		MenubarComponent menubarComp = ComponentFactory.makeNewMenubar(theControl);
		myBox.getChildren().add(menubarComp.getVisual());
		ToolbarComponent toolbarComp = ComponentFactory.makeNewToolbar(theControl);
		myBox.getChildren().add(toolbarComp.getVisual());
		
		myBox.getChildren().add(theControl.getWorkSpaceManager().getTabPane());
    }
}
