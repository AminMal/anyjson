package aminmal.anyjson

import aminmal.anyjson.implcore.JsonLibDescriptor
import zio.json.JsonDecoder.JsonError
import zio.json._

import scala.util.Try

package object impl extends JsonLibDescriptor {

  override type JValue = zio.json.ast.Json

  override type LibReader[T] = JsonDecoder[T]
  override type LibWriter[T] = JsonEncoder[T]
  override type LibFormatter[T] = JsonCodec[T]

  override type JError = JsonError
  override type ResultMonad[A] = cats.Id[A]

  override type Result[T] = ResultMonad[Either[JError, T]]

  override def parse(s: String): Result[JValue] =
    s.fromJson[JValue]
      .left.map(JsonError.Message.apply)

  override def write[T : LibWriter](t: T): JValue = implicitly[LibWriter[T]].toJsonAST(t) match {
    case Left(value) => throw new RuntimeException(value)
    case Right(value) => value
  }

  override def read[T : LibReader](value: JValue): Either[JError, T] = value.as[T].left.map(JsonError.Message.apply)
}
