package aminmal.anyjson

import zio.json.JsonDecoder.JsonError
import zio.json._

import scala.util.Try

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

  def write[T : LibWriter](t: T): JValue = implicitly[LibWriter[T]].toJsonAST(t) match {
    case Left(value) => throw new RuntimeException(value)
    case Right(value) => value
  }

  def read[T : LibReader](value: JValue): Either[JError, T] = value.as[T].left.map(JsonError.Message.apply)

}
