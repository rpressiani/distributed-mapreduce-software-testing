libraryDependencies ++= Seq(
    "org.jacoco" % "org.jacoco.core" % "0.5.7.201204190339" artifacts(Artifact("org.jacoco.core", "jar", "jar")),
    "org.jacoco" % "org.jacoco.report" % "0.5.7.201204190339" artifacts(Artifact("org.jacoco.report", "jar", "jar"))
    )

addSbtPlugin("com.github.sbt" % "sbt-jacoco" % "3.0.2")

// JacocoReportSettings("Jacoco Coverage Report", None, JacocoThresholds(), Seq(JacocoReportFormats.ScalaHTML), "utf-8")


// jacoco.reportFormats   in jacoco.Config := Seq(XMLReport("utf-8"), HTMLReport("utf-8"))
// reportSettings in jacoco.Config := Seq("Jacoco Coverage Report", None, JacocoThresholds(), Seq(JacocoReportFormats.ScalaHTML), "utf-8")
