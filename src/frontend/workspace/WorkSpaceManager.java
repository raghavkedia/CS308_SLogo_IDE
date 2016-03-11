package frontend.workspace;

import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

import backend.InterpreturInterface;
import controller.Controller;
import frontend.FrontendManager;
import frontend.GUI.Init.GUIString;
import javafx.scene.control.SingleSelectionModel;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;


public class WorkSpaceManager implements IWorkSpace{
	private TabPane tabPane;
	private Controller myController;
	private Properties myGUIProp;
	private Properties myLangProp;
	private InterpreturInterface myBackend;
	private Map<Integer, FrontendManager> myFrontendManagers;
	private int numOfWorkSpaceCreated;
	
	public WorkSpaceManager(Properties GUIProp, Properties LangProp, InterpreturInterface backend, Controller c) {
		tabPane = new TabPane();
		myFrontendManagers = new HashMap<Integer, FrontendManager>();
		myController = c;
		myGUIProp = GUIProp;
		myLangProp = LangProp;
		myBackend = backend;
		
		numOfWorkSpaceCreated = 0;
		createWorkSpace();
		SingleSelectionModel<Tab> selectionModel = tabPane.getSelectionModel();
		selectionModel.select(0);
		tabPane.getSelectionModel().getSelectedItem(); //tab	
	}
	

	
	@Override
	public void createWorkSpace() {
		Tab tab = new Tab();
		String tabname = getGUIProperty(GUIString.WORKSPACE_TABNAME.getKey());
		tab.setText(tabname + numOfWorkSpaceCreated);
		myBackend.addWorkSpace(numOfWorkSpaceCreated+ 1);
		FrontendManager frontendManager = new FrontendManager(myBackend, myController, 
				numOfWorkSpaceCreated);
		myFrontendManagers.put(frontendManager.getId(), frontendManager);

		tab.setContent(frontendManager.getMyBorderPane());
        tab.setId(String.valueOf(frontendManager.getId()));
        

        tab.setOnSelectionChanged(e -> {
        	myController.selectionResponse(myFrontendManagers.get(Integer.valueOf(tab.getId())));
        	});
        tab.setOnCloseRequest(e -> {
//        	myFrontendManagers.remove(Integer.valueOf(tab.getId()));
//        	tabPane.getSelectionModel().select(0);
        	});
        
		tabPane.getTabs().add(tab);
		numOfWorkSpaceCreated++;
		tabPane.getSelectionModel().select(tab);
	}
	
	@Override
	public TabPane getTabPane() {return tabPane;}
	
	@Override
	public FrontendManager getSelectedFrontendManager() {
		Tab selectedTab = tabPane.getSelectionModel().getSelectedItem(); 
		return myFrontendManagers.get(Integer.valueOf(selectedTab.getId()));
	}
	
	@Override
	public String getGUIProperty(String s) {
		return myGUIProp.getProperty(s);
	}
	
	@Override
	public Properties getLangProperty() {
		return myLangProp;
	}
}
