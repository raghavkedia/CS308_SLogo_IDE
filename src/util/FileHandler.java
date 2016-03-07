package util;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

import java.util.logging.Level;
import java.util.logging.Logger;



public class FileHandler implements FileSaver, FileGetter{

	public FileHandler() {
		// TODO Auto-generated constructor stub
	}
	
	public void saveFile(String fileName, String fileText) throws IOException{
		File file = new File(fileName);
		BufferedWriter out = new BufferedWriter(new FileWriter(file));
		out.write(fileText);
		out.close();
	}
	
    public static void saveFile(String content, File file) {
    	try {
            FileWriter fileWriter = new FileWriter(file);
            fileWriter.write(content);
            fileWriter.close();   
    	} catch (IOException ex) {
    		Logger.getLogger(FileHandler.class.getName()).log(Level.SEVERE, null, ex);
    	}
       
    }
	
	public String getFileText(String fileName) throws IOException{
		BufferedReader br = new BufferedReader(new FileReader(fileName));
	    try {
	        StringBuilder sb = new StringBuilder();
	        String line = br.readLine();

	        while (line != null) {
	            sb.append(line);
	            sb.append("\n");
	            line = br.readLine();
	        }
	        return sb.toString();
	    } finally {
	        br.close();
	    }
	}
	
}
