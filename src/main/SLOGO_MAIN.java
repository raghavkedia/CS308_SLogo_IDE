package main;

import frontend.GUI.Init;
import javafx.application.Application;
import javafx.stage.Stage;

public class SLOGO_MAIN extends Application{
	
	@Override
	public void start(Stage s){		
		new Init(s);
		s.show();
	}
	
	public static void main(String[] args) {
		launch(args);
	}

}
