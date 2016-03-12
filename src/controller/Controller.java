package controller;


import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import backend.*;
import backend.data.Character;
import backend.data.Data.PenPattern;
import exceptions.SlogoError;
import frontend.FrontendManager;
import frontend.workspace.IWorkSpace;
import frontend.workspace.WorkSpaceManager;
import javafx.scene.paint.Color;
import javafx.scene.shape.Line;
import javafx.stage.Stage;

public class Controller {
	private BackendManager myBackend;
    private FrontendManager myFrontend;
    
//	private ArrayList<InterpreturInterface> myBackendList;
	private int myId;

	private IWorkSpace myWorkSpace;
	private Stage myStage; //Testing, should be deleted later
	
	public Controller(Properties GUIProp, Properties myProp, Stage s){
		myId = 0;
		myBackend = new BackendManager();

//		myFrontend = new FrontendManager(GUIProp, myProp, myBackend, this, myId);
//		myFrontendList = new ArrayList<FrontendManager>();
//		myFrontendList.add(myFrontend);

		myStage = s;
		myWorkSpace = new WorkSpaceManager(GUIProp, myProp, myBackend, this);
		initColorMap();
		initShapeMap();
	}
	

	
	//WORKSPACE

	public void createWorkSpace() {
		myWorkSpace.createWorkSpace();
	}
	
	public void selectionResponse(FrontendManager frontendManager) {
		myFrontend = frontendManager;
		myId = myFrontend.getId();
	}
	
	public Properties getLangProperty() {
		return myWorkSpace.getLangProperty();
	}
	
	//FRONTEND METHODS	
	
	//CONSOLE
    public void passConsoleInput(String s){
    	String output = null;
    	try {
			output = myBackend.executeCommand(s, myId);
			myFrontend.resetHistoryPointer();
		} catch (SlogoError e) {
			// TODO Auto-generated catch block
			output = e.getMessage();
			//e.printStackTrace();
		}
    	
    	if (output != null){
    		myFrontend.showOutput(output);
    	}
    }
    public void displayInConsole(String input){myFrontend.displayInConsole(input);}
	public void clearConsole() { myFrontend.clearConsole(); }
	public void executeConsole() {myFrontend.executeConsole();}
	public String getConsoleText() { return myFrontend.getConsoleText();}
    
    //VARIABLES
    public void addToVariables(String s){myFrontend.addToVariables(s);}
    public void clearVariablesFrontend(){myFrontend.clearAllVars();}
    
    public void updateVariableValue(String var, String value){
    	try {
			myBackend.executeCommand("make :"+var + " " + value, myId);
		} catch (SlogoError e) {
			// TODO Auto-generated catch block
			myFrontend.showOutput(e.getMessage());
		}
    }
    
    //HISTORY
    public void clearHistoryFrontend(){myFrontend.clearHistory();}
    public void addToHistory(String s){myFrontend.addToHistory(s);}
    
    // DISPLAY
    public void drawLine(double x1, double y1, double x2, double y2, String charId){myFrontend.drawLine(x1, y1, x2, y2, charId);}
    public void changeDisplayBackgroundColor(Color c){
    	myFrontend.changeBackgroundColor(c);
    	myBackend.getProperties(myId).setBackgroundColor(toRGBCode(c));
    }
    public void changeLineColor(Color c, String charId){myBackend.getCharacterList(myId).getCharacter(charId).setPenColor(toRGBCode(c));}
    public void addPortrait(Character c){myFrontend.addPortrait(c);}
    public void clearCharactersFromFrontend(){myFrontend.clearCharacters();}
    public void clearAllLines(){
    	myFrontend.clearAllLines();
    }
    

    //USER DEFINED COMMANDS
    public void clearUDC(){myFrontend.clearUDC();}
    public void addCommandToUDC(String command){myFrontend.addToUDC(command);}
    
    //ALL CHARACTERS
    public void clearAllChars(){ myFrontend.clearAllChars(); }
    public void addChar(Character c){ myFrontend.addChar(c); }

    public String getDisplayBackgroundRGB(){
		return myFrontend.getBackgroundRGB();
    }

    
    //MISC
    public String getGUIProperty(String s) {
		return myWorkSpace.getGUIProperty(s);
	}
    
   
    public Stage getMyStage() {
    	return myStage;
    }
    
    //BACKEND
    public void addNewChar(Character c){myBackend.getCharacterList(myId).addCharacter(c);}
    
    public void setPenPattern(PenPattern pattern, String charId){myBackend.getCharacterList(myId).getCharacter(charId).setPenPattern(pattern);}
    public void setPenColor(Color c, String charId){myBackend.getCharacterList(myId).getCharacter(charId).setPenColor(toRGBCode(c));}
	public void setLineThickness(double newWidth, String charId) {myBackend.getCharacterList(myId).getCharacter(charId).setPenWidth(newWidth);}
    
	public Color getPenColor(String charId){return Color.web(myBackend.getCharacterList(myId).getCharacter(charId).getPenColor()); }
	public double getLineThickness(String charId){ return myBackend.getCharacterList(myId).getCharacter(charId).getPenWidth();}
    public PenPattern getPenPattern(String charId){return myBackend.getCharacterList(myId).getCharacter(charId).getPenPattern();}
    
    public boolean isCharIdActive(String charId){ return myBackend.getCharacterList(myId).getActiveCharacters().contains(charId); }
    public boolean isCharIdPenDown(String charId){ return myBackend.getCharacterList(myId).getCharacter(charId).getPenState(); }
    public boolean isCharIdVisible(String charId){return myBackend.getCharacterList(myId).getCharacter(charId).getVisability();}
    public void setCharIdActive(String charId, boolean isActive){
    	if (isActive && !myBackend.getCharacterList(myId).getActiveCharacters().contains(charId))
    		myBackend.getCharacterList(myId).getActiveCharacters().add(charId);
    	else if (!isActive && myBackend.getCharacterList(myId).getActiveCharacters().contains(charId))
    		myBackend.getCharacterList(myId).getActiveCharacters().remove(charId);
    }
    public void setCharIdPenDown(String charId, boolean isPenDown){ myBackend.getCharacterList(myId).getCharacter(charId).setPenState(isPenDown); }
    public void setCharIdVisible(String charId, boolean isVisible){ myBackend.getCharacterList(myId).getCharacter(charId).setVisability(isVisible);}
    public static String toRGBCode( Color color ){ return String.format( "#%02X%02X%02X",(int)( color.getRed() * 255 ),(int)( color.getGreen() * 255 ),(int)( color.getBlue() * 255 ) );}
    
    public String getCharPositionString(String charId){
    	return "X: "+myBackend.getCharacterList(myId).getCharacter(charId).getCoordX()+
    			",Y: "+myBackend.getCharacterList(myId).getCharacter(charId).getCoordY()+
    			",Heading: "+myBackend.getCharacterList(myId).getCharacter(charId).getMyAngle();
    }
    
    public void initColorMap(){
    	myBackend.getColorMap(myId).addColor(0, toRGBCode(Color.BLACK));
    	myBackend.getColorMap(myId).addColor(1, toRGBCode(Color.WHITE));
    	myBackend.getColorMap(myId).addColor(2, toRGBCode(Color.BLUE));
    	myBackend.getColorMap(myId).addColor(3, toRGBCode(Color.RED));
    	myBackend.getColorMap(myId).addColor(4, toRGBCode(Color.GREEN));
    }
    
    public void initShapeMap(){
    	myBackend.getShapeMap(myId).addShape(0, "T1.png");
    	myBackend.getShapeMap(myId).addShape(1, "T2.png");
    	myBackend.getShapeMap(myId).addShape(2, "T3.png");
    	myBackend.getShapeMap(myId).addShape(3, "T4.png");
    	myBackend.getShapeMap(myId).addShape(4, "T5.png");
    }
    
    public String getColorFromMap(int id){
    	return myBackend.getColorMap(myId).getColor(id);
    }
    
    public void setColorInMap(Color c, int i){
    	myBackend.getColorMap(myId).addColor(i, toRGBCode(c));
    }
    
    public String getImagePathFromMap(int id){
    	return myBackend.getShapeMap(myId).getImagePath(id);
    }
    
    public void setImagePathInMap(String imgPath, int id){
    	myBackend.getShapeMap(myId).addShape(id, imgPath);
    }

	
    //GETTERS AND SETTERS
    public FrontendManager getFrontendManager() {return this.myFrontend; }
    public IWorkSpace getWorkSpaceManager() {return this.myWorkSpace;}
}