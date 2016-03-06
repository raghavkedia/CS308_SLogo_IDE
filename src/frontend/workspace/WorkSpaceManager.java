package frontend.workspace;

import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

import backend.InterpreturInterface;
import controller.Controller;
import frontend.FrontendManager;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;


public class WorkSpaceManager implements IWorkSpace{
	private TabPane tabPane;
	private Controller myController;
	private Properties myGUIProp;
	private Properties myLangProp;
	private InterpreturInterface myBackend;
	private Map<Double, FrontendManager> myFrontendManagers ;
	
	public WorkSpaceManager(Properties GUIProp, Properties LangProp, InterpreturInterface backend, Controller c) {
		tabPane = new TabPane();
		myFrontendManagers = new HashMap<Double, FrontendManager>();
		createWorkSpace();
		myController = c;
		myGUIProp = GUIProp;
		myLangProp = LangProp;
		myBackend = backend;
	}
	
	@Override
	public void createWorkSpace() {
		Tab tab = new Tab();
		tab.setText("workspace " + myFrontendManagers.size());
		FrontendManager frontendManager = new FrontendManager(myGUIProp, myLangProp, myBackend, myController);
//		myFrontendManagers.put(frontendManager.getId(), frontendManager);
		
		tab.setContent(frontendManager.getMyBorderPane());
		tabPane.getTabs().add(tab);
	}
	
	public TabPane getTabPane() {return tabPane;}
}
