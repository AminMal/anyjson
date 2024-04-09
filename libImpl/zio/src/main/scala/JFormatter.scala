package aminmal.anyjson.impl

import aminmal.anyjson.core.Formatter
import zio.json.JsonDecoder.JsonError

trait JFormatter[T]
  extends JReader[T] with JWriter[T]
  with Formatter[T, JValue, JError, LibReader, LibWriter, LibFormatter, ResultMonad] {}

final class JFormatterImpl[T](override val formatter: LibFormatter[T]) extends JFormatter[T] {

  override def read(value: JValue): ResultMonad[Either[JError, T]] =
    value.as[T](reader).left.map(JsonError.Message.apply)

  override def reader: LibReader[T] = formatter.decoder

  override def write(t: T): ResultMonad[JValue] = writer.toJsonAST(t) match {
    case Left(value) => throw new RuntimeException(value)
    case Right(value) => value
  }

  override def writer: LibWriter[T] = formatter.encoder
}

object JFormatter {
  def fromLib[T](libFormatter: LibFormatter[T]): JFormatter[T] = new JFormatterImpl[T](libFormatter)
}
