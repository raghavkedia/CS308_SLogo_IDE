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

#User Interface Jane

![GUI](https://github.com/duke-compsci308-spring2016/slogo_team09/blob/master/GUI.jpg "GUI")

The is a graphic display window to display the turtle and its trajectory, a console to type command, a history box to display past command, a variable box to display variables that has been defined.

A user control the turtle by typing SLogo commands in console. When the “RUN” button on GUI is clicked, the current command on console is executed.

We might make turtle a dragable object, so user can drag the turtle to a certain position.

The console window will also report error whenever user’s command has type error or execution error. These errors are detected by the back-end, which will pass error messages to the front-end to be displayed.


#API Details Richard



#API Example Code - Christine 
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


#Design Considerations 



#Team Responsibilities

