package refined4s.modules.tapir.derivation.types

import hedgehog.*
import hedgehog.runner.*
import refined4s.types.network.*
import refined4s.types.networkGens
import sttp.tapir.{Schema, ValidationError}

/** @author Kevin Lee
  * @since 2025-09-02
  */
trait networkSpec {

  protected val networkTypeClasses: refined4s.modules.tapir.derivation.types.network
  import networkTypeClasses.given

  def allTests: List[Test] = List(
    //
    property("test Schema[Uri]", testSchemaUri),
    //
    property("test Schema[Url]", testSchemaUrl),

    //
    property("test Schema[PortNumber]", testSchemaPortNumber),
    //
    property("test Schema[SystemPortNumber]", testSchemaSystemPortNumber),
    //
    property("test Schema[NonSystemPortNumber]", testSchemaNonSystemPortNumber),
    //
    property("test Schema[UserPortNumber]", testSchemaUserPortNumber),
    //
    property("test Schema[DynamicPortNumber]", testSchemaDynamicPortNumber),
  )

  def testSchemaUri: Property =
    for {
      uri <- networkGens.genUriString.log("uri")
    } yield {
      val input = Uri.unsafeFrom(uri)

      val expected = List.empty[ValidationError[?]]
      val actual   = summon[Schema[Uri]].applyValidation(input)

      actual ==== expected
    }

  def testSchemaUrl: Property =
    for {
      url <- networkGens.genUrlString.log("url")
    } yield {
      val input = Url.unsafeFrom(url)

      val expected = List.empty[ValidationError[?]]
      val actual   = summon[Schema[Url]].applyValidation(input)

      actual ==== expected
    }

  /* network ports */

  def testSchemaPortNumber: Property =
    for {
      portNumber <- networkGens.genPortNumberInt.log("portNumber")
    } yield {

      val input =
        PortNumber.unsafeFrom(portNumber)

      val expected = List.empty[ValidationError[?]]
      val actual   =
        summon[Schema[PortNumber]].applyValidation(input)

      actual ==== expected
    }

  def testSchemaSystemPortNumber: Property =
    for {
      systemPortNumber <- networkGens.genSystemPortNumberInt.log("systemPortNumber")
    } yield {

      val input = SystemPortNumber.unsafeFrom(systemPortNumber)

      val expected = List.empty[ValidationError[?]]
      val actual   = summon[Schema[SystemPortNumber]].applyValidation(input)

      actual ==== expected
    }

  def testSchemaNonSystemPortNumber: Property =
    for {
      nonSystemPortNumber <- networkGens.genNonSystemPortNumberInt.log("nonSystemPortNumber")
    } yield {

      val input = NonSystemPortNumber.unsafeFrom(nonSystemPortNumber)

      val expected = List.empty[ValidationError[?]]
      val actual   = summon[Schema[NonSystemPortNumber]].applyValidation(input)

      actual ==== expected
    }

  def testSchemaUserPortNumber: Property =
    for {
      userPortNumber <- networkGens.genUserPortNumberInt.log("userPortNumber")
    } yield {

      val input = UserPortNumber.unsafeFrom(userPortNumber)

      val expected = List.empty[ValidationError[?]]
      val actual   = summon[Schema[UserPortNumber]].applyValidation(input)

      actual ==== expected
    }

  def testSchemaDynamicPortNumber: Property =
    for {
      dynamicPortNumber <- networkGens.genDynamicPortNumberInt.log("dynamicPortNumber")
    } yield {

      val input = DynamicPortNumber.unsafeFrom(dynamicPortNumber)

      val expected = List.empty[ValidationError[?]]
      val actual   = summon[Schema[DynamicPortNumber]].applyValidation(input)

      actual ==== expected
    }

}
object networkSpec extends Properties, networkSpec {

  override protected object networkTypeClasses extends refined4s.modules.tapir.derivation.types.network

  override def tests: List[Test] = allTests

}
