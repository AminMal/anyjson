import aminmal.anyjson.api.Json
import aminmal.anyjson.api._

object Main extends App {

  case class Person(name: String, age: Int)

  object Person {
    implicit val format: JFormatter[Person] = Json.jFormatter
  }

  val json =
    s"""
       |{
       |  "name":"Amin",
       |  "age": 24
       |}
       |""".stripMargin

  val json2 =
    s"""
       |{
       |  "name_fake":"Amin",
       |  "age": 24
       |}
       |""".stripMargin

  println(Json.toJson(Person("Bob", 24)))
  println(Json.parseAs[Person](json))
  println(Json.parseAs[Person](json2))


}
