package frontend.GUI;

import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebView;
import javafx.stage.Stage;

/**
 * Web popup page
 * 
 * @author Jiangzhen Yu
 */

public class WebHelp{
	private WebView myWebView;
	private Group myGroup;
	private Stage myStage;	
	
	/**
	 * Constructor.
	 * 
	 * @param url: URL string of the web page
     * @param title: title of the popup stage window
	 */
	public WebHelp(String url, String title) {
		myStage = new Stage();
		myGroup = new Group();
		myWebView = new WebView();
		WebEngine webEngine = myWebView.getEngine();
		webEngine.load(url);
		myGroup.getChildren().add(myWebView);
		Scene scene = new Scene(myGroup);
		myStage.setTitle(title);
		myStage.setScene(scene);
		myStage.show();	
	}
}
