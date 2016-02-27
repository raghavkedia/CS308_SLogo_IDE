package frontend;

public class ComponentFactory {
	public static LabeledButton makeNewLabeledButton(double width, double height){
		return new LabeledButton(width, height);
	}
	
	public static Display makeNewDisplay(double width, double height){
		return new Display(width, height);
	}
	
	public static History makeNewHistory(double width, double height){
		return new History(width, height);
	}
	
	public static Console makeNewConsole(double width, double height){
		return new Console(width, height);
	}
}
