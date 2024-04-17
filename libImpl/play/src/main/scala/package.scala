package aminmal.anyjson

import aminmal.anyjson.implcore.JsonLibDescriptor

package object impl extends JsonLibDescriptor {

  override type JValue = play.api.libs.json.JsValue

  override type LibReader[T] = play.api.libs.json.Reads[T]
  override type LibWriter[T] = play.api.libs.json.Writes[T]
  override type LibFormatter[T] = play.api.libs.json.Format[T]

  override type JError = play.api.libs.json.JsError
  override type ResultMonad[A] = cats.Id[A]

  override type Result[T] = ResultMonad[Either[JError, T]]

  override def parse(s: String): Result[JValue] = Right(play.api.libs.json.Json.parse(s))

  override def write[T : LibWriter](t: T): JValue = implicitly[LibWriter[T]].writes(t)

  override def read[T : LibReader](value: JValue): Either[JError, T] = implicitly[LibReader[T]].reads(value)
    .fold(errors => Left(new JError(errors)), Right.apply)
}
