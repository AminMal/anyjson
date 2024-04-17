import scala.collection.Seq

val SCALA_VERSION = "2.13.13"

ThisBuild / version := "0.2.1"

ThisBuild / scalaVersion := SCALA_VERSION

ThisBuild / organization := "com.github.aminmal"

ThisBuild / name := "anyjson"

name := "anyjson"


lazy val reflection = "org.scala-lang" % "scala-reflect" % SCALA_VERSION

lazy val catsVersion = "2.10.0"
lazy val catsCore = "org.typelevel" %% "cats-core" % catsVersion

lazy val playJson = "com.typesafe.play" %% "play-json" % "2.10.4"

val circeVersion = "0.14.1"
lazy val circe = Seq(
  "io.circe" %% "circe-core",
  "io.circe" %% "circe-generic",
  "io.circe" %% "circe-parser"
).map(_ % circeVersion)

lazy val jsonIter = Seq(
  "com.github.plokhotnyuk.jsoniter-scala" %% "jsoniter-scala-core"   % "2.28.4",
  "com.github.plokhotnyuk.jsoniter-scala" %% "jsoniter-scala-macros" % "2.28.4"
)

lazy val json4sNative = "org.json4s" %% "json4s-native" % "4.0.7"
lazy val json4sJackson = "org.json4s" %% "json4s-jackson" % "4.0.7"

lazy val spray = "io.spray" %%  "spray-json" % "1.3.6"

lazy val zioJson = "dev.zio" %% "zio-json" % "0.6.2"

lazy val libImplCore = (project in file("libImpl/core"))
  .settings(
    name := "libImpl-core",
    idePackagePrefix := Some("aminmal.anyjson.implcore"),
    libraryDependencies += reflection,
  )

lazy val playImpl = (project in file("libImpl/play"))
  .settings(
    name := "playImpl",
    idePackagePrefix := Some("aminmal.anyjson.impl"),
    libraryDependencies ++= Seq(playJson, catsCore),
  ).dependsOn(libImplCore)

lazy val circeImpl = (project in file("libImpl/circe"))
  .settings(
    name := "circeImpl",
    idePackagePrefix := Some("aminmal.anyjson.impl"),
    libraryDependencies ++= circe,
  ).dependsOn(libImplCore)

lazy val jsoniterImpl = (project in file("libImpl/jsoniter"))
  .settings(
    name := "jsoniterImpl",
    idePackagePrefix := Some("aminmal.anyjson.impl"),
    libraryDependencies ++= (catsCore +: jsonIter),
  ).dependsOn(libImplCore)

lazy val json4sNativeImpl = (project in file("libImpl/json4s"))
  .settings(
    name := "json4sNativeImpl",
    idePackagePrefix := Some("aminmal.anyjson.impl"),
    libraryDependencies ++= Seq(catsCore, json4sNative),
  ).dependsOn(libImplCore)

lazy val json4sJacksonImpl = (project in file("libImpl/json4sjackson"))
  .settings(
    name := "json4sJacksonImpl",
    idePackagePrefix := Some("aminmal.anyjson.impl"),
    libraryDependencies ++= Seq(catsCore, json4sJackson),
  ).dependsOn(libImplCore)

lazy val sprayJsonImpl = (project in file("libImpl/spray"))
  .settings(
    name := "sprayJsonImpl",
    idePackagePrefix := Some("aminmal.anyjson.impl"),
    libraryDependencies ++= Seq(catsCore, spray),
  ).dependsOn(libImplCore)

lazy val zioJsonImpl = (project in file("libImpl/zio"))
  .settings(
    name := "zioJsonImpl",
    idePackagePrefix := Some("aminmal.anyjson.impl"),
    libraryDependencies ++= Seq(catsCore, zioJson),
  ).dependsOn(libImplCore)

lazy val api = (project in file("api"))
  .settings(
    name := "api",
    idePackagePrefix := Some("aminmal.anyjson.api"),
    publish / skip := true,
  ).dependsOn(zioJsonImpl)

lazy val libImpl = (project in file("libImpl"))
  .aggregate(libImplCore, circeImpl, json4sNativeImpl, json4sJacksonImpl, jsoniterImpl, playImpl, sprayJsonImpl, zioJsonImpl)
  .settings(
    publish / skip := true
  )

lazy val apis = (project in file("apis"))
  .aggregate(
    List("circe", "json4s", "json4s-jackson", "jsoniter", "play", "spray", "zio-json")
      .map(name => LocalProject(s"anyjson-$name")) *
  )

lazy val anyjson = (project in file("."))
  .aggregate(api, libImpl, apis)
  .settings(
    publish / skip := true
  )

lazy val testrun = (project in file("testrun"))
  .settings(
    name := "testrun",
    publish / skip := true,
  ).dependsOn(api)
