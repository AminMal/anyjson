package aminmal.anyjson.implcore

import scala.reflect.macros.blackbox

abstract class MacroTemplate(val c: blackbox.Context) {

  def makeReaderImpl[T : c.WeakTypeTag]: c.Tree

  def makeWriterImpl[T : c.WeakTypeTag]: c.Tree

  def makeFormatterImpl[T : c.WeakTypeTag]: c.Tree

}
