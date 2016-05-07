SLOGO Addition
===========
##Raghav Kedia - rk145

####Estimation

For my SLOGO project, I was on the backend. We already had the WINDOW functionality implemented as our default behavior, so I decided to implement the FENCE function (but I also implemented WINDOW as a function to be able to switch between the two modes). 

I expect that it will take me no longer than 30 minutes to implement. I will probably only need to modify five files, but most of them will be the properties files and enum files for tracking all possible functions. The actual logic can be implemented in about 20 - 30 lines of code or so. This is because, from what I remember, all coordinate translations occur in one function, thus I would only need to make an addition of code in only one place. 

####Review

It took me about 35 minutes to complete the new feature. I ended up having to update six files, and add 3 new ones: three properties files, one enum class, and two "logic" classes. For the property files, I just added the two function names, the number of parameters for each function, and the coordinate system size. The coordinate system size addition was necessary because currently the size of the display was only in one of the frontend classes and not a property file (for better design it should be placed in a properties file anyways). Similarly, I added the function name in the enum class. For the logic classes, I made a new interface called IScreen, and it contains one method called translateCoords(). This method takes in a coordinate and will translate it accordingly. I then made two classes that implement this interface, WindowScreen, and FenceScreen. WindowScreen takes in a coordinate, and does no further modification to it because it's not supposed to. FenceScreen takes in a coordinate and bounds it according to the screen dimensions. This design is very extendable. If you wanted to add a function that would make the screen wrap, all you would do is create a new WrapScreen class and have it implement the IScreen interface. The properties class represents the properties of each workspace, so it made sense to store the screen object in there. In the command factory, I just added two checks for the two additional commands. 

***
I just want to make note that the current implementation of our Command Factory uses a large switch statement, and I understand that that isn't the best design. However, I addressed this issue in my masterpiece for SLOGO and cleaned it up significantly (by using reflection). For this extension, since I made the new branch off of our master branch and not my analysis branch, I did not implement it with my masterpiece code but our original code. 
***

I didn't get it completely right on the first try, but that was because of a minor typo/bug. I wrote Math.max instead of Math.min, but I quickly figured out my mistake. 

####Analysis: 

To be honest, this exercise revealed that our original design was pretty good. I only had to add about 30 - 40 lines to implement this feature. Documentation wise, it would have helped if our screen dimensions were places in a resource file and not in a class (as a result I had to search through some frontend classes to find the dimensions), but because I was familiar with our project it wasn't difficult. However, I do recognize that for someone with no past experience with our code, it would be a little bit more difficult to find those dimensions. As far as where to add the logic code, I feel that it was pretty intuitive to add it to our Command Factory class. I also recognize that while implementing this new feature, I violated the Open-Close principle since I had to go into an existing class to add this extension. As mentioned in the note above, I addressed this issue in my masterpiece. 

Overall, I was pleased with our design. Going through the code there are certainly some things I would change, but nothing major as far as design is concerned. The only improvement I would make is refactoring our Command Factory class. 

