package aminmal.anyjson

import com.github.plokhotnyuk.jsoniter_scala.core

import scala.util.Try

package object impl {

  type JValue = String

  type LibReader[T] = core.JsonValueCodec[T]
  type LibWriter[T] = core.JsonValueCodec[T]
  type LibFormatter[T] = core.JsonValueCodec[T]

  type JError = Throwable
  type ResultMonad[A] = cats.Id[A]

  type Result[T] = ResultMonad[Either[JError, T]]

  // for now, will have to see what is the parsing method in jsoniter
  def parse(s: String): Result[JValue] = Right(s)

  import com.github.plokhotnyuk.jsoniter_scala.core._

  def write[T : LibWriter](t: T): JValue = writeToString(t)(implicitly[LibWriter[T]])

  def read[T : LibReader](value: JValue): Either[JError, T] =
    Try(readFromString[T](value)(implicitly[LibReader[T]])).toEither
}
