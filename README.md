Gatling tests from different folder
==================================

A simple project showing how to configure and use Gatling's SBT plugin to run Gatling simulations from different folder other than `test`

This project forked from [gatling-sbt-plugin-demo](https://github.com/gatling/gatling-sbt-plugin-demo)

Settings
-------
 As in documentation ([sbt-pluging-2.2.0](http://gatling.io/docs/2.2.0/extensions/sbt_plugin.html#default-settings)) specified, configured `scalaSource` that alone not sufficient. I added following settings

 - Added `gatling` scope for required libraries

```
libraryDependencies += "io.gatling.highcharts" % "gatling-charts-highcharts" % "2.2.0" % "test,it,gatling"
libraryDependencies += "io.gatling" % "gatling-test-framework" % "2.2.0" % "test,it,gatling"
```
 - Along with `scalaSource`, `resources`, I have to added compiled classes path to `classpath`

```
.settings(resourceDirectory in Gatling := new File(gatlingResourceDir))
.settings(scalaSource in Gatling := new File(gatlingScalaSource))
.settings(fullClasspath in Gatling += new File(s"$gatlingTarget"))
```
 - NOTE: Don't override settings with `GatlingPlugin.gatlingSettings` at the bottom of the `build.sbt` (I did this mistake all my changes are not taken)

Get the project
---------------

```bash
git clone https://github.com/gatling/gatling-sbt-plugin-demo.git && cd gatling-sbt-plugin-demo
```

Start SBT
---------
```bash
$ sbt
```

Run all simulations
-------------------

```bash
> test
```

Run a single simulation
-----------------------

```bash
> testOnly computerdatabase.BasicSimulation
```

List all tasks
--------------------

```bash
> tasks
```