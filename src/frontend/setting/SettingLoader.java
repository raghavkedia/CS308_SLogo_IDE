package frontend.setting;

import java.io.File;
import java.io.IOException;
import java.util.Properties;

import frontend.FrontendManager;
import frontend.ExceptionWindow.IOExceptionWindow;
import frontend.setting.SettingSaver.SettingString;
import javafx.scene.paint.Color;
import util.PropertyHandler;

public class SettingLoader implements IFrontEndSettingLoader{
	private FrontendManager myFrontendManager;
	private Properties myProps;
	
	
	public SettingLoader(FrontendManager frontendManager, File file) {
		myFrontendManager = frontendManager;
		try {
			myProps = PropertyHandler.load(file);
			loadBGCol();
		} catch (IOException e) {
			IOExceptionWindow.display(e);
		}
	}
	@Override
	public void loadBGCol() {
		String bgCol = myProps.getProperty(SettingString.BG_COL_KEY.getKey());
		Color c = Color.web(bgCol);
		myFrontendManager.changeBackgroundColor(c);
	}

}
