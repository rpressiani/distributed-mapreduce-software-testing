# Sample Application
___
Assignment delivered by Riccardo Pressiani ([rpress4@uic.edu](mailto:rpress4@uic.edu)) as Homework 1 for both CS441 and CS474 courses, held at University of Illinois at Chicago during Fall 2017.

## Description
___
The goal of this homework was to gaining experience with git repository management, analyzing open-source Java projects, creating JUnit tests, using build tools such as Gradle and sbt the Java Monitoring Tools.

The project chose to fulfill the assignment is a Tetris game application (https://github.com/futek/tetris).

### Tests
The JUnit 4.10 framework has been used to provide a few test cases for the _GameModel_ and _Tetrimino_ classes.

#### GameModelTest
A `setUp()` method is provided to create a _GameModel_ object that will be used in all the test cases.

* **_swappedTetriminoWillComeBack_**: This test case verify that if a tetrimino (the  basic falling shape of the Tetris game) is swapped it will be put on hold. When another tetrimino will be swapped, it will be swapped with the one that is on hold.

* **_fallingTetriminoCanBeSwappedOnlyOnce_**: This test case verify that if a tetrimino is swapped the user cannot swap the tetrimino again.

#### TetriminoTest
The _ExpectedException_ object is created in order to verify the correct behavior of the exception thrown in the _outOfBoundRotationNotAllowed_ test case.

* **_rotationCannotBeNegative_**: This test case verify that the method that rotates a tetrimino never returns a negative rotation value. As a matter of fact the rotation of a tetrimino must be in a range between 0 and 3.

* **_outOfBoundRotationNotAllowed_**: This test case verify that a new tetrimino cannot be created with an out of bound rotation value and that the relative exception will be thrown.

[Update] Other tests have been added as part of homework 2 of the CS441 course.


Both Gradle and sbt can be used to run the tests using one of the following commands:

`./gradlew test` or `sbt test`


## Usage
___
The project features both [Gradle](https://gradle.org/) and [sbt](http://www.scala-sbt.org/) as build tools. The commands to compile and run the project are reported below.

### Compile

`./gradlew build` or `sbt compile`

### Run

`./gradlew run` or `sbt run`

## Java Monitoring Tools
___
The following [Java Monitoring Tools](https://docs.oracle.com/javase/8/docs/technotes/guides/troubleshoot/tooldescr025.html) have been used to analyze the execution of the application:

* [Java Mission Control](https://docs.oracle.com/javase/8/docs/technotes/guides/troubleshoot/tooldescr002.html#BABIBBDE)
* [jcmd](https://docs.oracle.com/javase/8/docs/technotes/guides/troubleshoot/tooldescr006.html#BABEHABG)
* [JConsole](https://docs.oracle.com/javase/8/docs/technotes/guides/troubleshoot/tooldescr009.html#BABDCICF)
* [jmap](https://docs.oracle.com/javase/8/docs/technotes/guides/troubleshoot/tooldescr014.html#BABGAFEG)
* [jps](https://docs.oracle.com/javase/8/docs/technotes/guides/troubleshoot/tooldescr015.html#BABHCEDC)
* [jstack](https://docs.oracle.com/javase/8/docs/technotes/guides/troubleshoot/tooldescr016.html#BABFCHDE)
* [jstat](https://docs.oracle.com/javase/8/docs/technotes/guides/troubleshoot/tooldescr017.html#BABCDBEA)
* [visualgc](https://docs.oracle.com/javase/8/docs/technotes/guides/troubleshoot/tooldescr018.html#BABCDEBE)
* [Java VisualVM](https://docs.oracle.com/javase/8/docs/technotes/guides/troubleshoot/tooldescr010.html#BABEEIFH)

All the outputs have been collected during the execution of a match. _jps_ has been used to retrieve the PID of the JVMs running in the system. Thanks to tools such as _Java Mission Control_, _JConsole_, _visualgc_ and _Java VisualVM_, it has been possible to understand that the CPU usage of the application is always controlled and pretty low, while the garbage collector executes everytime the heap reach a size of 65-70MB.

_jcmd_ provides information about the VM and the number and size of the instantiated classes. _jstack_ provides a deadlock detection features that analyzes all the blocked threads. _jstat_, similarly to the previously mentioned tools, monitors the resource consumption of the application. In addition to the other tools, _jmap_ provides detailed information about the heap configuration.

The output of the tools can be found in the _java-monitoring-tools_ directory.

## Limitations
___
The project used for this homework is a simple implementation of the Tetris game. Some additions to the actual implementation could be a local score to save the results of the matches played or the possibility to choose the difficulty level of the game.

For what concerns the tests implementation, the methods that have been tested make direct call to some of the JFrame functions that open parts of the user interface when tests are executed. This could be avoided using mocking frameworks like [Mockito](http://site.mockito.org/).

## References
___
In order to fulfill the assignment the following resources have been used:

* [sbt Reference Manual](http://www.scala-sbt.org/1.x/docs/index.html)
* [Gradle Documentation](https://gradle.org/docs/)
* [JUnit4 JavaDoc Documentation](http://junit.org/junit4/javadoc/latest/index.html)
