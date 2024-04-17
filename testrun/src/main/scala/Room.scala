import aminmal.anyjson.api._

case class Room(
               name: String,
               owner: Person
               )

object Room {
  implicit val formatter: JFormatter[Room] = AnyJson.jFormatter
}
