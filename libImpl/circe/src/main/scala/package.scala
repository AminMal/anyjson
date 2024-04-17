package aminmal.anyjson

package object impl {

  type JValue = io.circe.Json

  type LibReader[T] = io.circe.Decoder[T]
  type LibWriter[T] = io.circe.Encoder[T]
  type LibFormatter[T] = io.circe.Codec[T]

  type JError = io.circe.Error
  type ResultMonad[A] = cats.Id[A]

  type Result[T] = ResultMonad[Either[JError, T]]

  def parse(s: String): Result[JValue] = io.circe.parser.parse(s)

  def write[T : LibWriter](t: T): JValue = implicitly[LibWriter[T]].apply(t)

  def read[T : LibReader](value: JValue): Either[JError, T] = implicitly[LibReader[T]].decodeJson(value)
}
