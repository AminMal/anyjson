package aminmal.core

trait Writer[T] {
  def write(value: T): JValue
  def writer: LibWriter[T]
}

private[core] class WriterImpl[T](override val writer: LibWriter[T]) extends Writer[T] {
  override def write(value: T): JValue = writer.writes(value)
}

object Writer {
  def fromLib[T](writer: LibWriter[T]): Writer[T] = new WriterImpl[T](writer)
}
