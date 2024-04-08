package aminmal.anyjson

package object api {
  type JReader[T] = aminmal.anyjson.impl.JReader[T]
  type JWriter[T] = aminmal.anyjson.impl.JWriter[T]
  type JFormatter[T] = aminmal.anyjson.impl.JFormatter[T]

  type JValue = aminmal.anyjson.impl.JValue
  type JError = aminmal.anyjson.impl.JError

  type ResultMonad[A] = cats.Id[A]
  type Result[T] = ResultMonad[Either[JError, T]]
}
