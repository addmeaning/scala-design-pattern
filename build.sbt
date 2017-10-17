name := "designpatterns"

version := "1.0"

scalaVersion := "2.12.3"


scalacOptions := Seq("-unchecked", "-deprecation", "-encoding", "utf8")

javaOptions ++= Seq("-target", "1.8", "-source", "1.8")

publishMavenStyle := true


addCompilerPlugin("org.scalamacros" % "paradise" % "2.1.0" cross CrossVersion.full)

libraryDependencies ++= Seq(
  "org.scalatest" % "scalatest_2.12" % "3.0.4" % "test",
  "com.github.mpilquist" % "simulacrum_2.12" % "0.11.0"
)
