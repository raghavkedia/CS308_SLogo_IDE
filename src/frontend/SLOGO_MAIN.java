package frontend;

import javafx.application.Application;
import javafx.stage.Stage;

public class SLOGO_MAIN extends Application{
	
	private static FrontendManager myFrontendManager;

	@Override
	public void start(Stage s){
		myFrontendManager = new FrontendManager();
		s.setScene(myFrontendManager.getMyScene());
		s.setTitle("SLOGO IDE");
		s.show();
	}
	
	public static void main(String[] args) {
		launch(args);
	}

}
