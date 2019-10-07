# Jobsity Bowling

## Introduction

The program should read the input text file and parse its content, which should have the
results for several players bowling 10 frames each, written according to these guidelines:

a. Each line represents a player and a chance with the subsequent number of pins
knocked down.
b. An 'F' indicates a foul on that chance and no pins knocked down (identical for
scoring to a roll of 0).
c. The rows are tab-separated.

# Input sample

Your program should accept as its first argument a path to a filename. The input file contains several
lines. Each line is one test case.

Each line contains the name of the player and the score associated to that throw.

```
Jeff 10
John 3
John 7
Jeff 7
Jeff 3
John 6
John 3
Jeff 9
Jeff 0
John 10
Jeff 10
John 8
John 1
Jeff 0
Jeff 8
John 10
Jeff 8
Jeff 2
John 10
Jeff F
Jeff 6
John 9
John 0
Jeff 10
John 7
John 3
Jeff 10
John 4
John 4
Jeff 10
Jeff 8
Jeff 1
John 10
John 9
John 0
```
## Output sample

For each player the program should print pin falls and score associated to each frame
E.g.

```
Frame     1     2     3     4     5     6     7     8     9     10     
Carl
PinFalls     X     X     X     X     X     X     X     X     X     X X X     
Score     30     60     90     120     150     180     210     240     270     300
```

## Considerations

1. Invalid line format will stop the execution
2. The program allows for an extra throw in case that the last frame is a strike
3. Fault value will be consider as 0
4. Assuming that number of frames will be right for each player

### Technologies

- Java 8
- Maven
- Lombok Plugin
- JUnit
- Log4j

### How to start

Once the application is fetched from git it can be built with maven
~~~~
mvn clean install
~~~~
This will fetch dependencies and run all tests 

To run the app execute:
~~~~
java -jar target/bowling-1.0-SNAPSHOT.jar --File Location

E.g.
java -jar target/bowling-1.0-SNAPSHOT.jar "C:\\Users\\esteban.santamarina\\Desktop\\demo.txt"
~~~~
