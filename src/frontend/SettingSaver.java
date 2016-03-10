package frontend;

import java.io.File;
import java.util.Properties;

import util.PropertySaver;

public class SettingSaver implements IFrontEndSettingSaver {
	private FrontendManager myFrontendManager;
	private Properties myProps;
	private static final String BG_COL_KEY = "background_color";
	
	
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
		myProps.setProperty(BG_COL_KEY, bgCol);
	}
}
