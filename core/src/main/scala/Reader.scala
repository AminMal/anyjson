package aminmal.core

trait Reader[T, JS, Error, UnderlyingReader[_], ResultMonad[_]] {

  def read(value: JS): ResultMonad[Either[Error, T]]

  def reader: UnderlyingReader[T]
}
