package aminmal.anyjson.impl

import aminmal.anyjson.implcore.MacroTemplate

import scala.reflect.macros.blackbox

class Macros(override val c: blackbox.Context) extends MacroTemplate(c) {

  import c.universe._

  override def makeReaderImpl[T: c.WeakTypeTag]: c.Tree = {
    val typeT = c.weakTypeOf[T]
    q"""
       import zio.json.DeriveJsonDecoder
       DeriveJsonDecoder.gen[$typeT]
     """
  }

  override def makeWriterImpl[T: c.WeakTypeTag]: c.Tree = {
    val typeT = c.weakTypeOf[T]
    q"""
       import zio.json.DeriveJsonEncoder
       DeriveJsonEncoder.gen[$typeT]
     """
  }

  override def makeFormatterImpl[T: c.WeakTypeTag]: c.Tree = {
    val typeT = c.weakTypeOf[T]
    q"""
       import zio.json.DeriveJsonCodec
       DeriveJsonCodec.gen[$typeT]
     """
  }
}
