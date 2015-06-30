version := "0.1.0"

name := "scalautil"

scalaVersion := "2.11.6"

libraryDependencies ++= Seq(
	"org.scalacheck" %% "scalacheck" % "1.12.4" % "test"
)

scalacOptions ++= Seq("-feature", "-deprecation")
