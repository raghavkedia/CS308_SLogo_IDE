package frontend.setting;

import java.io.File;
import java.io.IOException;
import java.util.Map;
import java.util.Properties;

import backend.data.Character;
import frontend.FrontendManager;
import frontend.ExceptionWindow.IOExceptionWindow;
import util.PropertyHandler;
import backend.data.Character;

public class SettingSaver implements IFrontEndSettingSaver {
	private FrontendManager myFrontendManager;
	private Properties myProps;
	
	public enum SettingString {
		BG_COL_KEY("background_color"),
		CHARACTER_NUMS("number_of_character(s)"),
		CHARACTER_ID("ID"),
		CHARACTER_X("X"),
		CHARACTER_Y("Y"),
		CHARACTER_ANGLE("angle"),
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
		saveChar();
		try {
			PropertyHandler.save(myProps, file);
		} catch (IOException e) {
			IOExceptionWindow.display(e);
		}
	}
    
	@Override
	public void saveBGCol() {
		String bgCol = myFrontendManager.getBackgroundRGB();
		myProps.setProperty(SettingString.BG_COL_KEY.getKey(), bgCol);
	}
	
	@Override
	public void saveChar() {
		Map<Integer, Character> myCharMap = myFrontendManager.getCharMap();
		int numOfCha = myCharMap.size();
		myProps.setProperty(SettingString.CHARACTER_NUMS.getKey(), String.valueOf(numOfCha));
		for (int i = 0; i < numOfCha; i++) {
			Character c = myCharMap.get(i);
			String key = SettingString.CHARACTER_ID.getKey() + i;
			myProps.setProperty(key, c.getName());
		}
	}
	
}
