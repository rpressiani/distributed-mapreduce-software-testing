# HW1 - CS441/CS474
___
Assignment delivered by Riccardo Pressiani ([rpress4@uic.edu](mailto:rpress4@uic.edu)) as Homework 2 for CS441, held at University of Illinois at Chicago during Fall 2017.

## Description
___
The goal of this homework was to gain experience with the Apache Hadoop framework and the Amazon AWS platform with a focus on AWS Elastic Map Reduce.

Two directory can be found in the repository: `application` and `mapreduce-task`. In the first one, the Java application used for the previous homework has been uploaded along with an expanded test set. Instead, in the second directory, the Map Reduce application implemented for this homework has been uploaded.

In order to retrieve the test line coverage information, the Jacoco plugin for Gradle has been used. The report are generated as XML files and in order to be processed by Hadoop, they are preprocessed with a Python script that extract the information needed and store it as CSV files. The Python script (`XMLTestParser.py`) can be found in the `mapreduce-task` directory.

After being tested locally, the Map Reduce task has been deployed on AWS EMR. The video in which I explain the step followed to do that can be found at: 

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

```
./gradlew run 
sbt run
```

`sbt run`

* gradle jacoco
* python parser -directory-
* hadoop le cose in locale con le tre direcoty
*


## Limitations
___
* no automatic tests with jacoco

## References
___
In order to fulfill the assignment the following resources have been used:

* [sbt Reference Manual](http://www.scala-sbt.org/1.x/docs/index.html)
* [Gradle Documentation](https://gradle.org/docs/)
* [The JaCoCo Plugin - Gradle User Guide](https://docs.gradle.org/current/userguide/jacoco_plugin.html)
* [Getting Started: Analyzing Big Data with Amazon EMR](http://docs.aws.amazon.com/emr/latest/ManagementGuide/emr-gs.html)
