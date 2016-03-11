package frontend.setting;

import java.io.File;
import java.io.IOException;
import java.util.Properties;

import frontend.FrontendManager;
import javafx.scene.paint.Color;
import util.PropertyLoader;

public class SettingLoader implements IFrontEndSettingLoader{
	private FrontendManager myFrontendManager;
	private Properties myProps;
	
	
	public SettingLoader(FrontendManager frontendManager, File file) {
		myFrontendManager = frontendManager;
		try {
			myProps = PropertyLoader.load(file);
			loadBGCol();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	@Override
	public void loadBGCol() {
		String bgCol = myProps.getProperty(SettingSaver.BG_COL_KEY);
		Color c = Color.web(bgCol);
		myFrontendManager.changeBackgroundColor(c);
	}

}