package aminmal.anyjson.impl

import aminmal.anyjson.implcore.MacroTemplate

import scala.reflect.macros.blackbox

class Macros(override val c: blackbox.Context) extends MacroTemplate(c) {

  import c.universe._

  override def makeReaderImpl[T: c.WeakTypeTag]: c.Tree = {
    val typeT = c.weakTypeOf[T]
    val fields = typeT.decls.filter(_.isPrivateThis)
    val fieldNamesVararg = fields.map(_.asTerm.name.toString.trim).map(a => Literal(Constant(a)))
    val constructor = typeT.companion.decl(TermName("apply")).asMethod
    val typeTName = TypeName(typeT.typeSymbol.name.toString)
    val companionTName = TermName(typeT.typeSymbol.name.toString)
    val fieldTypes = fields.map(_.asTerm.info.typeSymbol.name).map(s => TypeName(s.toString))
    q"""
       import spray.json.DefaultJsonProtocol._,  spray.json._
       jsonFormat[..$fieldTypes, $typeTName]($companionTName.$constructor, ..$fieldNamesVararg).asInstanceOf[JsonReader[$typeT]]
     """
  }

  override def makeWriterImpl[T: c.WeakTypeTag]: c.Tree = {
    val typeT = c.weakTypeOf[T]
    val fields = typeT.decls.filter(_.isPrivateThis)
    val fieldNamesVararg = fields.map(_.asTerm.name.toString.trim).map(a => Literal(Constant(a)))
    val constructor = typeT.companion.decl(TermName("apply")).asMethod
    val typeTName = TypeName(typeT.typeSymbol.name.toString)
    val companionTName = TermName(typeT.typeSymbol.name.toString)
    val fieldTypes = fields.map(_.asTerm.info.typeSymbol.name).map(s => TypeName(s.toString))
    q"""
       import spray.json.DefaultJsonProtocol._,  spray.json._
       jsonFormat[..$fieldTypes, $typeTName]($companionTName.$constructor, ..$fieldNamesVararg).asInstanceOf[JsonWriter[$typeT]]
     """
  }

  override def makeFormatterImpl[T: c.WeakTypeTag]: c.Tree = {
    val typeT = c.weakTypeOf[T]
    val fields = typeT.decls.filter(_.isPrivateThis)
    val fieldNamesVararg = fields.map(_.asTerm.name.toString.trim).map(a => Literal(Constant(a)))
    val constructor = typeT.companion.decl(TermName("apply")).asMethod
    val typeTName = TypeName(typeT.typeSymbol.name.toString)
    val companionTName = TermName(typeT.typeSymbol.name.toString)
    val fieldTypes = fields.map(_.asTerm.info.typeSymbol.name).map(s => TypeName(s.toString))
    q"""
       import spray.json.DefaultJsonProtocol._,  spray.json._
       jsonFormat[..$fieldTypes, $typeTName]($companionTName.$constructor, ..$fieldNamesVararg)
     """
  }
}
