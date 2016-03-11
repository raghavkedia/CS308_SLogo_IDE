package util;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class PropertyLoader {
	public static final String PATH = "resources/";
	public static final String EXTENSION = ".properties";
	public static final String ERROR_MESSAGE = "No such property file";

	public static Properties load(File file) throws IOException {
		InputStream input = new FileInputStream(file);
		return convertToProp(input);
	}
	
	public static Properties load(String fileName) throws IOException {
		fileName = PATH + fileName + EXTENSION;
     	InputStream input = PropertyLoader.class.getClassLoader().getResourceAsStream(fileName);
		return convertToProp(input);
	}
	
	public static Properties convertToProp(InputStream input) throws IOException {
		if (input == null) {
			throw new FileNotFoundException(ERROR_MESSAGE);
		}
		Properties prop = new Properties();
		prop.load(input);
		input.close();
		return prop;
	}
//	
//	public static void main(String[] args) {
//		try {
//			Properties prop = PropertyLoader.load("default");
//			System.out.println(prop.getProperty("background_color").length());
//		
//		} catch (IOException e) {
//			System.out.println("No file");
//		}
//	}
}
