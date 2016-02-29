package frontend;

import frontend.init.Init;
import javafx.application.Application;
import javafx.stage.Stage;

public class SLOGO_MAIN extends Application{
	private static final String TILTE = "SLOGO IDE";
	@Override
	public void start(Stage s){		
		new Init(s);
		s.setTitle(TILTE);
		s.show();
	}
	
	public static void main(String[] args) {
		launch(args);
	}

}
