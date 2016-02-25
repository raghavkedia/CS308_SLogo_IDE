package backend;

import java.util.List;

public abstract class Result {

	private String myString;
	private List<String> myList;
	private int[] myArr;
	private double myDouble;
	
	public String getMyString() {
		return myString;
	}
	public void setMyString(String myString) {
		this.myString = myString;
	}
	public List<String> getMyList() {
		return myList;
	}
	public void setMyList(List<String> myList) {
		this.myList = myList;
	}
	public int[] getMyArr() {
		return myArr;
	}
	public void setMyArr(int[] myArr) {
		this.myArr = myArr;
	}
	public double getMyDouble() {
		return myDouble;
	}
	public void setMyDouble(double myDouble) {
		this.myDouble = myDouble;
	}

}
