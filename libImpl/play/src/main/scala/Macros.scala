package aminmal.impl.playImpl

import scala.reflect.macros.blackbox

class Macros(val c: blackbox.Context) {

  import c.universe._

  def makeJReaderImpl[T : c.WeakTypeTag]: c.Tree = {
    val typeT = c.weakTypeOf[T]
    q"""
       import aminmal.impl.playImpl.JReader
       JReader.fromLib(_root_.play.api.libs.json.Json.reads[$typeT])
     """
  }

  def makeJWriterImpl[T : c.WeakTypeTag]: c.Tree = {
    val typeT = c.weakTypeOf[T]
    q"""
       import aminmal.impl.playImpl.JWriter
       JWriter.fromLib(_root_.play.api.libs.json.Json.writes[$typeT])
     """
  }

  def makeJFormatterImpl[T : c.WeakTypeTag]: c.Tree = {
    val typeT = c.weakTypeOf[T]
    q"""
       import aminmal.impl.playImpl.JFormatter
       JFormatter.fromLib(_root_.play.api.libs.json.Json.format[$typeT])
     """
  }

}
