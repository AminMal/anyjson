package aminmal.anyjson.impl

import aminmal.anyjson.implcore.MacroTemplate

import scala.reflect.macros.blackbox

class Macros(override val c: blackbox.Context) extends MacroTemplate(c) {

  import c.universe._

  override def makeJReaderImpl[T: c.WeakTypeTag]: c.Tree = {
    val typeT = c.weakTypeOf[T]
    q"""
       import zio.json.DeriveJsonDecoder
       import aminmal.anyjson.impl.JReader
       JReader.fromLib(DeriveJsonDecoder.gen[$typeT])
     """
  }

  override def makeJWriterImpl[T: c.WeakTypeTag]: c.Tree = {
    val typeT = c.weakTypeOf[T]
    q"""
       import zio.json.DeriveJsonEncoder
       import aminmal.anyjson.impl.JWriter
       JWriter.fromLib(DeriveJsonEncoder.gen[$typeT])
     """
  }

  override def makeJFormatterImpl[T: c.WeakTypeTag]: c.Tree = {
    val typeT = c.weakTypeOf[T]
    q"""
       import zio.json.DeriveJsonCodec
       import aminmal.anyjson.impl.JFormatter
       JFormatter.fromLib(DeriveJsonCodec.gen[$typeT])
     """
  }
}
