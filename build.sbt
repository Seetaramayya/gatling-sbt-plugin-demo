import io.gatling.sbt.GatlingPlugin._

enablePlugins(GatlingPlugin)

scalaVersion := "2.11.8"

scalacOptions := Seq(
  "-encoding", "UTF-8", "-target:jvm-1.8", "-deprecation",
  "-feature", "-unchecked", "-language:implicitConversions", "-language:postfixOps")

libraryDependencies += "io.gatling.highcharts" % "gatling-charts-highcharts" % "2.2.0" % "test,it,gatling"
libraryDependencies += "io.gatling" % "gatling-test-framework" % "2.2.0" % "test,it,gatling"


def isPerfTest(name: String): Boolean = name endsWith "Simulation"

val baseDir: String = s"${System.getProperty("user.dir")}"
val gatlingSourceDir: String = s"$baseDir/src/perf"
val gatlingResourceDir: String = s"$gatlingSourceDir/resources"
val gatlingScalaSource: String = s"$gatlingSourceDir/scala"
val gatlingJavaSource: String = s"$gatlingSourceDir/java"
val gatlingTarget: String = s"$baseDir/target/scala-2.11/gatling-classes"


lazy val root = (project in file("."))
  .settings(gatlingSettings: _*)
  .configs(Gatling)
  .settings(resourceDirectory in Gatling := new File(gatlingResourceDir))
  .settings(scalaSource in Gatling := new File(gatlingScalaSource))
  .settings(fullClasspath in Gatling += new File(s"$gatlingTarget"))
  .settings(testOptions in Gatling := Seq(Tests.Filter(isPerfTest(_))))

