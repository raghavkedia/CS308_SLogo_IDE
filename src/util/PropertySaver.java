package util;

import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.util.Properties;

public class PropertySaver {
	public static void save(Properties props, File file) {
	    try {
	        OutputStream out = new FileOutputStream(file);
	        props.store(out, "This is an optional header comment string");
	    }
	    catch (Exception e ) {
	        e.printStackTrace();
	    }
	}
	
}
