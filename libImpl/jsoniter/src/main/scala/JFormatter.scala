package aminmal.anyjson.impl

import aminmal.anyjson.core.Formatter
import com.github.plokhotnyuk.jsoniter_scala.core.{readFromString, writeToString}

import scala.util.Try

trait JFormatter[T]
  extends JReader[T] with JWriter[T]
  with Formatter[T, JValue, JError, LibReader, LibWriter, LibFormatter, ResultMonad] {}

final class JFormatterImpl[T](override val formatter: LibFormatter[T]) extends JFormatter[T] {

  override def read(value: JValue): ResultMonad[Either[JError, T]] = Try(readFromString[T](value)(reader)).toEither

  override def reader: LibReader[T] = formatter

  override def write(t: T): ResultMonad[JValue] = writeToString(t)(formatter)

  override def writer: LibWriter[T] = formatter
}

object JFormatter {
  def fromLib[T](libFormatter: LibFormatter[T]): JFormatter[T] = new JFormatterImpl[T](libFormatter)
}
