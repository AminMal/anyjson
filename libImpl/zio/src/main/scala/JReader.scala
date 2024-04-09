package aminmal.anyjson.impl

import aminmal.anyjson.core.Reader
import zio.json.JsonDecoder.JsonError

trait JReader[T] extends Reader[T, JValue, JError, LibReader, ResultMonad] {}

final class JReaderImpl[T](override val reader: LibReader[T]) extends JReader[T] {
  override def read(value: JValue): ResultMonad[Either[JError, T]] =
    value.as[T](reader).left.map(JsonError.Message.apply)
}

object JReader {
  def fromLib[T](libReader: LibReader[T]): JReader[T] = new JReaderImpl[T](libReader)
}
