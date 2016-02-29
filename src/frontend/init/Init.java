package frontend.init;

import java.io.IOException;
import java.util.Properties;

import frontend.FrontendManager;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.event.ActionEvent;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;
import util.PropertyLoader;

public class Init {
	private static final String LANG_PATH = "languages/";
	private static final String LANG_OPTION = LANG_PATH + "languageOption";
	private static final String LANG_DEFAULT = "default_lang";
	private static final String BUTTON_NAME = "START";
	private static final int SCENE_HEIGHT = 500;
	private static final int SCENE_WIDTH = 300;
	private Stage myStage;
	private Scene myScene;
	private Group myGroup;
	private ComboBox<String> myComboBox;
	
	public Init(Stage s) {
		myStage = s;
		myGroup = new Group();
		myScene = new Scene(myGroup, SCENE_HEIGHT, SCENE_WIDTH);
		myComboBox = makeLanguageBox();
		makeStartButton();
		display();
	}
	
	private void display() {
		myStage.setScene(myScene);
		myStage.centerOnScreen();
	}

	
    private ComboBox<String> makeLanguageBox() {
    	ComboBox<String> comboBox = new ComboBox<String>();
    	try {
    		Properties prop = PropertyLoader.load(LANG_OPTION);   		
    		for(String key : prop.stringPropertyNames()) {
    			  String value = prop.getProperty(key);
    			  comboBox.getItems().add(value);
    		}
    		comboBox.setValue(prop.getProperty(LANG_DEFAULT));
    		comboBox.setTranslateX(SCENE_WIDTH * 2/3);
    		comboBox.setTranslateY(SCENE_HEIGHT/3);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	myGroup.getChildren().add(comboBox);
    	return comboBox;
    }
    

    private Button makeStartButton() {
    	Button startButton = new Button(BUTTON_NAME);
		startButton.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				try {
					Properties prop = PropertyLoader.load(LANG_PATH  + myComboBox.getValue());
					FrontendManager fm = new FrontendManager(prop);
					myStage.setScene(fm.getMyScene());
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} 
			}
		});
		startButton.setTranslateX(SCENE_WIDTH * 2/3);
		startButton.setTranslateY(SCENE_HEIGHT/2);
		myGroup.getChildren().add(startButton);
    	return startButton;
    }
}