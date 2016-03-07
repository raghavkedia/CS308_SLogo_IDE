package util;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;



public class FileHandler {
	public static void saveFile(String content, File file) {
    	try {
            FileWriter fileWriter = new FileWriter(file);
            fileWriter.write(content);
            fileWriter.close();   
    	} catch (IOException ex) {
    		Logger.getLogger(FileHandler.class.getName()).log(Level.SEVERE, null, ex);
    	}
	}
	
	public static String getFileText(File file) throws IOException{
		return new Scanner(file).useDelimiter("\\Z").next();
	}	
}
