lazy val root = project.in(file(".")).enablePlugins(PlayJava, SbtWeb)

name := """pathfindplay"""

version := "1.0-SNAPSHOT"

libraryDependencies ++= Seq(
  javaJdbc,
  cache,
  javaWs,
  javaEbean
)



fork in run := true