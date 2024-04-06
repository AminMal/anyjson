package aminmal.cmp

import scala.reflect.macros.blackbox

class JsMacros(val c: blackbox.Context) {
  import c.universe._
  def makeReaderImpl[T: c.WeakTypeTag]: c.Tree = {
    val typeT = c.weakTypeOf[T]
    q"""
       aminmal.core.Reader.fromLib(_root_.play.api.libs.json.Json.reads[$typeT])
       """
  }

  def makeWriterImpl[T: c.WeakTypeTag]: c.Tree = {
    val typeT = c.weakTypeOf[T]
    q"""
       aminmal.core.Writer.fromLib(_root_.play.api.libs.json.Json.writes[$typeT])
       """
  }

  def makeFormatterImpl[T: c.WeakTypeTag]: c.Tree = {
    val typeT = c.weakTypeOf[T]
    q"""
       aminmal.core.Formatter.fromLib(_root_.play.api.libs.json.Json.format[$typeT])
       """
  }

}
