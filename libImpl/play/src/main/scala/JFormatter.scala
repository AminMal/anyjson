package aminmal.anyjson.impl.playImpl

import aminmal.anyjson.core.Formatter

sealed trait JFormatter[T]
  extends JReader[T] with JWriter[T]
  with Formatter[T, JValue, JError, LibReader, LibWriter, LibFormatter, cats.Id] {}

final class JFormatterImpl[T](override val formatter: LibFormatter[T]) extends JFormatter[T] {

  override def read(value: JValue): Either[JError, T] = formatter.reads(value)
    .fold(errors => Left(new JError(errors)), Right.apply)

  override def write(t: T): JValue = formatter.writes(t)

  override def reader: LibReader[T] = formatter

  override def writer: LibWriter[T] = formatter
}

object JFormatter {
  def fromLib[T](libFormatter: LibFormatter[T]): JFormatter[T] = new JFormatterImpl[T](libFormatter)
}
