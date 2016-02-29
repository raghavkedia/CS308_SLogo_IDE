package util;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class PropertyLoader {
	private static final String PATH = "resources/";
	private static final String EXTENSION = ".properties";

	
	public static Properties load(String fileName) throws IOException {
		fileName = PATH + fileName + EXTENSION;
		Properties prop = new Properties();
     	InputStream input = PropertyLoader.class.getClassLoader().getResourceAsStream(fileName);
		if (input == null) {
			throw new FileNotFoundException("No such file" + fileName);
		}
		prop.load(input);
		input.close();
		return prop;
	}
	
//	public static void main(String[] args) {
//		try {
//		       System.out.println("Working Directory = " +
//		               System.getProperty("user.dir"));
//			Properties prop = PropertyLoader.load("languages/English");
//			System.out.println(prop.getProperty("Forward"));
//		
//		} catch (IOException e) {
//			System.out.println("No file");
//		}
//	}
}
