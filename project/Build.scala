import sbt.*
import Keys._

object Build extends AutoPlugin {

  lazy val libs = List(
    ("circeImpl", "circe"), ("json4sNativeImpl", "json4s"), ("json4sJacksonImpl", "json4s-jackson"), ("jsoniterImpl", "jsoniter"), ("playImpl", "play"), ("sprayJsonImpl", "spray"), ("zioJsonImpl", "zio-json")
  )
  override def extraProjects: Seq[Project] = {
    libs.map {
      case (name, libName) =>
        val d = LocalProject(name)
        val rootDir = file("..")
        val libDir = s"apis/api-$libName"

        Project(s"anyjson-$libName", file(s"apis/api-$libName"))
          .settings(
            organization := "com.github.aminmal",
            Compile / organization := "com.github.aminmal",
            Compile / sourceGenerators += Def.task [Seq[File]] {
              val packageFileLoc = rootDir / s"${libDir}/src/main/scala/package.scala"
              val anyJsonFileLoc = rootDir / s"${libDir}/src/main/scala/AnyJson.scala"

              IO.copyDirectory(rootDir / s"api", rootDir / libDir)
              Seq(packageFileLoc, anyJsonFileLoc)
            }
          ).dependsOn(d)
    }
  }

}
