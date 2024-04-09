package aminmal.anyjson.impl

import aminmal.anyjson.core.Reader

import scala.util.Try

trait JReader[T] extends Reader[T, JValue, JError, LibReader, ResultMonad] {}

final class JReaderImpl[T](override val reader: LibReader[T]) extends JReader[T] {
  override def read(value: JValue): ResultMonad[Either[JError, T]] = Try(reader.read(value)).toEither
}

object JReader {
  def fromLib[T](libReader: LibReader[T]): JReader[T] = new JReaderImpl[T](libReader)
}
