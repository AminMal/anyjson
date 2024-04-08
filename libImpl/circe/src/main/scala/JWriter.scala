package aminmal.anyjson.impl

import aminmal.anyjson.core.Writer

trait JWriter[T] extends Writer[T, JValue, LibWriter, ResultMonad] {}

final class JWriterImpl[T](override val writer: LibWriter[T]) extends JWriter[T] {
  override def write(t: T): ResultMonad[JValue] = writer(t)
}

object JWriter {
  def fromLib[T](libWriter: LibWriter[T]): JWriter[T] = new JWriterImpl[T](libWriter)
}
