package aminmal.anyjson.impl

import aminmal.anyjson.core.Writer

trait JWriter[T] extends Writer[T, JValue, LibWriter, ResultMonad] {}

final class JWriterImpl[T](override val writer: LibWriter[T]) extends JWriter[T] {
  override def write(t: T): ResultMonad[JValue] =
    writer.toJsonAST(t) match {
      case Left(value) => throw new RuntimeException(value)
      case Right(value) => value
    }
}
