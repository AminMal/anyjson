package aminmal.anyjson

import aminmal.anyjson.implcore.JsonLibDescriptor

import scala.util.Try

package object impl extends JsonLibDescriptor {

  override type JValue = spray.json.JsValue

  override type LibReader[T] = spray.json.JsonReader[T]
  override type LibWriter[T] = spray.json.JsonWriter[T]
  override type LibFormatter[T] = spray.json.JsonFormat[T]

  override type JError = Throwable
  override type ResultMonad[A] = cats.Id[A]

  override type Result[T] = ResultMonad[Either[JError, T]]

  override def parse(s: String): Result[JValue] = Right(spray.json.JsonParser(s))

  override def write[T : LibWriter](t: T): JValue = implicitly[LibWriter[T]].write(t)

  override def read[T : LibReader](value: JValue): Either[JError, T] = Try(implicitly[LibReader[T]].read(value)).toEither
}
