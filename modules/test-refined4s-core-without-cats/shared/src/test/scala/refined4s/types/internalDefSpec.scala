package refined4s.types

import hedgehog.*
import hedgehog.runner.*
import orphan.OrphanCatsMessages

/** @author Kevin Lee
  * @since 2025-08-24
  */
object internalDefSpec extends Properties {

  override def tests: List[Test] = List(
    example("test internalDef.contraCoercible", testContraCoercible)
  )

  def testContraCoercible: Result = {
    import scala.compiletime.testing.typeCheckErrors

    val expected = OrphanCatsMessages.MissingCatsContravariant

    val actual = typeCheckErrors(
      "val _ = internalDef.contraCoercible"
    ).map(_.message).mkString

    actual ==== expected
  }
}
