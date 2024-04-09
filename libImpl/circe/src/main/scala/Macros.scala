package aminmal.anyjson.impl

import scala.reflect.macros.blackbox

import aminmal.anyjson.implcore.MacroTemplate

class Macros(override val c: blackbox.Context) extends MacroTemplate(c) {
  import c.universe._

  override def makeJReaderImpl[T : c.WeakTypeTag]: c.Tree = {
    val typeT = c.weakTypeOf[T]
    q"""
       import io.circe._, io.circe.generic.semiauto._
       import aminmal.anyjson.impl.JReader
       JReader.fromLib(deriveDecoder[$typeT])
     """
  }

  override def makeJWriterImpl[T : c.WeakTypeTag]: c.Tree = {
    val typeT = c.weakTypeOf[T]
    q"""
       import io.circe._, io.circe.generic.semiauto._
       import aminmal.anyjson.impl.JWriter
       JWriter.fromLib(deriveEncoder[$typeT])
     """
  }

  override def makeJFormatterImpl[T : c.WeakTypeTag]: c.Tree = {
    val typeT = c.weakTypeOf[T]
    q"""
       import io.circe._, io.circe.generic.semiauto._
       import aminmal.anyjson.impl.JFormatter
       JFormatter.fromLib(deriveCodec[$typeT])
     """
  }
}
