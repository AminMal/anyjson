package aminmal.anyjson

import zio.json._

package object impl {

  type JValue = zio.json.ast.Json

  type LibReader[T] = JsonDecoder[T]
  type LibWriter[T] = JsonEncoder[T]
  type LibFormatter[T] = JsonCodec[T]

  type JError = JsonError
  type ResultMonad[A] = cats.Id[A]

  type Result[T] = ResultMonad[Either[JError, T]]

  def parse(s: String): Result[JValue] =
    s.fromJson[JValue]
      .left.map(JsonError.Message.apply)

}
