package aminmal.anyjson

package object api {
  type JReader[T] = aminmal.anyjson.impl.playImpl.JReader[T]
  type JWriter[T] = aminmal.anyjson.impl.playImpl.JWriter[T]
  type JFormatter[T] = aminmal.anyjson.impl.playImpl.JFormatter[T]

  type JValue = aminmal.anyjson.impl.playImpl.JValue
  type JError = aminmal.anyjson.impl.playImpl.JError

  type ResultMonad[A] = cats.Id[A]
  type Result[T] = ResultMonad[Either[JError, T]]
}
