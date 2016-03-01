package frontend;

import java.io.IOException;

import frontend.GUI.Init;
import javafx.application.Application;
import javafx.stage.Stage;

public class SLOGO_MAIN extends Application{
	
	@Override
	public void start(Stage s){		
		try {
			new Init(s);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		s.show();
	}
	
	public static void main(String[] args) {
		launch(args);
	}

}
