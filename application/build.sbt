organization := "com.salvadorjacobi"

name := "tetris"

version := "1.0-SNAPSHOT"

libraryDependencies += "com.novocode" % "junit-interface" % "0.10" % "test"
libraryDependencies += "junit" % "junit" % "4.11" % "test"

fork in run := true

lazy val jacoco = Seq(
  jacocoReportSettings in run := JacocoReportSettings()
    .withFormats(JacocoReportFormats.XML)
    .withFileEncoding("UTF-8")
)

// jacoco.settings