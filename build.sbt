import io.gatling.sbt.GatlingPlugin._

enablePlugins(GatlingPlugin)

scalaVersion := "2.11.8"

scalacOptions := Seq(
  "-encoding", "UTF-8", "-target:jvm-1.8", "-deprecation",
  "-feature", "-unchecked", "-language:implicitConversions", "-language:postfixOps")

libraryDependencies += "io.gatling.highcharts" % "gatling-charts-highcharts" % "2.2.0" % "test"
libraryDependencies += "io.gatling"            % "gatling-test-framework"    % "2.2.0" % "test"

//lazy val PerfTest = config("perf") extend(Test)


def isPerfTest(name: String): Boolean = name endsWith "Simulation"

val gatlingSourceDir: String = s"${System.getProperty("user.dir")}/src/perf"
val gatlingResourceDir: String = s"$gatlingSourceDir/resources"
val gatlingScalaSource: String = s"$gatlingSourceDir/scala"
val gatlingJavaSource: String = s"$gatlingSourceDir/java"

lazy val root = (project in file(".")).
  configs(Gatling).
  settings(resourceDirectory in Gatling := new File(gatlingResourceDir)).
  settings(sourceDirectory in Gatling := new File(gatlingScalaSource)).
  settings(scalaSource in Gatling := new File(gatlingScalaSource)).
  settings(javaSource in Gatling := new File(gatlingJavaSource)).
  settings(testOptions in Gatling := Seq(Tests.Filter(isPerfTest(_))))

gatlingSettings

