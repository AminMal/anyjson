package aminmal

package object api {
  type JReader[T] = aminmal.impl.playImpl.JReader[T]
  type JWriter[T] = aminmal.impl.playImpl.JWriter[T]
  type JFormatter[T] = aminmal.impl.playImpl.JFormatter[T]

  type JValue = aminmal.impl.playImpl.JValue
  type JError = aminmal.impl.playImpl.JError

  type ResultMonad[A] = cats.Id[A]
  type Result[T] = ResultMonad[Either[JError, T]]
}
