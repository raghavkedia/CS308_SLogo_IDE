package frontend.setting;

import java.io.File;
import java.util.Properties;

import frontend.FrontendManager;
import util.PropertySaver;

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
		PropertySaver.save(myProps, file);
	}
    
	@Override
	public void saveBGCol() {
		String bgCol = myFrontendManager.getBackgroundRGB();
//		System.out.println("my current rgb " + bgCol + ", " + bgCol.length());
		myProps.setProperty(SettingString.BG_COL_KEY.getKey(), bgCol);
	}
}
