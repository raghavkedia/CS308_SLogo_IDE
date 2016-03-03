####Part 1
1. The frontend external API is very clean, with only one external method that allows the backend to print errors. This makes the frontend very flexible.
2. The printError() method encaspulates the printing process.
3. Exceptions are all handled by the backend calling printError()
4. It does anything we need with the least number of methods.

####Part 2
We're most worried about displaying multiple turtles while maintaining encapsulation. With multiple turtles and lines, the frontend can be messy if we don't limit the amount of data from the backend we receive. Other issues such as dynamically resizing the canvas based on the turtles' positions can be difficult to contend with if we are not careful.