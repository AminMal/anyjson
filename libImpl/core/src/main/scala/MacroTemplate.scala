package aminmal.anyjson.implcore

import scala.reflect.macros.blackbox

abstract class MacroTemplate(val c: blackbox.Context) {

  def makeJReaderImpl[T : c.WeakTypeTag]: c.Tree

  def makeJWriterImpl[T : c.WeakTypeTag]: c.Tree

  def makeJFormatterImpl[T : c.WeakTypeTag]: c.Tree

}
