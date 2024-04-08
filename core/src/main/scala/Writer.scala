package aminmal.anyjson.core

trait Writer[T, JS, UnderlyingWriter[_], ResultMonad[_]] {

  def write(t: T): ResultMonad[JS]

  def writer: UnderlyingWriter[T]
}