package aminmal.anyjson.implcore

trait JsonLibDescriptor {

  type JValue

  type LibReader[T]
  type LibWriter[T]
  type LibFormatter[T]

  type JError
  type ResultMonad[A]

  type Result[T]

  def parse(s: String): Result[JValue]

  def write[T : LibWriter](t: T): JValue

  def read[T : LibReader](value: JValue): Either[JError, T]
}
