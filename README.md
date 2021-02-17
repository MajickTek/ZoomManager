# Zoom Manager

This app allows you to put all of your recurring zoom meetings in one place. It is minimalistic and light, only requiring a Java Runtime Environment.

Interface: A graphical list that is modified with keybinds

Features:

Add/Edit meetings: SPACE brings up the "Edit Meeting" window, which allows you to add meetings. pressing ENTER on one meeting brings up the menu with the previous values auto-filled in. If ENTER is pressed and multiple meetings are selected, only the topmost meeting is edited.

Remove meetings: One or more meetings can be selected in the list and deleted with the DELETE key.

Launch/Join Meeting: meeting can be joined manually with the E key.

Save/Load list: CTRL + S saves the list to a file in "C:\Users\%username%\Documents\MajickTek\ZoomManager" which is automatically loaded on launch.


## Limits
Currently, The app is hard-coded to allow for 16 meetings, which is quite a lot. The reason for the limit is because of the way I was handling the timer (Which has since been removed).


## TODO
Open meeting automatically when day/Time is reached. This is complicated because of time zones etc. My idea is to do this based on the host computer's clock. The application would either need to stay open or have some background service installed and ran at first launch.

Clean up code/Modularize code. Currently there are repeated sections that could be fixed.

Fix error on first opening the app when load file doesn't exist

![First Screenshot of App](https://github.com/MajickTek/ZoomManager/raw/master/screenshots/first.png)