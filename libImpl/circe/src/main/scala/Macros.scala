package aminmal.anyjson.impl

import scala.reflect.macros.blackbox

class Macros(val c: blackbox.Context) {
  import c.universe._

  def makeJReaderImpl[T : c.WeakTypeTag]: c.Tree = {
    val typeT = c.weakTypeOf[T]
    q"""
       import io.circe._, io.circe.generic.semiauto._
       import aminmal.anyjson.impl.JReader
       JReader.fromLib(deriveDecoder[$typeT])
     """
  }

  def makeJWriterImpl[T : c.WeakTypeTag]: c.Tree = {
    val typeT = c.weakTypeOf[T]
    q"""
       import io.circe._, io.circe.generic.semiauto._
       import aminmal.anyjson.impl.JWriter
       JWriter.fromLib(deriveEncoder[$typeT])
     """
  }

  def makeJFormatterImpl[T : c.WeakTypeTag]: c.Tree = {
    val typeT = c.weakTypeOf[T]
    q"""
       import io.circe._, io.circe.generic.semiauto._
       import aminmal.anyjson.impl.JFormatter
       JFormatter.fromLib(deriveCodec[$typeT])
     """
  }
}
