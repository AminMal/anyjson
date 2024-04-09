package aminmal.anyjson.impl

import aminmal.anyjson.core.Formatter

import scala.util.Try

trait JFormatter[T]
  extends JReader[T] with JWriter[T]
  with Formatter[T, JValue, JError, LibReader, LibWriter, LibFormatter, ResultMonad] {}

final class JFormatterImpl[T](override val formatter: LibFormatter[T]) extends JFormatter[T] {

  override def read(value: JValue): ResultMonad[Either[JError, T]] = Try(formatter.read(value)).toEither

  override def reader: LibReader[T] = formatter

  override def write(t: T): ResultMonad[JValue] = formatter.write(t)

  override def writer: LibWriter[T] = formatter
}

object JFormatter {
  def fromLib[T](libFormatter: LibFormatter[T]): JFormatter[T] = new JFormatterImpl[T](libFormatter)
}
