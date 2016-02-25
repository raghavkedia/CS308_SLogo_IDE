package backend;

import javafx.scene.image.Image;

public interface Setable {
	
	public void setCurrCoord(int x, int y);
	public void setName(String name);
	public void setImage(Image image);
	public void setVisability(boolean visability);
	public void setPenState(boolean penState);
	
}
