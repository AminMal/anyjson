import aminmal.anyjson.api._

sealed trait Weather {}

object Weather {
  implicit val formatter: JFormatter[Weather] = AnyJson.jFormatter
}

case class Rainy(possibility: Int) extends Weather

object Rainy {
  implicit val formatter: JFormatter[Rainy] = AnyJson.jFormatter
}

case class Sunny(temperature: Int) extends Weather

object Sunny {
  implicit val formatter: JFormatter[Sunny] = AnyJson.jFormatter
}
