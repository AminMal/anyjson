import aminmal.anyjson.api._

case class Person(
                 name: String,
                 age: Int
                 )

object Person {
  implicit val formatter: JFormatter[Person] = AnyJson.jFormatter
}
