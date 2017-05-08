# ROUTELINE
This is the main repository for the Routeline project we're doing as part of the MSc Computer Science 2016/17 Software Engineering with Group Project module.

## Description

A a system for providing information about rail travel for the North Eastern Railway Division (NERD). The idea behind the application is to make life easier for travellers by allowing them to check their train information at kiosks in stations or online.

## Functionality
The system allows the user to do the following:
-	Create train lines.
-	Record all the train stations on a line, a minimum transfer time between trains at each station, and the journey time between stations.
-	Allow for addition and removal of new stations and connections
-	Provide an interface for the user to allow them to search for optimal routes between stations
-	Allow a route to be requested that must avoid, or has to include, a given station.
-	Allow a traveller to select a maximum number of train changes for a long trip.

## Installing and Running
## Requirements
JDK 1.8 (JDK8) and Activator 1.3.12

## Instructions
Extract 'activator-1.3.12-minimal.zip' to a known location

Adding Activator to path:
In Mac OS and Linux using Terminal:
Note: change '/home/cree/Desktop/' to the correct path to your directory
export PATH=$PATH:/home/cree/Desktop/activator-1.3.12-minimal/bin

On Windows from CMD (as admin):
Note: change Drive: to your drive letter, and 'pathToFolder' to the correct path to your directory.
setx path "%path%;Drive:\pathToFolder\activator-1.3.12-minimal\bin"

Run 'activator ui' to download certain dependencies then close the browser UI and wait for the process to close, or close the Terminal/prompt and open a new Terminal/prompt. Note: on Linux/Mac OS, the path variable will need to be reset each time the terminal is closed.

Change to the 'routeline' folder and type 'activator run' -- allow the dependencies to download. When it is finished, the app can be accessed from http://localhost:9000.

Type 'http://localhost:9000/init' to seed DB with users. To access admin section, the username is 'admin@routeline.com' and password 'admin'.

If you wish to browse the database separately from the app's CRUD admin interface, instead of 'activator run', type 'activator' then type 'h2-broswer' to bring up the h2-browser web interface. Before doing anything on the h2-browser, type 'run' in the Terminal/prompt. The app should then load. At this point, after the evolutions have applied, the DB can be viewed when the following options are selected:

Driver Class: org.h2.Driver
JDBC URL: jdbc:h2:mem:play
User Name: sa
Password: <blank>

CTRL+D in the terminal closes the process when finished.
