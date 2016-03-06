package frontend.workspace;

import java.util.HashMap;
import java.util.Map;
import java.util.Properties;


import com.sun.prism.paint.Color;

import backend.InterpreturInterface;
import controller.Controller;
import frontend.FrontendManager;
import frontend.toobar.ToolbarComponent;
import javafx.scene.control.Button;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;


public class WorkSpaceManager implements IWorkSpace{
	private TabPane tabPane;
	private Controller myController;
	private Properties myGUIProp;
	private Properties myLangProp;
	private InterpreturInterface myBackend;
	private Map<Double, FrontendManager> myFrontendManagers;
	private int numOfWorkSpace;
	
	public WorkSpaceManager(Properties GUIProp, Properties LangProp, InterpreturInterface backend, Controller c) {
		tabPane = new TabPane();
		myFrontendManagers = new HashMap<Double, FrontendManager>();
		myController = c;
		myGUIProp = GUIProp;
		myLangProp = LangProp;
		myBackend = backend;
		
		numOfWorkSpace = 0;
		createWorkSpace();
		createWorkSpace();
		createWorkSpace();
		
		
	}
	
	@Override
	public void createWorkSpace() {
		numOfWorkSpace ++;
		Tab tab = new Tab();
		tab.setText("workspace " + numOfWorkSpace);
		FrontendManager frontendManager = myController.getFrontendManager();
		myFrontendManagers.put((double) frontendManager.getId(), frontendManager);
		tab.setContent(new Button( " " + numOfWorkSpace));
//		tab.setContent(frontendManager.getMyBorderPane());
//		ToolbarComponent tb = new ToolbarComponent(null, myController);
//		tab.setContent(tb.getVisual());
		
		tabPane.getTabs().add(tab);
	}
	
	public TabPane getTabPane() {return tabPane;}
}
