#!/bin/sh
cd /Users/debroni_pro/Documents/University/Berest/Task/src/main/java/ua/sumdu/j2se/matusevich/tasks/
cls
echo Deleting class files.
del *.class
echo Compiling.
javac StartApplication.java
java StartApplication
echo All done!