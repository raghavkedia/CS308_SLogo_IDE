package backend;

import java.util.List;

import javafx.scene.image.Image;

public interface Getable {
	
	public void addLine(List<int[]> line);
	public int getCoordX();
	public int getCoordY();
	public String getName();
	public boolean getVisability();
	public boolean getPenState();
	public List<List<int[]>> getLines();
	
}
