package aminmal.anyjson

package object impl {

  type JValue = spray.json.JsValue

  type LibReader[T] = spray.json.JsonReader[T]
  type LibWriter[T] = spray.json.JsonWriter[T]
  type LibFormatter[T] = spray.json.JsonFormat[T]

  type JError = Throwable
  type ResultMonad[A] = cats.Id[A]

  type Result[T] = ResultMonad[Either[JError, T]]

  def parse(s: String): Result[JValue] = Right(spray.json.JsonParser(s))
}
