package util;

import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.util.Properties;

public class PropertySaver {
	public static void save(Properties props, String fileName) {
	    try {
	    	fileName = fileName + PropertyLoader.EXTENSION;
	    	System.out.println(fileName);
	        File f = new File(fileName);
	        OutputStream out = new FileOutputStream( f );
	        props.store(out, "This is an optional header comment string");
	    }
	    catch (Exception e ) {
	        e.printStackTrace();
	    }
	}
	
}
