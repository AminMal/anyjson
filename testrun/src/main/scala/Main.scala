import aminmal.anyjson.api.AnyJson

object Main extends App {

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

  println(AnyJson.toJson(Person("Bob", 24)))
  println(AnyJson.parseAs[Person](json))
  println(AnyJson.parseAs[Person](json2))
}
