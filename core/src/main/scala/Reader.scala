package aminmal.core

import play.api.libs.json.JsSuccess

trait Reader[T] {
  def read(j: JValue): Result[T]
  def reader: LibReader[T]
}

private[core] class ReaderImpl[T](override val reader: LibReader[T]) extends Reader[T] {
  override def read(j: JValue): Result[T] = reader.reads(j) match {
    case JsSuccess(value, _) => Right(value)
    case e@play.api.libs.json.JsError(_) => Left(e)
  }
}

object Reader {
  def fromLib[T](reader: LibReader[T]): Reader[T] = new ReaderImpl[T](reader)
}
