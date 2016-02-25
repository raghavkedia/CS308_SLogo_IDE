package frontend;

import java.util.List;

abstract class ListView extends VisualComponent{
	private List<Object> myList;
	
	public ListView(double width, double height){
		super(width, height);
	}
}
