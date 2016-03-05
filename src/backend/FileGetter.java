package backend;

import java.io.IOException;

public interface FileGetter {
	
	public String getFileText(String fileName) throws IOException;
	
}
