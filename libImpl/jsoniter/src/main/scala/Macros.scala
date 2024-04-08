package aminmal.anyjson.impl

import scala.reflect.macros.blackbox

class Macros(val c: blackbox.Context) {

  import c.universe._

  def makeJReaderImpl[T : c.WeakTypeTag]: c.Tree = {
    val typeT = c.weakTypeOf[T]
    q"""
       import com.github.plokhotnyuk.jsoniter_scala.macros._
       import aminmal.anyjson.impl.JReader
       JReader.fromLib(JsonCodecMaker.make[$typeT])
     """
  }

  def makeJWriterImpl[T : c.WeakTypeTag]: c.Tree = {
    val typeT = c.weakTypeOf[T]
    q"""
       import com.github.plokhotnyuk.jsoniter_scala.macros._
       import aminmal.anyjson.impl.JWriter
       JWriter.fromLib(JsonCodecMaker.make[$typeT])
     """
  }

  def makeJFormatterImpl[T : c.WeakTypeTag]: c.Tree = {
    val typeT = c.weakTypeOf[T]
    q"""
       import com.github.plokhotnyuk.jsoniter_scala.macros._
       import aminmal.anyjson.impl.JFormatter
       JFormatter.fromLib(JsonCodecMaker.make[$typeT])
     """
  }
}
