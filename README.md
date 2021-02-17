# Zoom Manager

![First Screenshot of App](https://github.com/MajickTek/ZoomManager/raw/master/screenshots/first.png)

This app allows you to put all of your recurring zoom meetings in one place. It is minimalistic and light, only requiring a Java Runtime Environment.

This app is intended for use by students.

Interface: A graphical list that is modified with keybinds

Features:

Add/Edit meetings: SPACE brings up the "Edit Meeting" window, which allows you to add meetings. pressing ENTER on one meeting brings up the menu with the previous values auto-filled in. If ENTER is pressed and multiple meetings are selected, only the topmost meeting is edited.

Remove meetings: One or more meetings can be selected in the list and deleted with the DELETE key.

Launch/Join Meeting: meeting can be joined manually with the E key.

Save/Load list: CTRL + S saves the list to a file in "C:\Users\%username%\Documents\MajickTek\ZoomManager" which is automatically loaded on launch.

## Requirements
OS: Windows/macOS/[Linux/UNIX]/Anything else than can run Java and supports Swing apps (So, not Android for now. iOS support is not planned, as Java is hard/impossible to run on that platform).

Storage: JAR is less than half a GB. Allow a few megabytes for the binary save file.

## Limits
Currently, The app is hard-coded to allow for 16 meetings, which is quite a lot. The reason for the limit is because of the way I was handling the timer (Which has since been removed).

## Technical Info
### Save file Spec
Currently the save file is a raw binary. It is simply a serialized JList model that is written to/read from a file.

This save file is not versioned and also probably doesn't carry across platforms (especially across 32/64 bit systems.)

## TODO
Open meeting automatically when day/Time is reached. This is complicated because of time zones etc. My idea is to do this based on the host computer's clock. The application would either need to stay open or have some background service installed and ran at first launch.

Clean up code/Modularize code. Currently there are repeated sections that could be fixed.

Fix error on first opening the app when load file doesn't exist

Allow export of save file to either a common binary or text format that can be read across platforms easily.
 - possibly just need to provide structure and versioning to the current format