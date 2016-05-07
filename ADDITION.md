ADDITION_rrl6
===============

## Estimation

I think it will take ma about 1 hour to finish this new feature. 

I'll probably need to add classes for a TurtleList, and a Button class that brings up the aforementioned TurtleList. 

## Review

It took me about an hour and a half, because I had to debug this weird Autolayout error because I had accidentally written `ListView's` `update` method. 

I had to add a total of three classes, the List, the Button (both mentioned above) as well as a PopupWindow class. I think my logic was pretty sound on the first try, but I had to debug some minor issues. 

## Analysis

This extension was fairly easy for me because our frontend was extensible enough to support additions like this. Adding a button to the toolbar is easy, creating popups is easy, changing a character's turtle image was pretty straightforward as well. Most of the time was spent on filling in the frontend components. 

One class in particular could've been improved -- `ListVisual`. It was supposed to be a wrapper class for a JavaFX `ListView` but it was only a `ListView<String>` so I couldn't use it to manage `HBox's`.

If I was not familiar with the code at all, I believe it still would've been implemented relatively quickly. The `Controller` class offered a lot of functional support and implementing Toolbar/PopupWindow elements has already been done before so it would be a matter of extending the right classes and copying the right code. 