package frontend.workspace;

import java.util.Properties;

import frontend.FrontendManager;
import javafx.scene.Parent;

public interface IWorkSpace {
	public void createWorkSpace();

	public Parent getTabPane();

	public FrontendManager getSelectedFrontendManager();
	public String getGUIProperty(String s);
	public Properties getLangProperty();
}
