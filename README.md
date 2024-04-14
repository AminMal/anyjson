# AnyJson

AnyJson provides a unified syntax for all the different JSON libraries in Scala.
Well, not all of them, the ones who follow the type-class formatting pattern.

## Supported libraries
AnyJson currently supports:

- [X] PlayJson
- [X] Circe
- [X] Json4s
- [X] Json4sJackson
- [X] Jsoniter
- [X] SprayJson
- [X] ZIO Json

## How to Use
As already mentioned, this library also follows the Codec type class pattern.
So you'll need to define formatters/readers/writers for your classes. 
The general behavior of them is defined by the library, for instance, if you use jsoniter,
you don't need to create them for every single class, only the top-most class.
But if you're using play under the hood, you'll need to provide one for each of your classes.


Considering a simple example, here's how you use `AnyJson`:

### Convert to JSON
```scala
import aminmal.anyjson.api.AnyJson
import aminmal.anyjson.api._

case class Person(name: String, age: Int)

object Person {
  implicit val formatter: JFormatter[Person] = AnyJson.jFormatter
}

val person = Person("Bob", 42)
val json: JValue = AnyJson.toJson(person) // inner-library-specific json AST representing {"name":"Bob,"age":42}
```

### Convert from JSON
```scala
val json: JValue = AnyJson.toJson(Person("Bob", 42))
val person = AnyJson.fromJson[Person](json)
```

### Parse to JValue and types
```scala
val str = "{\"name\":\"Bob\",\"age\":42}"
val json = AnyJson.parse(str)
val person = AnyJson.parseAs[Person](json)
```

## Apply library-specific configurations
If a library supports some sort of configurations, you should be able to also apply it to anyjson, for instance,
play uses an implicit configuration instance for reading and writing:
```scala
implicit val conf: JsonConfiguration = ???
implicit val format: Format[SomeType] = Json.format[SomeType]
```
Can be converted to:
```scala
implicit val conf: JsonConfiguration = ???
implicit val format: JFormat[SomeType] = AnyJson.jFormat[SomeType]
```
