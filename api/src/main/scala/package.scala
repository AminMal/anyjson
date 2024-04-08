package aminmal.anyjson

package object api {
  type JReader[T] = aminmal.anyjson.impl.circeImpl.JReader[T]
  type JWriter[T] = aminmal.anyjson.impl.circeImpl.JWriter[T]
  type JFormatter[T] = aminmal.anyjson.impl.circeImpl.JFormatter[T]

  type JValue = aminmal.anyjson.impl.circeImpl.JValue
  type JError = aminmal.anyjson.impl.circeImpl.JError

  type ResultMonad[A] = cats.Id[A]
  type Result[T] = ResultMonad[Either[JError, T]]
}
