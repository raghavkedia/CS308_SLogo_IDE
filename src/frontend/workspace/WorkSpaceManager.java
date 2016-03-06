package frontend.workspace;

import java.util.Properties;

import backend.InterpreturInterface;
import controller.Controller;
import frontend.FrontendManager;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.stage.Stage;

public class WorkSpaceManager implements IWorkSpace{
	private int WORKSPACE_NUMBER = 0;
	private TabPane tabPane;
	private Controller myController;
	private Properties myGUIProp;
	private Properties myLangProp;
	private Stage myStage;
	private InterpreturInterface myBackend;
	
	public WorkSpaceManager(Properties GUIProp, Properties LangProp, InterpreturInterface backend, Stage stage, Controller c) {
		tabPane = new TabPane();
		createWorkSpace();
		myController = c;
		myGUIProp = GUIProp;
		myLangProp = LangProp;
		myBackend = backend;
		myStage = stage;
		
	}
	
	@Override
	public void createWorkSpace() {
		Tab tab = new Tab();
		tab.setText("workspace" + String.valueOf(WORKSPACE_NUMBER + 1));
		FrontendManager frontendManager = new FrontendManager(myGUIProp, myLangProp, myBackend, myController);
		tab.setContent(frontendManager.getMyBorderPane());
		tabPane.getTabs().add(tab);
	}
	
	public TabPane getTabPane() {return tabPane;}
}
