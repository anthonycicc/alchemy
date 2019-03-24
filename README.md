alchemy
=======

A 7 day Roguelike in Clojure

Originally by Mike Anderson, adapted for gradle by Anthony Cicchetti

## Story

    You are a talented young alchemist. You discover that the fabled Philosopher's 
    Stone is hidden at the bottom of an old dungeon. What luck! All you have to do 
    is descend and grab the stone before your rivals catch the scent. 
    
    What could possibly go wrong?
    


## Build instructions

Alchemy requires the jdk to be on your path to build 

The following command should produce a working build:

    ./gradlew shadowJar

This will create an executable jar file (in the build/libs directory) that can be run with a command like:

    java -jar alchemy-0.0.3-SNAPSHOT-all.jar
