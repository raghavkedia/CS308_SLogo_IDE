Discussion from: Raghav Kedia (rk145), Jane Yu (), Christine Zhou (clz4)

```
package cellsociety_team01;
public abstract class Cell {  
  	public Image getCellImage() //Internal API (no longer necessary)
}

package cellsociety_team01;
public class ChooseParser { //Internal API 
  	public Parser choose(File file) throws SAXException, IOException, ParserConfigurationException
}
 
package cellsociety_team01;
public class Display { //External API 
  	public Display(Stage stage)
	public void showGUI()
	public void hideGUI()
}
 
package cellsociety_team01;
public class dummy { //should be removed
  	public String readFile(File file) throws SAXException, IOException
}
 
package cellsociety_team01;
public class Engine { //External API
    	public Engine()
  	public void initializeSimulation(File file)
  	public void step()
  	public void updateGridToNextGeneration()
  	public void start()
  	public void stop()
  	public void resetGrid()
  	public void resetRule()
  	public Grid getGrid()
}
 
package cellsociety_team01;
public class FireCell extends Cell { //External API
  	public FireCell()
	public State returnState(char s)
	public List<Cell> getEmpties()
	public void setFutureStateBurning()
	public void setFutureStateEmpty()
	public void setFutureStateTree()
	public boolean isTree()
	public boolean isBurning()
	public boolean isDead()
}
 
package cellsociety_team01;
public class FireGrid extends Grid { //External API
  	public FireGrid(int dimX, int dimY) 
}
 
package cellsociety_team01;
public class FireParser extends Parser { //Internal API
  	public FireParser(Document doc) 
	public Rule returnRule() 
	public Grid createGrid()
}
 
package cellsociety_team01;
public class FireRule extends Rule{ //Internal API
  	public FireRule()
	public void setProbCatch(double probCatch)
	public Cell returnNewState(Cell myCell) 
}
 
package cellsociety_team01;
public class FireState extends State { //Internal API
  	public FireState() 
	public fireState getState()
	public void setState(fireState f)
	public void setTree()
	public void setBurning()
	public void setEMPTY()
	public boolean isTree()
	public boolean isBurning()
	public boolean isDead()
	public String toString()
}
 
package cellsociety_team01;
public class GameOfLifeCell extends Cell { //External API
  	public GameOfLifeCell()
	public State returnState(char s)
	public List<Cell> getEmpties()
	public boolean isAlive()
	public void setFutureStateDead()
	public void setFutureStateAlive()
}
 
package cellsociety_team01;
public class GameOfLifeGrid extends Grid { //External API
  	public GameOfLifeGrid(int dimX, int dimY) 
}
 
package cellsociety_team01;
public class GameOfLifeParser extends Parser { //Internal API
  	public GameOfLifeParser(Document doc) 
	public Rule returnRule() 
	public Grid createGrid()
}
 
package cellsociety_team01;
public class GameOfLifeRule extends Rule { //Internal API
  	public Cell returnNewState(Cell myCell) 
}
 
package cellsociety_team01;
public class GameOfLifeState extends State { //Internal API
  	public GameOfLifeState() 
	public gameOfLifeState getState()
	public void setState(gameOfLifeState l)
	public void setAlive()
	public void setDead()
	public boolean isAlive()
	public String toString()
}
 
package cellsociety_team01;
public abstract class Grid { //Internal API 
  	public void setRow(int numRow)
	public void calculateFutureGrid(Rule rule) 
	public void updateToNextGeneration(Rule rule)
	public void setCellsInGrid(List<Cell> c)
}
 
package cellsociety_team01;
public class main extends Application{ //Internal API
  	public void start(Stage primaryStage) throws Exception 
}
 
package cellsociety_team01;
abstract public class Parser extends ChooseParser {  //External API
  	public void setDocument(Document d)
	public Document getDocument()
	abstract public Rule returnRule();
}
 
package cellsociety_team01;
abstract public class Rule { //Internal API
  	abstract public Cell returnNewState(Cell myCell);
}
 
package cellsociety_team01;
public class SegregationCell extends Cell { //External API
  	public SegregationCell() 
	public State returnState(char s) 
	public List<Cell> getEmpties() 
	public boolean getMarked() 
	public void setMarked(boolean marked) 
	public boolean isPop1() 
	public boolean isPop2() 
	public boolean isNone() 
	public void setFutureState2() 
	public void setFutureState1() 
	public void setFutureStateEmpty() 
}
 
package cellsociety_team01;
public class SegregationGrid extends Grid { //External API
  	public SegregationGrid(int dimX, int dimY) 
	public void calculateFutureGrid(Rule rule) 
	public void updateToNextGeneration(Rule rule)
}
 
package cellsociety_team01;
public class SegregationParser extends Parser { //Internal API
  	public SegregationParser(Document doc) 
	public Rule returnRule() 
	public Grid createGrid()
}
 
package cellsociety_team01;
public class SegregationRule extends Rule { //Internal API
  	public int Rounder(double round)
	public void setProportion(double proportion) 
	public SegregationGrid moveCells(SegregationGrid grid,Cell myCell)
	public Grid returnNewState(Cell myCell,SegregationGrid grid) 
	public void setFalse(SegregationCell c) 
	public Cell returnNewState(Cell myCell) 
}
 
package cellsociety_team01;
public class SegregationState extends State { //Internal API
  	public SegregationState() 
	public segState getState() 
	public void setState(segState s) 
	public void setPopulation1() 
	public void setPopulation2() 
	public void setEMPTY() 
	public boolean isPop1() 
	public boolean isPop2() 
	public boolean isEmpty() 
	public String toString()
}
 
package cellsociety_team01;
public class Settings { //Internal API
  }
 
package cellsociety_team01;
public abstract class State { //Internal API
  	public Image getImage()
	public void setImage(Image i)
	public abstract String toString();
}
 
package cellsociety_team01;
public class WatorCell extends Cell { //External API
  	public int[] getGoing() 
	public void setGoing(int[] going) 
	public int[] getComing() 
public void decreaseEnergy(int number)
	public void setComing(int[] coming) 
	public WatorCell() 
	public void setSharkEnergy(int sharkEnergy) 
	public void setNumFrames(int numFrames) 
	public void incrementEnergy(int increment)
	public void incrementFrames()
	public State returnState(char s)
	public ArrayList<Cell> getEmpties()
	public int getEnergy()
	public void setFutureStateShark() 
	public void setFutureStateFish() 
	public void setFutureStateEmpty() 
	public boolean isShark() 
	public boolean isFish() 
	public boolean isNone() 
	public int getFrames()
	public boolean getEaten()
	public void setEaten(boolean eaten)
	public boolean getMarked()
	public void setMarked(boolean marked)
}
 
package cellsociety_team01;
public class WatorGrid extends Grid { //External API
  	public ArrayList<int[]> getSharkEats() 
	public void addToSharkEats(int[] sharkEats) 
	public WatorGrid(int dimX, int dimY) 
	public void calculateFutureGrid(Rule rule) 
	public void updateToNextGeneration(Rule rule)
}
 
package cellsociety_team01;
public class WatorParser extends Parser { //Internal API
  	public WatorParser(Document doc) 
	public Rule returnRule() 
    public Grid createGrid()
}
 
package cellsociety_team01;
public class WatorRule extends Rule { //External API
  	public WatorRule() 
	public Grid returnNewState(Cell myCell, Grid grid) 
	public ArrayList<WatorCell> getEmpty(WatorCell c1, Grid grid) 
	public ArrayList<WatorCell> getFish(WatorCell c1, Grid grid) 
	public Grid move(WatorCell c1, Grid grid, ArrayList<WatorCell> fishies, boolean eating, boolean reproducing) 
	public void reproduce(Cell myCell, ArrayList<Cell> empties) 
	public Cell returnNewState(Cell myCell) 
}
 
package cellsociety_team01;
public class WatorState extends State { //External API
  	public WatorState() 
	public watorState getState()
	public void setState(watorState w)
	public void setShark()
	public void setFish()
	public void setEmpty()
	public boolean isShark() 
	public boolean isFish() 
	public boolean isEmpty() 
	public String toString()
}
 
public class GenerateRandomGrids { //Internal API
  	public GenerateRandomGrids() 
	public String returnRandomGrid(String simulation)
}
```

###Simulation 
Internal: Grid classes, cell classes, state classes. 
External: Rule classes are external.

Grid classes keep track of cells in the grid and cell classes keep track of individual states of cells. These are part of the internal API because the state of each individual cell and grid state should only be a concern to the simulation. The rule classes are part of the external API because the rule class controls the actual simulation logic. 

###Configuration
Internal: parser methods
External: setNeighbors methods

Parser methods were considered internal because the parser is only concerned with parsing an xml file and there isn't a reason to give access to an external user. 

setNeighbors methods are considered external because setNeighbors is called to during different grid configurations. 

###Visualization 

Internal: All methods in display class are part of the internal API
External: None

The methods in display are considered part of the internal API because the methods are just for the GUI to call. These also aren't given to external users to use. 

SLogo 

*When does parsing need to take place and what does it need to start properly?* 
The parsing can be done by the back-end. The front-end can have an external API that passes the user input to the back-end to parse. 

*What is the result of parsing and who receives it?*
The parser will parse the input into a command and its input. Some code in the back-end for command handling will get the command and input. 

*When are errors detected and how are they reported?*
Syntax error are like nonexistent function errors and typos and there are logical errors. The syntax errors can be handled by the parser class and the logical errors by the command class. 

*What do commands know, when do they know it, and how do they get it?*
Commands know their inputs, what is "good logic" and they get their inputs from the parser class. 

*How is the GUI updated after a command has completed execution?*
Output the result of the command whether its a result of a mathematical command or a result that will move the turtle. The GUI will also update the view of the previous commands. The GUI will either get a string to print to console or get a list of coordinates to move the turtle to. 
