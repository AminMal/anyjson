package aminmal.anyjson

import org.json4s.{Formats, MappingException}

import scala.util.Try

package object impl {

  type JValue = org.json4s.JValue

  type LibReader[T] = org.json4s.Reader[T]
  type LibWriter[T] = org.json4s.Writer[T]
  type LibFormatter[T] = org.json4s.JsonFormat[T]

  type JError = Throwable
  type ResultMonad[A] = cats.Id[A]

  type Result[T] = ResultMonad[Either[JError, T]]

  def parse(s: String): Result[JValue] = Try(org.json4s.native.JsonMethods.parse(s)).toEither
}
