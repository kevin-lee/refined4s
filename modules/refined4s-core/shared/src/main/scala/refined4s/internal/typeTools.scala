package refined4s.internal

/** @author Kevin Lee
  * @since 2023-12-06
  */
object typeTools {

  import scala.compiletime.{erasedValue, summonInline}
  import scala.quoted.*

  inline def getTypeName[T]: String = ${ getNestedTypeNameImpl[T] }

  def getNestedTypeNameImpl[T: Type](using Quotes): Expr[String] = {
    import quotes.reflect.*
    Expr(TypeRepr.of[T].show)
  }
}
