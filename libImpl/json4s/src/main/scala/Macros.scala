package aminmal.anyjson.impl

import scala.reflect.macros.blackbox

import aminmal.anyjson.implcore.MacroTemplate

class Macros(override val c: blackbox.Context) extends MacroTemplate(c) {

  import c.universe._

  override def makeJReaderImpl[T : c.WeakTypeTag]: c.Tree = {
    val typeT = c.weakTypeOf[T]
    val fields = typeT.decls.filter(_.isPrivateThis)
    val fieldNamesVararg = fields.map(_.asTerm.name.toString.trim).map(a => Literal(Constant(a)))
    val constructor = typeT.companion.decl(TermName("apply")).asMethod
    val typeTName = TypeName(typeT.typeSymbol.name.toString)
    val companionTName = TermName(typeT.typeSymbol.name.toString)
    val fieldTypes = fields.map(_.asTerm.info.typeSymbol.name).map(s => TypeName(s.toString))
    q"""
       import org.json4s.DefaultReaders._
       import aminmal.anyjson.impl.JReader
       JReader.fromLib(org.json4s.Reader.reader[..$fieldTypes, $typeTName]($companionTName.$constructor)(..$fieldNamesVararg))
     """
  }

  override def makeJWriterImpl[T : c.WeakTypeTag]: c.Tree = {
    val typeT = c.weakTypeOf[T]
    val fields = typeT.decls.filter(_.isPrivateThis)
    val fieldNames = fields.map(_.asTerm.name.toString.trim).map(TermName.apply)
    val fieldSelections = fieldNames.map(t => q"x.$t")
    val fieldNamesVararg = fields.map(_.asTerm.name.toString.trim).map(a => Literal(Constant(a)))
    val typeTName = TypeName(typeT.typeSymbol.name.toString)
    val fieldTypes = fields.map(_.asTerm.info.typeSymbol.name).map(s => TypeName(s.toString))
    q"""
      import org.json4s.DefaultWriters._
      import aminmal.anyjson.impl.JWriter
      JWriter.fromLib(org.json4s.Writer.writer[..$fieldTypes, $typeTName](x => (..$fieldSelections))(..$fieldNamesVararg))
     """
  }

  override def makeJFormatterImpl[T : c.WeakTypeTag]: c.Tree = {
    val typeT = c.weakTypeOf[T]
    val fields = typeT.decls.filter(_.isPrivateThis)
    val fieldNames = fields.map(_.asTerm.name.toString.trim).map(TermName.apply)
    val fieldSelections = fieldNames.map(t => q"x.$t")
    val fieldNamesVararg = fields.map(_.asTerm.name.toString.trim).map(a => Literal(Constant(a)))
    val constructor = typeT.companion.decl(TermName("apply")).asMethod
    val typeTName = TypeName(typeT.typeSymbol.name.toString)
    val companionTName = TermName(typeT.typeSymbol.name.toString)
    val fieldTypes = fields.map(_.asTerm.info.typeSymbol.name).map(s => TypeName(s.toString))
    q"""
       import org.json4s.DefaultJsonFormats._
       import aminmal.anyjson.impl.JFormatter
       JFormatter.fromLib(org.json4s.JsonFormat.format[..$fieldTypes, $typeTName]($companionTName.$constructor, x => (..$fieldSelections))(..$fieldNamesVararg))
     """
  }

}