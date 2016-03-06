package frontend.workspace;

import frontend.FrontendManager;
import javafx.scene.Parent;

public interface IWorkSpace {
	public void createWorkSpace();

	public Parent getTabPane();

	public FrontendManager getSelectedFrontendManager();

}
