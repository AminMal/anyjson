package aminmal.anyjson

import aminmal.anyjson.implcore.JsonLibDescriptor
import com.github.plokhotnyuk.jsoniter_scala.{core => libcore}

import scala.util.Try

package object impl extends JsonLibDescriptor {

  override type JValue = String

  override type LibReader[T] = libcore.JsonValueCodec[T]
  override type LibWriter[T] = libcore.JsonValueCodec[T]
  override type LibFormatter[T] = libcore.JsonValueCodec[T]

  override type JError = Throwable
  override type ResultMonad[A] = cats.Id[A]

  override type Result[T] = ResultMonad[Either[JError, T]]

  // for now, will have to see what is the parsing method in jsoniter
  override def parse(s: String): Result[JValue] = Right(s)

  override def write[T : LibWriter](t: T): JValue = libcore.writeToString(t)(implicitly[LibWriter[T]])

  override def read[T : LibReader](value: JValue): Either[JError, T] =
    Try(libcore.readFromString[T](value)(implicitly[LibReader[T]])).toEither
}
