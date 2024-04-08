package aminmal.anyjson.impl.circeImpl

import scala.reflect.macros.blackbox

class Macros(val c: blackbox.Context) {
  import c.universe._

  def makeJReaderImpl[T : c.WeakTypeTag]: c.Tree = {
    val typeT = c.weakTypeOf[T]
    q"""
       import io.circe._, io.circe.generic.semiauto._
       import aminmal.anyjson.impl.circeImpl.JReader
       JReader.fromLib(deriveDecoder[$typeT])
     """
  }

  def makeJWriterImpl[T : c.WeakTypeTag]: c.Tree = {
    val typeT = c.weakTypeOf[T]
    q"""
       import io.circe._, io.circe.generic.semiauto._
       import aminmal.anyjson.impl.circeImpl.JWriter
       JWriter.fromLib(deriveEncoder[$typeT])
     """
  }

  def makeJFormatterImpl[T : c.WeakTypeTag]: c.Tree = {
    val typeT = c.weakTypeOf[T]
    q"""
       import io.circe._, io.circe.generic.semiauto._
       import aminmal.anyjson.impl.circeImpl.JFormatter
       JFormatter.fromLib(deriveCodec[$typeT])
     """
  }
}
