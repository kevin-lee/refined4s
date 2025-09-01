package refined4s.modules.chimney.derivation.types

import hedgehog.*
import hedgehog.runner.*
import io.scalaland.chimney
import io.scalaland.chimney.dsl.*
import refined4s.types.network.*
import refined4s.types.networkGens

/** @author Kevin Lee
  * @since 2024-08-06
  */
trait networkSpec {

  protected val networkTypeClasses: refined4s.modules.chimney.derivation.types.network

  import networkTypeClasses.given

  def allTests: List[Test] = List(
    // Uri
    property("test from Uri to String", testFromUri),
    property("test from String to Uri", testToUri),
    // Url
    property("test from Url to String", testFromUrl),
    property("test from String to Url", testToUrl),
    // PortNumber
    property("test from PortNumber to Int", testFromPortNumber),
    property("test from Int to PortNumber", testToPortNumber),
    // SystemPortNumber
    property("test from SystemPortNumber to Int", testFromSystemPortNumber),
    property("test from Int to SystemPortNumber", testToSystemPortNumber),
    // NonSystemPortNumber
    property("test from NonSystemPortNumber to Int", testFromNonSystemPortNumber),
    property("test from Int to NonSystemPortNumber", testToNonSystemPortNumber),
    // UserPortNumber
    property("test from UserPortNumber to Int", testFromUserPortNumber),
    property("test from Int to UserPortNumber", testToUserPortNumber),
    // DynamicPortNumber
    property("test from DynamicPortNumber to Int", testFromDynamicPortNumber),
    property("test from Int to DynamicPortNumber", testToDynamicPortNumber),
  )

  def testFromUri: Property =
    for {
      uriString <- networkGens.genUriString.log("uriString")
      uri = Uri.unsafeFrom(uriString)
    } yield {
      val input = uri

      val expected = uriString
      val actual   = input.into[String].transform

      actual ==== expected
    }

  def testToUri: Property =
    for {
      uriString <- networkGens.genUriString.log("uriString")
      uri = Uri.unsafeFrom(uriString)
    } yield {
      val input = uriString

      val expected = chimney.partial.Result.fromValue(uri)

      val actual = input.intoPartial[Uri].transform

      actual ==== expected
    }

  def testFromUrl: Property =
    for {
      urlString <- networkGens.genUrlString.log("urlString")
      url = Url.unsafeFrom(urlString)
    } yield {
      val input = url

      val expected = urlString
      val actual   = input.into[String].transform

      actual ==== expected
    }

  def testToUrl: Property =
    for {
      urlString <- networkGens.genUrlString.log("urlString")
      url = Url.unsafeFrom(urlString)
    } yield {
      val input = urlString

      val expected = chimney.partial.Result.fromValue(url)

      val actual = input.intoPartial[Url].transform

      actual ==== expected
    }

  def testFromPortNumber: Property =
    for {
      portNumberInt <- networkGens.genPortNumberInt.log("portNumberInt")
      portNumber = PortNumber.unsafeFrom(portNumberInt)
    } yield {
      val input = portNumber

      val expected = portNumberInt
      val actual   = input.into[Int].transform

      actual ==== expected
    }

  def testToPortNumber: Property =
    for {
      portNumberInt <- networkGens.genPortNumberInt.log("portNumberInt")
      portNumber = PortNumber.unsafeFrom(portNumberInt)
    } yield {
      val input = portNumberInt

      val expected = chimney.partial.Result.fromValue(portNumber)

      val actual = input.intoPartial[PortNumber].transform

      actual ==== expected
    }

  def testFromSystemPortNumber: Property =
    for {
      portNumberInt <- networkGens.genSystemPortNumberInt.log("portNumberInt")
      portNumber = SystemPortNumber.unsafeFrom(portNumberInt)
    } yield {
      val input = portNumber

      val expected = portNumberInt
      val actual   = input.into[Int].transform

      actual ==== expected
    }

  def testToSystemPortNumber: Property =
    for {
      portNumberInt <- networkGens.genSystemPortNumberInt.log("portNumberInt")
      portNumber = SystemPortNumber.unsafeFrom(portNumberInt)
    } yield {
      val input = portNumberInt

      val expected = chimney.partial.Result.fromValue(portNumber)

      val actual = input.intoPartial[SystemPortNumber].transform

      actual ==== expected
    }

  def testFromNonSystemPortNumber: Property =
    for {
      portNumberInt <- networkGens.genNonSystemPortNumberInt.log("portNumberInt")
      portNumber = NonSystemPortNumber.unsafeFrom(portNumberInt)
    } yield {
      val input = portNumber

      val expected = portNumberInt
      val actual   = input.into[Int].transform

      actual ==== expected
    }

  def testToNonSystemPortNumber: Property =
    for {
      portNumberInt <- networkGens.genNonSystemPortNumberInt.log("portNumberInt")
      portNumber = NonSystemPortNumber.unsafeFrom(portNumberInt)
    } yield {
      val input = portNumberInt

      val expected = chimney.partial.Result.fromValue(portNumber)

      val actual = input.intoPartial[NonSystemPortNumber].transform

      actual ==== expected
    }

  def testFromUserPortNumber: Property =
    for {
      portNumberInt <- networkGens.genUserPortNumberInt.log("portNumberInt")
      portNumber = UserPortNumber.unsafeFrom(portNumberInt)
    } yield {
      val input = portNumber

      val expected = portNumberInt
      val actual   = input.into[Int].transform

      actual ==== expected
    }

  def testToUserPortNumber: Property =
    for {
      portNumberInt <- networkGens.genUserPortNumberInt.log("portNumberInt")
      portNumber = UserPortNumber.unsafeFrom(portNumberInt)
    } yield {
      val input = portNumberInt

      val expected = chimney.partial.Result.fromValue(portNumber)

      val actual = input.intoPartial[UserPortNumber].transform

      actual ==== expected
    }

  def testFromDynamicPortNumber: Property =
    for {
      portNumberInt <- networkGens.genDynamicPortNumberInt.log("portNumberInt")
      portNumber = DynamicPortNumber.unsafeFrom(portNumberInt)
    } yield {
      val input = portNumber

      val expected = portNumberInt
      val actual   = input.into[Int].transform

      actual ==== expected
    }

  def testToDynamicPortNumber: Property =
    for {
      portNumberInt <- networkGens.genDynamicPortNumberInt.log("portNumberInt")
      portNumber = DynamicPortNumber.unsafeFrom(portNumberInt)
    } yield {
      val input = portNumberInt

      val expected = chimney.partial.Result.fromValue(portNumber)

      val actual = input.intoPartial[DynamicPortNumber].transform

      actual ==== expected
    }

}
object networkSpec extends Properties, networkSpec {

  override protected object networkTypeClasses extends refined4s.modules.chimney.derivation.types.network

  override def tests: List[Prop] = allTests

}
