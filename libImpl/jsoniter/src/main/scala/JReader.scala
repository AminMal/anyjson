package aminmal.anyjson.impl

import aminmal.anyjson.core.Reader
import com.github.plokhotnyuk.jsoniter_scala.core._

import scala.util.Try

trait JReader[T] extends Reader[T, JValue, JError, LibReader, ResultMonad] {}

final class JReaderImpl[T](override val reader: LibReader[T]) extends JReader[T] {
  override def read(value: JValue): ResultMonad[Either[JError, T]] =
    Try(readFromString[T](value)(reader)).toEither
}

object JReader {
  def fromLib[T](libReader: LibReader[T]): JReader[T] = new JReaderImpl[T](libReader)
}
