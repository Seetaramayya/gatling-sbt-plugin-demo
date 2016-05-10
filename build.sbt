import io.gatling.sbt.GatlingPlugin._

enablePlugins(GatlingPlugin)

scalaVersion := "2.11.8"

scalacOptions := Seq(
  "-encoding", "UTF-8", "-target:jvm-1.8", "-deprecation",
  "-feature", "-unchecked", "-language:implicitConversions", "-language:postfixOps")

libraryDependencies += "io.gatling.highcharts" % "gatling-charts-highcharts" % "2.2.0" % "test,it,gatling"
libraryDependencies += "io.gatling" % "gatling-test-framework" % "2.2.0" % "test,it,gatling"


def isPerfTest(name: String): Boolean = name endsWith "Simulation"

lazy val root = (project in file("."))
  .settings(gatlingSettings: _*)
  .configs(Gatling)
  .settings(resourceDirectory in Gatling := new File(sourceDirectory.value, "perf/resources"))
  .settings(scalaSource in Gatling := new File(sourceDirectory.value, "perf/scala"))
  .settings(fullClasspath in Gatling += new File(crossTarget.value, "gatling-classes"))
  .settings(testOptions in Gatling := Seq(Tests.Filter(isPerfTest(_))))

