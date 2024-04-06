package aminmal.core

import play.api.libs.json.JsSuccess

trait Formatter[T] extends Reader[T] with Writer[T] {
  def formatter: LibFormatter[T]
}

private[core] class FormatterImpl[T](override val formatter: LibFormatter[T]) extends Formatter[T] {
  override def write(value: T): JValue = formatter.writes(value)

  override def read(j: JValue): Result[T] = reader.reads(j) match {
    case JsSuccess(value, _) => Right(value)
    case e@play.api.libs.json.JsError(_) => Left(e)
  }

  override def writer: LibWriter[T] = formatter

  override def reader: LibReader[T] = formatter
}

object Formatter {
  def fromLib[T](format: LibFormatter[T]): Formatter[T] = new FormatterImpl[T](format)
}
