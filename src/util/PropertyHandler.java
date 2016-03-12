package util;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Properties;

public class PropertyHandler {
	public static final String PATH = "resources/";
	public static final String EXTENSION = ".properties";
	public static final String ERROR_MESSAGE = "No such property file";

	//Loading Methods
	public static Properties load(File file) throws IOException {
		InputStream input = new FileInputStream(file);
		return convertToProp(input);
	}
	
	public static Properties load(String fileName) throws IOException {
		fileName = PATH + fileName + EXTENSION;
     	InputStream input = PropertyHandler.class.getClassLoader().getResourceAsStream(fileName);
		return convertToProp(input);
	}
	
	private static Properties convertToProp(InputStream input) throws IOException {
		if (input == null) {
			throw new FileNotFoundException(ERROR_MESSAGE);
		}
		Properties prop = new Properties();
		prop.load(input);
		input.close();
		return prop;
	}
	
	//Saving Methods
	public static void save(Properties props, File file) throws IOException {
	      OutputStream out = new FileOutputStream(file);
	      props.store(out, "This is an optional header comment string");	    
	}
}
