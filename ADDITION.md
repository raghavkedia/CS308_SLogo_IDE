###Estimation: before looking at the old code:###
*how long do you think it will take you to complete this new feature?*
I think it would take me around an hour to complete this implementation.

*how many files will you need to add or update? Why?*
I think I would need to update the command factory and the enum class for the command factory. This is because to add commands these classes need to be modified so the command factory recognizes this is a valid command.

###Review: after completing the feature:###
*how long did it take you to complete this new feature?*
It took me an hour to complete this and I'm not exactly sure if I set the display dimensions correctly. This is because the turtle isn't visible for me for some reason (it's been an error for a while).

*how many files did you need to add or update? Why?*
I had to update the command factory and enum classes for the reasons given before. I also though forgot that I had to update two of the properties files because these set how my tokenizer and expression tree builder work. It turns out I also had to update a properties class since my command factory is recreated per a command which I didn't remember. 

*did you get it completely right on the first try?*
No I did not get it right. I forgot a lot of things that had to be added to allow for additional commands. These didn't feel like much during the actual project but were hard to remember later.

###Analysis: what do you feel this exercise reveals about your project's design and documentation?###
*was it as good (or bad) as you remembered?*
The documentation on the code was nonexistent so that was a lot worse than I remembered. I also couldn't keep track of all the classes I had to modify even though it wasn't that many for each class.

*what could be improved?*
It might have been a good idea to use reflection to do the command factory instead of the current method. I gave reasons in my previous analysis for why it wasn't used, but I realized this made it quite difficult to do otherwise. There still also was more conditionals used because of the original not so great coding conventions. The original code didn't allow for much flexibility with adding on to the existing code without using case switches. Since I didn't change the entire structure original code to implement this, it made me realize that little bad practices can make things very difficult very quickly.

what would it have been like if you were not familiar with the code at all?
This probably would have been almost impossible to code without knowing the code at all. Since a lot of the code depends on things within the properties files, if someone didn't know to look in the properties files and change them according to the new commands, they probably would not have been able to find things like the GUI size (which wasn't actually stored in the properties file but some enum class), command argument number, etc. All of these would have caused severe frustration. Documentation should have been much much better and original not so great coding also makes it difficult to change code later on.
