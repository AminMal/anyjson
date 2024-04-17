package aminmal.anyjson.impl

import scala.reflect.macros.blackbox

import aminmal.anyjson.implcore.MacroTemplate

class Macros(override val c: blackbox.Context) extends MacroTemplate(c) {

  import c.universe._
  override def makeFormatterImpl[T : c.WeakTypeTag]: c.Tree = {
    val typeT = c.weakTypeOf[T]
    q"""
       _root_.play.api.libs.json.Json.format[$typeT]
     """
  }

  override def makeReaderImpl[T : c.WeakTypeTag]: c.Tree = {
    val typeT = c.weakTypeOf[T]
    q"""
       _root_.play.api.libs.json.Json.reads[$typeT]
     """
  }

  override def makeWriterImpl[T : c.WeakTypeTag]: c.Tree = {
    val typeT = c.weakTypeOf[T]
    q"""
       _root_.play.api.libs.json.Json.writes[$typeT]
     """
  }

}
