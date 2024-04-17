import aminmal.anyjson.api.AnyJson
import aminmal.anyjson.api._

object Main extends App {

  case class Person(name: String, age: Int)

  object Person {
    implicit val format: JFormatter[Person] = AnyJson.jFormatter
  }

  case class Student(grade: String, actualPerson: Person)

  object Student {
    implicit val format: JFormatter[Student] = AnyJson.jFormatter
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

  println(AnyJson.toJson(Person("Bob", 24)))
  println(AnyJson.parseAs[Person](json))
  println(AnyJson.parseAs[Person](json2))

  val student = Student("101", Person("Bob", 24))
  println(AnyJson.toJson(student))


}
