package aminmal.api

import aminmal.core._
import aminmal.cmp._

import scala.language.experimental.macros

object Json {

  def formatter[T]: Formatter[T] = macro JsMacros.makeFormatterImpl[T]

  def reader[T]: Reader[T] = macro JsMacros.makeReaderImpl[T]

  def writer[T]: Writer[T] = macro JsMacros.makeWriterImpl[T]

  def parse(s: String): Result[JValue] = aminmal.core.parse(s)

  def parseAs[T](s: String)(implicit r: Reader[T]): Result[T] = parse(s).flatMap(fromJson[T])

  def toJson[T](value: T)(implicit w: Writer[T]): JValue = w.write(value)

  def fromJson[T](j: JValue)(implicit r: Reader[T]): Result[T] = r.read(j)

}
