package refined4s.modules.extras.derivation.types

import extras.render.syntax.*
import hedgehog.*
import hedgehog.runner.*
import refined4s.types.network.*
import refined4s.types.networkGens

trait networkSpec {

  protected val networkTypeClasses: refined4s.modules.extras.derivation.types.network
  import networkTypeClasses.given

  def allTests: List[Test] = List(
    //
    property("test Render[Uri]", testRenderUri),
    //
    property("test Render[Url]", testRenderUrl),

    //
    property("test Render[PortNumber]", testRenderPortNumber),
    //
    property("test Render[SystemPortNumber]", testRenderSystemPortNumber),
    //
    property("test Render[NonSystemPortNumber]", testRenderNonSystemPortNumber),
    //
    property("test Render[UserPortNumber]", testRenderUserPortNumber),
    //
    property("test Render[DynamicPortNumber]", testRenderDynamicPortNumber),
  )

  def testRenderUri: Property =
    for {
      uri <- networkGens.genUriString.log("uri")
    } yield {
      val input = Uri.unsafeFrom(uri)

      val expected = uri
      val actual   = input.render

      actual ==== expected
    }

  def testRenderUrl: Property =
    for {
      url <- networkGens.genUrlString.log("url")
    } yield {
      val input = Url.unsafeFrom(url)

      val expected = url
      val actual   = input.render

      actual ==== expected
    }

  /* network ports */

  def testRenderPortNumber: Property =
    for {
      portNumber <- networkGens.genPortNumberInt.log("portNumber")
    } yield {

      val input = PortNumber.unsafeFrom(portNumber)

      val expected = portNumber.toString
      val actual   = input.render

      actual ==== expected
    }

  def testRenderSystemPortNumber: Property =
    for {
      systemPortNumber <- networkGens.genSystemPortNumberInt.log("systemPortNumber")
    } yield {

      val input = SystemPortNumber.unsafeFrom(systemPortNumber)

      val expected = systemPortNumber.toString
      val actual   = input.render

      actual ==== expected
    }

  def testRenderNonSystemPortNumber: Property =
    for {
      nonSystemPortNumber <- networkGens.genNonSystemPortNumberInt.log("nonSystemPortNumber")
    } yield {

      val input = NonSystemPortNumber.unsafeFrom(nonSystemPortNumber)

      val expected = nonSystemPortNumber.toString
      val actual   = input.render

      actual ==== expected
    }

  def testRenderUserPortNumber: Property =
    for {
      userPortNumber <- networkGens.genUserPortNumberInt.log("userPortNumber")
    } yield {

      val input = UserPortNumber.unsafeFrom(userPortNumber)

      val expected = userPortNumber.toString
      val actual   = input.render

      actual ==== expected
    }

  def testRenderDynamicPortNumber: Property =
    for {
      dynamicPortNumber <- networkGens.genDynamicPortNumberInt.log("dynamicPortNumber")
    } yield {

      val input = DynamicPortNumber.unsafeFrom(dynamicPortNumber)

      val expected = dynamicPortNumber.toString
      val actual   = input.render

      actual ==== expected
    }

}
object networkSpec extends Properties, networkSpec {

  override protected object networkTypeClasses extends refined4s.modules.extras.derivation.types.network

  override def tests: List[Test] = allTests

}
