package aminmal.anyjson.impl

import scala.reflect.macros.blackbox

import aminmal.anyjson.implcore.MacroTemplate

class Macros(override val c: blackbox.Context) extends MacroTemplate(c) {
  import c.universe._

  override def makeReaderImpl[T : c.WeakTypeTag]: c.Tree = {
    val typeT = c.weakTypeOf[T]
    q"""
       import io.circe._, io.circe.generic.semiauto._
       deriveDecoder[$typeT]
     """
  }

  override def makeWriterImpl[T : c.WeakTypeTag]: c.Tree = {
    val typeT = c.weakTypeOf[T]
    q"""
       import io.circe._, io.circe.generic.semiauto._
       deriveEncoder[$typeT]
     """
  }

  override def makeFormatterImpl[T : c.WeakTypeTag]: c.Tree = {
    val typeT = c.weakTypeOf[T]
    q"""
       import io.circe._, io.circe.generic.semiauto._
       deriveCodec[$typeT]
     """
  }
}
