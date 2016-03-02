package frontend.GUI;


import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebView;
import javafx.stage.Stage;

public class WebHelp extends Application{
    private String myTitle;
	private WebView myWebView;
	private Group myGroup;

	
	public WebHelp(String url, String tilte) {
		myGroup = new Group();
		myWebView = new WebView();
		WebEngine webEngine = myWebView.getEngine();
		webEngine.load(url);
		myGroup.getChildren().add(myWebView);
		
	}
	
	@Override
	public void start(Stage stage) throws Exception {
		try{
			Scene scene = new Scene(myGroup);
			stage.setTitle(myTitle);
			stage.setScene(scene);
			stage.show();			
		} catch(Exception e){
			System.out.print("error in web help");
			e.printStackTrace();
		}
	}
	
	
}
