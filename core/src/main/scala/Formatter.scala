package aminmal.anyjson.core

trait Formatter[T, JS, Error, UnderlyingReader[_], UnderlyingWriter[_], UnderlyingFormatter[_], ResultMonad[_]]
  extends Reader[T, JS, Error, UnderlyingReader, ResultMonad]
    with Writer[T, JS, UnderlyingWriter, ResultMonad]
{
  def formatter: UnderlyingFormatter[T]
}

