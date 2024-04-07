ThisBuild / version := "0.1.0-SNAPSHOT"

ThisBuild / scalaVersion := "2.13.13"

lazy val reflection = "org.scala-lang" % "scala-reflect" % "2.13.13"


val catsVersion = "2.10.0"
val catsCore = "org.typelevel" %% "cats-core" % catsVersion

val playJson = "com.typesafe.play" %% "play-json" % "2.7.4"

lazy val core = (project in file("core"))
  .settings(
    name := "core",
    idePackagePrefix := Some("aminmal.core"),
    libraryDependencies ++= Seq(
      catsCore, playJson
    )
  )

lazy val cmp = (project in file("cmp"))
  .settings(
    name := "cmp",
    idePackagePrefix := Some("aminmal.cmp"),
    libraryDependencies ++= Seq(reflection)
  ).dependsOn(core)

lazy val api = (project in file("api"))
  .settings(
    name := "api",
    idePackagePrefix := Some("aminmal.api")
  ).dependsOn(cmp)

lazy val testrun = (project in file("testrun"))
  .settings(
    name := "testrun"
  ).dependsOn(api)
