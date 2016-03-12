package frontend.GUI;

import java.io.IOException;
import java.util.Properties;

import controller.Controller;
import frontend.ComponentFactory;
import frontend.ExceptionWindow.IOExceptionWindow;
import frontend.menubar.MenubarComponent;
import frontend.toobar.ToolbarComponent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.layout.VBox;
import javafx.event.ActionEvent;
import javafx.stage.Stage;
import util.PropertyHandler;


/**
 * Init class to let user to choose command language and start the program
 * 
 * @author Jiangzhen Yu
 */
public class Init {
	private static final String LANG_PATH = "languages/";
	private static final String LANG_OPTION = LANG_PATH + "languageOption";
	private static final String LANG_DEFAULT = "default_lang";
	private static final String GUI_FILE_NAME = "GUI";
	private Stage myStage;
	private Scene myScene;
    private VBox myBox;
	private ComboBox<String> myComboBox;
	private Button myStartButton;
	private Properties myGUIProp; 
	
	
	/**
	 * enums for properties in GUI.properties file for display
	 *
	 */
	public enum GUIString {
		TITLE("title"),
	    START("start"),
	    HELP("help"),
		CLEAR("clear"),
        WORKSPACE("workspace"),
		RUN("run"),
		PORTRAIT("portrait"),
		PEN("pen"),
		HELP_TITLE("help_title"),
		HELP_URL("help_url"),
		PORTRAIT_TITLE("portrait_title"),
	
		WORKSPACE_TABNAME("workspace_tabname"),
		FILE_MENU("file_menu"),
		FILE_MENU_LOAD("file_menu_load"),
		FILE_MENU_SAVE("file_menu_save"),
		FILE_LOAD_TITLE("file_load_tilte"),
		FILE_SAVE_TITLE("file_save_title"),
		SETTING_MENU("setting_menu"),
		SETTING_MENU_LOAD("setting_menu_load"),
		SETTING_MENU_SAVE("setting_menu_save"),
		SETTING_LOAD_TITLE("setting_load_tilte"),
		SETTING_SAVE_TITLE("setting_save_title"),
		;
		private final String key;
		
		private GUIString(String keyName) {
			this.key = keyName;
		}	
		
		public String getKey() {
			return this.key;
		}		
	}
	
	/**
	 * enums for dimension of GUI
	 *
	 */
	public enum Dimension {
		INIT_SCENE_HEIGHT(500),
		INIT_SCENE_WIDTH(300),
		MAIN_SCENE_HEIGHT(1000),
		MAIN_SCENE_WIDTH(800),
		;
		private final int value;
		
		Dimension(int val) {
			this.value = val;
		}	
		
		public int getDim() {
			return this.value;
		}		
	}	
	public Init(Stage s) {
		myStage = s;
		try {
			myGUIProp = PropertyHandler.load(GUI_FILE_NAME);
			s.setTitle(myGUIProp.getProperty(GUIString.TITLE.key));
			myBox = new VBox();
			myBox.setAlignment(Pos.CENTER);
			myScene = new Scene(myBox, Dimension.INIT_SCENE_HEIGHT.getDim(), Dimension.INIT_SCENE_WIDTH.getDim());
			
	
			myComboBox = makeLanguageBox();
	
			myBox.getChildren().add(myComboBox);
			
			String myStartButtonLabel = myGUIProp.getProperty(GUIString.START.key);
			myStartButton = ComponentFactory.makeButton(myStartButtonLabel, initMainScene());
			myBox.getChildren().add(myStartButton);
			
			display();
	    } catch (IOException e) {
	    	IOExceptionWindow.display(e);
		}
	}
	
	private void display() {
		myStage.setScene(myScene);
		myStage.centerOnScreen();
	}
	
    /**
     * make the language ComboBox for available command language
     */
	
    private ComboBox<String> makeLanguageBox() throws IOException {
    	ComboBox<String> comboBox = new ComboBox<String>();
		Properties prop = PropertyHandler.load(LANG_OPTION);
		prop.stringPropertyNames().stream()
		                          .forEach(key -> {
		                        	  String value = prop.getProperty(key);
		                        	  comboBox.getItems().add(value);
		                          });
		comboBox.setValue(prop.getProperty(LANG_DEFAULT));
    	return comboBox;
    }
    
    /**
     * Initialize the main scene of the program when start button is pressed
     * 
     * @return EventHandler when start button is pressed
     */
    
    private EventHandler<ActionEvent> initMainScene() {
    	return new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
		    	Properties prop;
				try {
					prop = PropertyHandler.load(LANG_PATH + myComboBox.getValue());
					Controller theControl = new Controller(myGUIProp, prop, myStage);
			        myBox = new VBox();
					myScene = new Scene(myBox, Dimension.MAIN_SCENE_HEIGHT.getDim(), Dimension.MAIN_SCENE_WIDTH.getDim());
					MenubarComponent menubarComp = ComponentFactory.makeNewMenubar(theControl);
					myBox.getChildren().add(menubarComp.getVisual());
					ToolbarComponent toolbarComp = ComponentFactory.makeNewToolbar(theControl);
					myBox.getChildren().add(toolbarComp.getVisual());
					
					myBox.getChildren().add(theControl.getWorkSpaceManager().getTabPane());
					display();
				} catch (IOException e) {
					IOExceptionWindow.display(e);
				}
			}
    	};
    }
}