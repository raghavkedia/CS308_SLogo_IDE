package backend;

import java.io.IOException;

public interface FileSaver {
	
	public void saveFile(String fileName, String fileText) throws IOException;
	
}
