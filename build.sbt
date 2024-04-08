ThisBuild / version := "0.1.0-SNAPSHOT"

val SCALA_VERSION = "2.13.13"

ThisBuild / scalaVersion := SCALA_VERSION

lazy val reflection = "org.scala-lang" % "scala-reflect" % SCALA_VERSION


val catsVersion = "2.10.0"
val catsCore = "org.typelevel" %% "cats-core" % catsVersion

val playJson = "com.typesafe.play" %% "play-json" % "2.10.4"

lazy val core = (project in file("core"))
  .settings(
    name := "core",
    idePackagePrefix := Some("aminmal.anyjson.core"),
  )

lazy val playImpl = (project in file("libImpl/play"))
  .settings(
    name := "playImpl",
    idePackagePrefix := Some("aminmal.anyjson.impl.playImpl"),
    libraryDependencies ++= Seq(reflection, playJson, catsCore)
  ).dependsOn(core)

lazy val api = (project in file("api"))
  .settings(
    name := "api",
    idePackagePrefix := Some("aminmal.anyjson.api")
  ).dependsOn(playImpl)

lazy val testrun = (project in file("testrun"))
  .settings(
    name := "testrun"
  ).dependsOn(api)
