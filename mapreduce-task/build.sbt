organization := "com.riccardopressiani"

name := "mapreduce-hw2"

version := "1.0-SNAPSHOT"

libraryDependencies += "com.novocode" % "junit-interface" % "0.10" % "test"
libraryDependencies += "junit" % "junit" % "4.11" % "test"

libraryDependencies += "org.apache.hadoop" % "hadoop-mapreduce-client-core" % "2.8.1"
libraryDependencies += "org.apache.hadoop" % "hadoop-common" % "2.8.1"

fork in run := true
