package aminmal.anyjson

import scala.util.Try

package object impl {

  type JValue = org.json4s.JValue

  type LibReader[T] = org.json4s.Reader[T]
  type LibWriter[T] = org.json4s.Writer[T]
  type LibFormatter[T] = org.json4s.JsonFormat[T]

  type JError = Throwable
  type ResultMonad[A] = cats.Id[A]

  type Result[T] = ResultMonad[Either[JError, T]]

  def parse(s: String): Result[JValue] = Try(org.json4s.jackson.JsonMethods.parse(s)).toEither

  def write[T : LibWriter](t: T): JValue = implicitly[LibWriter[T]].write(t)

  def read[T : LibReader](value: JValue): Either[JError, T] = implicitly[LibReader[T]].readEither(value)

}
