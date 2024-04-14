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
        val dir = file("..")
        val libDir = s"apis/api-$libName"

        Project(s"anyjson-$libName", file(s"apis/api-$libName"))
          .settings(
            organization := "com.github.aminmal",
            Compile / organization := "com.github.aminmal",
            Compile / sourceGenerators += Def.task [Seq[File]] {
              val packageFileLoc = dir / s"${libDir}/src/main/scala/package.scala"
              val anyJsonFileLoc = dir / s"${libDir}/src/main/scala/AnyJson.scala"

              val packageFileContent =
                """package aminmal.anyjson
                  |
                  |package object api {
                  |  type JReader[T] = aminmal.anyjson.impl.JReader[T]
                  |  type JWriter[T] = aminmal.anyjson.impl.JWriter[T]
                  |  type JFormatter[T] = aminmal.anyjson.impl.JFormatter[T]
                  |
                  |  type JValue = aminmal.anyjson.impl.JValue
                  |  type JError = aminmal.anyjson.impl.JError
                  |
                  |  type ResultMonad[A] = cats.Id[A]
                  |  type Result[T] = ResultMonad[Either[JError, T]]
                  |}
                  |""".stripMargin

              val anyJsonFileContent =
                """package aminmal.anyjson.api
                  |
                  |import aminmal.anyjson.impl.Macros
                  |
                  |import scala.language.experimental.macros
                  |
                  |object AnyJson {
                  |  def parse(s: String): Result[JValue] = aminmal.anyjson.impl.parse(s)
                  |
                  |  def toJson[T](value: T)(implicit jWriter: JWriter[T]): JValue = jWriter.write(value)
                  |
                  |  def fromJson[T](json: JValue)(implicit jReader: JReader[T]): Result[T] =
                  |    jReader.read(json)
                  |
                  |  def parseAs[T](s: String)(implicit jReader: JReader[T]): Result[T] =
                  |    parse(s).flatMap(fromJson[T])
                  |
                  |  def jReader[T]: JReader[T] = macro Macros.makeJReaderImpl[T]
                  |  def jWriter[T]: JWriter[T] = macro Macros.makeJWriterImpl[T]
                  |  def jFormatter[T]: JFormatter[T] = macro Macros.makeJFormatterImpl[T]
                  |}
                  |""".stripMargin
              IO.write(packageFileLoc, packageFileContent)
              IO.write(anyJsonFileLoc, anyJsonFileContent)
              Seq(packageFileLoc, anyJsonFileLoc)
            }
          ).dependsOn(d)
    }
  }

}
