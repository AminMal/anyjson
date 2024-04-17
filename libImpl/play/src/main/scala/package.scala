package aminmal.anyjson

package object impl {

  type JValue = play.api.libs.json.JsValue

  type LibReader[T] = play.api.libs.json.Reads[T]
  type LibWriter[T] = play.api.libs.json.Writes[T]
  type LibFormatter[T] = play.api.libs.json.Format[T]

  type JError = play.api.libs.json.JsError
  type ResultMonad[A] = cats.Id[A]

  type Result[T] = ResultMonad[Either[JError, T]]

  def parse(s: String): Result[JValue] = Right(play.api.libs.json.Json.parse(s))

  def write[T : LibWriter](t: T): JValue = implicitly[LibWriter[T]].writes(t)

  def read[T : LibReader](value: JValue): Either[JError, T] = implicitly[LibReader[T]].reads(value)
    .fold(errors => Left(new JError(errors)), Right.apply)
}
