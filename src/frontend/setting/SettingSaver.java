package frontend.setting;

import java.io.File;
import java.io.IOException;
import java.util.Properties;

import frontend.FrontendManager;
import frontend.ExceptionWindow.IOExceptionWindow;
import util.PropertyHandler;


public class SettingSaver implements IFrontEndSettingSaver {
	private FrontendManager myFrontendManager;
	private Properties myProps;
	
	public enum SettingString {
		BG_COL_KEY("background_color")
		;
		private final String key;
		
		private SettingString(String keyName) {
			this.key = keyName;
		}		
		
		public String getKey() {
			return this.key;
		}		
	}
	
	public SettingSaver(FrontendManager frontendManager, File file) {
		myFrontendManager = frontendManager;
		myProps = new Properties();
		saveBGCol();
		try {
			PropertyHandler.save(myProps, file);
		} catch (IOException e) {
			IOExceptionWindow.display(e);
		}
	}
    
	@Override
	public void saveBGCol() {
		String bgCol = myFrontendManager.getBackgroundRGB();
//		System.out.println("my current rgb " + bgCol + ", " + bgCol.length());
		myProps.setProperty(SettingString.BG_COL_KEY.getKey(), bgCol);
	}
}
