package aminmal

object Main extends App {

  import aminmal.api.Json

  case class Person(name: String, age: Int)

  object Person {
    implicit val formatter = Json.formatter[Person]
  }

  val json =
    s"""
       |{
       |  "name": "Amin",
       |  "age": 21
       |}
       |""".stripMargin

  val p = Person("Bob", 22)

  println(Json.toJson(p))
  println(Json.parseAs[Person](json))

}
