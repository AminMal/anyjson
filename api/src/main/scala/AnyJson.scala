package aminmal.anyjson.api

import aminmal.anyjson.impl._

import scala.language.experimental.macros

object AnyJson {
  def parse(s: String): Result[JValue] = aminmal.anyjson.impl.parse(s)

  def toJson[T](value: T)(implicit jWriter: JWriter[T]): JValue = write(value)

  def fromJson[T](json: JValue)(implicit jReader: JReader[T]): Result[T] = read(json)

  def parseAs[T](s: String)(implicit jReader: JReader[T]): Result[T] = parse(s).flatMap(fromJson[T])

  def jReader[T]: JReader[T] = macro Macros.makeReaderImpl[T]
  def jWriter[T]: JWriter[T] = macro Macros.makeWriterImpl[T]
  def jFormatter[T]: JFormatter[T] = macro Macros.makeFormatterImpl[T]
}