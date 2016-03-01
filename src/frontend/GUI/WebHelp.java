package frontend.GUI;

import java.util.Properties;

import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebView;
import javafx.stage.Stage;

public class WebHelp extends Application{
	private static final String HELP_TITLE = "help_title";
	private static final String HELP_URL = "help_url";
	private WebView myWebView;
	private Group myGroup;
	private Properties myGUIProp;
	
	
	public WebHelp(Properties prop) {
		myGUIProp = prop;
		myGroup = new Group();
		myWebView = new WebView();
		WebEngine webEngine = myWebView.getEngine();
		String url = myGUIProp.getProperty(HELP_URL);
		webEngine.load(url);
		myGroup.getChildren().add(myWebView);
		
	}
	
	@Override
	public void start(Stage stage) throws Exception {
		try{
			Scene scene = new Scene(myGroup);
			stage.setTitle(myGUIProp.getProperty(HELP_TITLE));
			stage.setScene(scene);
			stage.show();			
		} catch(Exception e){
			System.out.print("error in web help");
			e.printStackTrace();
		}
	}
	
	
}
