package aminmal

import cats.Id
import play.api.libs.json._

package object core {

  type JValue = JsValue

  type JsError = play.api.libs.json.JsError
  type ResultMonad[A] = Id[A]
  type Result[A] = ResultMonad[Either[JsError, A]]

  type LibReader[T] = Reads[T]
  type LibWriter[T] = Writes[T]
  type LibFormatter[T] = Format[T]

  def parse(s: String): Result[JValue] = Right(Json.parse(s))
}
