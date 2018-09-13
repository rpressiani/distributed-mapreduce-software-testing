# Distributed Software Testing with MapReduce
___
Assignment delivered by Riccardo Pressiani ([rpress4@uic.edu](mailto:rpress4@uic.edu)) as Homework 1 and 2 for CS441, held at University of Illinois at Chicago during Fall 2017.

## Description
___
The goal of this project was to gain experience with the Apache Hadoop framework and the Amazon AWS platform with a focus on AWS Elastic Map Reduce. To do this, we have been asked to implement a MapReduce job to parallelize software testing on Apache Hadoop.

Two directory can be found in the repository: `application` and `mapreduce-task`. In the first one, the Java application used as sample application has been uploaded. Instead, in the second directory, the Map Reduce application has been uploaded.

In order to retrieve the test line coverage information, the Jacoco plugin for Gradle has been used. The report are generated as XML files and in order to be processed by Hadoop, they are preprocessed with a Python script that extract the information needed and store it as CSV files. The Python script (`XMLTestParser.py`) can be found in the `mapreduce-task` directory.

After being tested locally, the Map Reduce task has been deployed on AWS EMR. The video in which I explain the step followed to do that can be found at: [https://youtu.be/oSY5jLPICxM](https://youtu.be/oSY5jLPICxM)

### Map Reduce implementation
The Map Reduce implementation is structured on two separate jobs. The Map class of the first job accept as input CSV lines and map the input as <(className, lineNumber), test>. The tuples returned by the map are ordered by key. The reducer of the first job accumulate in a list all the test values corresponding to each pair (className, lineNumber), so that the result is <(className, lineNumber), [test1, test2, ...]>.

The second job has been necessary to sort the tuples in descending order by the number of tests that cover each line, as requested in the assignment. This could not be done in the first job since, at the time the first Map is running, the number of the tests that cover a line is not known and the sorting of the tuples is based on the `compareTo` method of the map class.

## Usage
___

### Jacoco Line Coverage

To run the Jacoco plugin, you have to build the project and then run the Jacoco plugin with Gradle.

```shell
./gradlew build
./gradlew jacocoTestReport
```

### Python XML Parser

XMLTestParser will create a diretory inside <dir> with the preprocessed line coverage report. The new files will be in CSV format readable by the MapReduce job.

```python
python XMLTestParser.py <dir>
```


### Run MapReduce job locally

We assume the user has a fully working installation of Apache Hadoop, HDFS running and the input files already uploaded. The <temp directory> is a support directory needed to store the result of the first MapReduce job.

```shell
hadoop <jar archive> <input directory> <temp directory> <output directory>
```

## Limitations
___
At this moment, it is not possible to retrieve automatically the file containing the information about the line coverage of a single test. As a matter of fact, the command `./gradlew jacocoTestReport` generate a unique report containing the line coverage of all the test cases provided in the Java project. In this way, it is not possible to get which and how many tests are covering a single line of the code. To overcome this limit, the input files regarding the line coverage of each test have been generated manually, building the application each time with only one test case.

## References
___
In order to fulfill the assignment the following resources have been used:

* [sbt Reference Manual](http://www.scala-sbt.org/1.x/docs/index.html)
* [Gradle Documentation](https://gradle.org/docs/)
* [The JaCoCo Plugin - Gradle User Guide](https://docs.gradle.org/current/userguide/jacoco_plugin.html)
* [Getting Started: Analyzing Big Data with Amazon EMR](http://docs.aws.amazon.com/emr/latest/ManagementGuide/emr-gs.html)
