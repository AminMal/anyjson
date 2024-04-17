package aminmal.anyjson

import aminmal.anyjson.implcore.JsonLibDescriptor

package object impl extends JsonLibDescriptor {

  override type JValue = io.circe.Json

  override type LibReader[T] = io.circe.Decoder[T]
  override type LibWriter[T] = io.circe.Encoder[T]
  override type LibFormatter[T] = io.circe.Codec[T]

  override type JError = io.circe.Error
  override type ResultMonad[A] = cats.Id[A]

  override type Result[T] = ResultMonad[Either[JError, T]]

  override def parse(s: String): Result[JValue] = io.circe.parser.parse(s)

  override def write[T : LibWriter](t: T): JValue = implicitly[LibWriter[T]].apply(t)

  override def read[T : LibReader](value: JValue): Either[JError, T] = implicitly[LibReader[T]].decodeJson(value)
}
