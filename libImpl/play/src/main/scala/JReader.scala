package aminmal.impl.playImpl

import aminmal.core.Reader

trait JReader[T] extends Reader[T, JValue, JError, LibReader, cats.Id] {}

final class JReaderImpl[T](override val reader: LibReader[T]) extends JReader[T] {
  override def read(value: JValue): Either[JError, T] = reader.reads(value)
    .fold(errors => Left(new JError(errors)), Right.apply)
}

object JReader {
  def fromLib[T](libReader: LibReader[T]): JReader[T] = new JReaderImpl[T](libReader)
}

