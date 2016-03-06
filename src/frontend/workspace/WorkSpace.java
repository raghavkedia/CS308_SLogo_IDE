package frontend.workspace;

import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;

public class WorkSpace implements IWorkSpace{
	private int WORKSPACE_NUMBER = 0;
	private TabPane tabPane;
	
	public WorkSpace() {
		tabPane = new TabPane();
	}
	
	@Override
	public void createWorkspace() {
		// TODO Auto-generated method stub
		Tab tab = new Tab();
		tab.setText("workspace" + String.valueOf(WORKSPACE_NUMBER + 1));
		tab.setContent();
		tabPane.getTabs().add(tab);
	}

}
