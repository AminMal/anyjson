package aminmal.anyjson

import aminmal.anyjson.implcore.JsonLibDescriptor

import scala.util.Try

package object impl extends JsonLibDescriptor {

  override type JValue = org.json4s.JValue

  override type LibReader[T] = org.json4s.Reader[T]
  override type LibWriter[T] = org.json4s.Writer[T]
  override type LibFormatter[T] = org.json4s.JsonFormat[T]

  override type JError = Throwable
  override type ResultMonad[A] = cats.Id[A]

  override type Result[T] = ResultMonad[Either[JError, T]]

  override def parse(s: String): Result[JValue] = Try(org.json4s.jackson.JsonMethods.parse(s)).toEither

  override def write[T : LibWriter](t: T): JValue = implicitly[LibWriter[T]].write(t)

  override def read[T : LibReader](value: JValue): Either[JError, T] = implicitly[LibReader[T]].readEither(value)

}
