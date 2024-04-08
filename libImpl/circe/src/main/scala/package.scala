package aminmal.anyjson.impl

package object circeImpl {

  type JValue = io.circe.Json

  type LibReader[T] = io.circe.Decoder[T]
  type LibWriter[T] = io.circe.Encoder[T]
  type LibFormatter[T] = io.circe.Codec[T]

  type JError = io.circe.Error
  type ResultMonad[A] = cats.Id[A]

  type Result[T] = ResultMonad[Either[JError, T]]

  def parse(s: String): Result[JValue] = io.circe.parser.parse(s)
}
