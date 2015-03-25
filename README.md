MapQuest Demo
=====

In this project I show a very good solution to avoid recreation of the activity (or fragment in this case) when the device is rotated forcing a change configuration event on the system. The setRetainInstance(boolean) method is used to avoid the object to be destroyed and recreated.
You can see this behavior on the first screen when a background with a progress bar is shown. You will see that you will not lose the progress of the bar on every change (rotation).

Also you can see an example of how make a request to the Instagram's endpoint to retrieve the public timeline using hashtags with the help of the Volley library and how to handle its different events. 
For memory management the library Picasso is loaded to display the images retrieved from the server.
