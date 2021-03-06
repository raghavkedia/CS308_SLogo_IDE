#Introduction

The problem that our team is trying to solve is to create a basic integrated development environment for the programming language Logo. In this project, we will follow the Model-View Controller (MVC) design paradigm where the frontend display interacts as little with the data-processing backend as possible. 

The general responsibility for the frontend display is to:
Provide an interface through which the user can interact with the program by inputting commands, changing variable assignments, etc.
Display the current state of the back-end data which includes the position of the turtle, lines that were drawn, past inputs, etc.
The general responsibility for the backend is to:
Keep track of all the necessary data, which includes the exact position of the turtle and the locations of lines that need to be drawn
Translate the input of the user into an action upon the data
Detect errors when the user input is in an incorrect format

To increase modularity, our design will have just one class in the backend open to one class in the frontend and vice versa. These two classes will be the “managers” of the frontend and backend and each will be responsible for delegating duties to other classes in the frontend and backend respectively. In that manner, the class responsible for translating commands will be closed to the class that receives the user input. 

Our design goals is to make our IDE as extensible as possible. We want it to be simple to implement new commands, display a variety of different objects and have display windows for many aspects of the data. To this end, we believe that it’s important to have a class dedicated to translating commands into actions so that new commands can be added easier. On the frontend, extensibility relies on ease of access of information through the Backend Manager so we want to make that channel as efficient as possible.


#Design Overview

For this project, we have decided to divide our program into two main components: Frontend and Backend. The Frontend primarily consists of our GUI, which is what the user would interact with. Our Backend is responsible for handling user input, executing commands, and error checking (this is an important design aspect, all error checking will be done in our backend, this is to adhere to MVC design principles). 

Here is a diagram of our design: 

![Design](https://github.com/duke-compsci308-spring2016/slogo_team09/blob/master/Design.Diagram.jpg "Design")

Our Frontend has one main Manager, and this is responsible for communicating with the Backend, and sending information to the GUI. Our GUI has four main components: Display, Console, History, and Variables. The Display is where the turtle will be shown and moved. The Console is where the user types in commands and sees the output. The History module displays all the past commands that the user typed in, and the Variables module displays all the current variables that the user has created. 

Our Backend also has a Manager, and it is responsible for controlling all the actions of the Backend. It is what communicates directly with the Frontend Manager, and this way, we only have two components interacting with each other from the Frontend and Backend. As mentioned, the Backend Manager is responsible for controlling the entire Backend, which includes the following components: Parser, CommandHandler, Data. The Parser is what parses the user input and divides it up into a format that our CommandHandler can handle. The Parser is also responsible for catching any syntax errors and notifying the Backend Manager. If there are no syntax errors, the Parser sends the parsed input to the CommandHandler, which will execute the command accordingly. The CommandHandler is also responsible for catching any logical or runtime errors. The CommandHandler must also interact with the Data class, which keeps track of the current state of everything (turtle location, variables, lines drawn, etc...). After the command has been executed, it sends the result to the Backend Manager which then notifies the Frontend Manager, which in turn will make the required updates to the components of the GUI. 

For this project we have four main API's: Frontend Internal/External, and Backend Internal/External:

* Frontend Internal is responsible for:
  * Displaying output/exceptions in the Console
  * Displaying past user inputs in History box
  * Updating Display variable values
  * Mirror Backend data in Display

* Frontend External is responsible for:
  * Pass user input to Backend
  * Recieve updated data/ exceptions
  
* Backend Internal is responsible for:
  * Sending text to Parser
  * Sending Parser text to CommandHandler
  * Sending calculated output to Backend Manager
  * Manager receiving exceptions

* Backend External is responsible for:
  * Passing current state of all data to Frontend Manager

#User Interface

![GUI](https://github.com/duke-compsci308-spring2016/slogo_team09/blob/master/GUI.jpg "GUI")

The is a graphic display window to display the turtle and its trajectory, a console to type command, a history box to display past command, a variable box to display variables that has been defined.

A user control the turtle by typing SLogo commands in console. When the “RUN” button on GUI is clicked, the current command on console is executed.

We might make turtle a dragable object, so user can drag the turtle to a certain position.

The console window will also report error whenever user’s command has type error or execution error. These errors are detected by the back-end, which will pass error messages to the front-end to be displayed.


#API Details

Our project will generally follow the rule of utilizing four API’s to solve the problem, which correspond to external and internal usage for both backend and frontend. 

####Frontend Internal

Responsibilities: 

* To display information in the correct component (errors will be displayed in console, variables and values will be displayed in variable section)
  * See the results of the turtle commands

####Frontend External

Responsibilities:

* To transmit the user input and other user-designated changes to the backend for the correct transformation of data
  * Entering commands to the user input
  * See errors displayed in the console
  * See variables currently assigned in the GUI
  * See a history of past commands

####Backend Internal

Responsibilities:

* To parse the data of the user input and translate that input into a command 

* To execute certain commands to transform the data stored in the Backend Manager class

####Backend External

Responsibilities:

* To return the data in the current state for purposes of display

We would need at least two types of Exceptions, those that are related to the user input and one related to the state of or action upon the data. For example, if the turtle was told to move off the screen we may want to create an exception that relates to us the information that the last command could not be completed. An exception related to the user input might be a command that does not exist.


#API Example Code 
Fd 50:
```java
String input = consoleInputListener() returns String containing user input
public frontEndManager.receiveUserInput(String input) 
//Console sends front end manager the user input
public backEndMangager.receiveUserInput(String input)
//Front end manager send user input to backend manager
public parser.receiveInput(String input)
//Back end manager sends user input to parser
public commandHandler.receive(parsed input)
//command handler receives some sort of parsed input from parser
public backEndManager.receiveCommand(command) 
//command handler sends back end manager commands
public frontEndManager.receiveCommand(command)
//Back end manager sends commands to front end manager
//Also possible that exception was thrown and back end manager sends message to print to console
public console.receiveOutput(command)
public turtle.update(command)
public history.update(command)
```

HOME:
```java
String input = consoleInputListener() returns String containing user input
public frontEndManager.receiveUserInput(String input) 
public backEndMangager.receiveUserInput(String input)
public parser.receiveInput(String input)
public commandHandler.receive(parsed input)
public Data.returnTotalDistanceTraveled()
public backEndManager.receiveCommand(command) 
public frontEndManager.receiveCommand(command)
public console.receiveOutput(command)
public turtle.update(command)
public history.update(command)
```

DISPLAY:
```java
public void displayData(){
  myLines = backendManager.retrieveLines();
  myTurtle = backendManager.retrieveTurtle();
  myDisplay.display(myLines);
  myDisplay.display(myTurtle);
}
```

LESS? expr1 expr2
```java
public frontEndManager.receiveUserInput(String input) 
public backEndMangager.receiveUserInput(String input)
public parser.receiveInput(String input)
public commandHandler.receive(parsed input)
private int commandHandler.compareExpr(parsed input) {
 return expr1 > expr2 ? 1 : -1;
}
public frontEndManager.receiveCommand(command)
public console.receiveOutput(command)
```

#Design Considerations 

Our biggest discussion with regards to design was how to divide up the functionality between the Backend and Frontend. We considered different designs where the Frontend and Backend would take responsibility over certain tasks. For example, we debated whether or not the Parsing of user input should be done on the Frontend or Backend (the reason why we considered parsing on the Frontend was because that is where the use text is ultimately first read). 

We decided to keep the parser on the Backend because this would help us adhere to the MVC design paradigm. More generally, we decided to keep all functionality with regards to command computation and execution (parsing, handling, computing, error checking, etc…) in the Backend, and thus all the Frontend would be responsible for would be the different displays (console, history, display), text input, text output, and turtle actions (the calculations for turtle actions will be done in the Backend, the Frontend will simply update the turtle state accordingly). 

#Team Responsibilities

###Richard

* Implement processes to display and update back-end data in the GUI
* Allow the user to interact with the program through console and buttons

###Jane
* Allow the turtle to receive information from the front end and show state on display 
* Store user commands in history 

###Raghav

* Implement Backend Manager to communicate with Frontend
* Work with Christine on Parser

###Christine
* Working on the parser with Raghav 
* Working on the command handler for turtle command, math commands, and boolean commands
