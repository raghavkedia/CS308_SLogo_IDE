package backend;

import java.util.List;

import javafx.scene.image.Image;

public interface Updateable {
	
	public void setCurrCoord(int x, int y);
	public void setName(String name);
	public void setImage(Image image);
	public void setVisability(boolean visability);
	public void setPenState(boolean penState);
	public void addLine(List<int[]> line);
	public int getCoordX();
	public int getCoordY();
	public String getName();
	public boolean getVisability();
	public boolean getPenState();
	public List<List<int[]>> getLines();
	
}
