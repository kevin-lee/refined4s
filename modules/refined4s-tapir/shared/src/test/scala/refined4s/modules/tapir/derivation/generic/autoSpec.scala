package refined4s.modules.tapir.derivation.generic

import hedgehog.*
import hedgehog.runner.*
import refined4s.*
import refined4s.modules.tapir.derivation.generic.auto.given
import refined4s.types.all.*
import refined4s.types.{networkGens, UuidV7TestTools}
import sttp.tapir.{Schema, ValidationError}

import java.nio.charset.StandardCharsets
import java.util.UUID

/** @author Kevin Lee
  * @since 2024-04-04
  */
object autoSpec extends Properties {

  override def tests: List[Test] = List(
    property("test Schema[NegInt]", testSchemaNegInt),
    //
    property("test Schema[NonNegInt]", testSchemaNonNegInt),
    //
    property("test Schema[PosInt]", testSchemaPosInt),
    //
    property("test Schema[NonPosInt]", testSchemaNonPosInt),
    //
    property("test Schema[NegLong]", testSchemaNegLong),
    //
    property("test Schema[NonNegLong]", testSchemaNonNegLong),
    //
    property("test Schema[PosLong]", testSchemaPosLong),
    //
    property("test Schema[NonPosLong]", testSchemaNonPosLong),
    //
    property("test Schema[NegShort]", testSchemaNegShort),
    //
    property("test Schema[NonNegShort]", testSchemaNonNegShort),
    //
    property("test Schema[PosShort]", testSchemaPosShort),
    //
    property("test Schema[NonPosShort]", testSchemaNonPosShort),
    //
    property("test Schema[NegByte]", testSchemaNegByte),
    //
    property("test Schema[NonNegByte]", testSchemaNonNegByte),
    //
    property("test Schema[PosByte]", testSchemaPosByte),
    //
    property("test Schema[NonPosByte]", testSchemaNonPosByte),
    //
    property("test Schema[NegFloat]", testSchemaNegFloat),
    //
    property("test Schema[NonNegFloat]", testSchemaNonNegFloat),
    //
    property("test Schema[PosFloat]", testSchemaPosFloat),
    //
    property("test Schema[NonPosFloat]", testSchemaNonPosFloat),
    //
    property("test Schema[NegDouble]", testSchemaNegDouble),
    //
    property("test Schema[NonNegDouble]", testSchemaNonNegDouble),
    //
    property("test Schema[PosDouble]", testSchemaPosDouble),
    //
    property("test Schema[NonPosDouble]", testSchemaNonPosDouble),
    //
    property("test Schema[NegBigInt]", testSchemaNegBigInt),
    //
    property("test Schema[NonNegBigInt]", testSchemaNonNegBigInt),
    //
    property("test Schema[PosBigInt]", testSchemaPosBigInt),
    //
    property("test Schema[NonPosBigInt]", testSchemaNonPosBigInt),
    //
    property("test Schema[NegBigDecimal]", testSchemaNegBigDecimal),
    //
    property("test Schema[NonNegBigDecimal]", testSchemaNonNegBigDecimal),
    //
    property("test Schema[PosBigDecimal]", testSchemaPosBigDecimal),
    //
    property("test Schema[NonPosBigDecimal]", testSchemaNonPosBigDecimal),
    //
    property("test Schema[NonEmptyString]", testSchemaNonEmptyString),
    //
    property("test Schema[NonBlankString]", testSchemaNonBlankString),
    //
    property("test Schema[Uuid]", testSchemaUuid),
    //
    property("test Schema[UuidV7]", testSchemaUuidV7),
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
    //
    property("test Schema[MyNum] (Newtype)", testSchemaMyNum),
    //
    property("test Schema[MyStr] (Refined)", testSchemaMyStr),
  )

  def testSchemaNegInt: Property =
    for {
      n <- Gen.int(Range.linear(-1, Int.MinValue)).log("n")
    } yield {
      val input = NegInt.unsafeFrom(n)

      val expected = List.empty[ValidationError[?]]
      val actual   = summon[Schema[NegInt]].applyValidation(input)

      actual ==== expected
    }

  def testSchemaNonNegInt: Property =
    for {
      n <- Gen.int(Range.linear(0, Int.MaxValue)).log("n")
    } yield {
      val input = NonNegInt.unsafeFrom(n)

      val expected = List.empty[ValidationError[?]]
      val actual   = summon[Schema[NonNegInt]].applyValidation(input)

      actual ==== expected
    }

  def testSchemaPosInt: Property =
    for {
      n <- Gen.int(Range.linear(1, Int.MaxValue)).log("n")
    } yield {
      val input =
        PosInt.unsafeFrom(n)

      val expected = List.empty[ValidationError[?]]
      val actual   = summon[Schema[PosInt]].applyValidation(input)

      actual ==== expected
    }

  def testSchemaNonPosInt: Property =
    for {
      n <- Gen.int(Range.linear(0, Int.MinValue)).log("n")
    } yield {
      val input =
        NonPosInt.unsafeFrom(n)

      val expected = List.empty[ValidationError[?]]
      val actual   = summon[Schema[NonPosInt]].applyValidation(input)

      actual ==== expected
    }

  def testSchemaNegLong: Property =
    for {
      n <- Gen.long(Range.linear(-1L, Long.MinValue)).log("n")
    } yield {
      val input = NegLong.unsafeFrom(n)

      val expected = List.empty[ValidationError[?]]
      val actual   = summon[Schema[NegLong]].applyValidation(input)

      actual ==== expected
    }

  def testSchemaNonNegLong: Property =
    for {
      n <- Gen.long(Range.linear(0L, Long.MaxValue)).log("n")
    } yield {
      val input = NonNegLong.unsafeFrom(n)

      val expected = List.empty[ValidationError[?]]
      val actual   = summon[Schema[NonNegLong]].applyValidation(input)

      actual ==== expected
    }

  def testSchemaPosLong: Property =
    for {
      n <- Gen.long(Range.linear(1L, Long.MaxValue)).log("n")
    } yield {
      val input = PosLong.unsafeFrom(n)

      val expected = List.empty[ValidationError[?]]
      val actual   = summon[Schema[PosLong]].applyValidation(input)

      actual ==== expected
    }

  def testSchemaNonPosLong: Property =
    for {
      n <- Gen.long(Range.linear(0, Long.MinValue)).log("n")
    } yield {
      val input = NonPosLong.unsafeFrom(n)

      val expected = List.empty[ValidationError[?]]
      val actual   = summon[Schema[NonPosLong]].applyValidation(input)

      actual ==== expected
    }

  def testSchemaNegShort: Property =
    for {
      n <- Gen.short(Range.linear(-1, Short.MinValue)).log("n")
    } yield {
      val input = NegShort.unsafeFrom(n)

      val expected = List.empty[ValidationError[?]]
      val actual   = summon[Schema[NegShort]].applyValidation(input)

      actual ==== expected
    }

  def testSchemaNonNegShort: Property =
    for {
      n <- Gen.short(Range.linear(0, Short.MaxValue)).log("n")
    } yield {
      val input = NonNegShort.unsafeFrom(n)

      val expected = List.empty[ValidationError[?]]
      val actual   = summon[Schema[NonNegShort]].applyValidation(input)

      actual ==== expected
    }

  def testSchemaPosShort: Property =
    for {
      n <- Gen.short(Range.linear(1, Short.MaxValue)).log("n")
    } yield {
      val input = PosShort.unsafeFrom(n)

      val expected = List.empty[ValidationError[?]]
      val actual   = summon[Schema[PosShort]].applyValidation(input)

      actual ==== expected
    }

  def testSchemaNonPosShort: Property =
    for {
      n <- Gen.short(Range.linear(0, Short.MinValue)).log("n")
    } yield {
      val input = NonPosShort.unsafeFrom(n)

      val expected = List.empty[ValidationError[?]]
      val actual   = summon[Schema[NonPosShort]].applyValidation(input)

      actual ==== expected
    }

  def testSchemaNegByte: Property =
    for {
      n <- Gen.byte(Range.linear(-1, Byte.MinValue)).log("n")
    } yield {
      val input = NegByte.unsafeFrom(n)

      val expected = List.empty[ValidationError[?]]
      val actual   = summon[Schema[NegByte]].applyValidation(input)

      actual ==== expected
    }

  def testSchemaNonNegByte: Property =
    for {
      n <- Gen.byte(Range.linear(0, Byte.MaxValue)).log("n")
    } yield {
      val input = NonNegByte.unsafeFrom(n)

      val expected = List.empty[ValidationError[?]]
      val actual   = summon[Schema[NonNegByte]].applyValidation(input)

      actual ==== expected
    }

  def testSchemaPosByte: Property =
    for {
      n <- Gen.byte(Range.linear(1, Byte.MaxValue)).log("n")
    } yield {
      val input = PosByte.unsafeFrom(n)

      val expected = List.empty[ValidationError[?]]
      val actual   = summon[Schema[PosByte]].applyValidation(input)

      actual ==== expected
    }

  def testSchemaNonPosByte: Property =
    for {
      n <- Gen.byte(Range.linear(0, Byte.MinValue)).log("n")
    } yield {
      val input = NonPosByte.unsafeFrom(n)

      val expected = List.empty[ValidationError[?]]
      val actual   = summon[Schema[NonPosByte]].applyValidation(input)

      actual ==== expected
    }

  def testSchemaNegFloat: Property =
    for {
      n <- Gen.double(Range.linearFrac(-0.00001d, Float.MinValue)).map(_.toFloat).log("n")
    } yield {
      val input = NegFloat.unsafeFrom(n)

      val expected = List.empty[ValidationError[?]]
      val actual   = summon[Schema[NegFloat]].applyValidation(input)

      actual ==== expected
    }

  def testSchemaNonNegFloat: Property =
    for {
      n <- Gen.double(Range.linearFrac(0d, Float.MaxValue)).map(_.toFloat).log("n")
    } yield {
      val input = NonNegFloat.unsafeFrom(n)

      val expected = List.empty[ValidationError[?]]
      val actual   = summon[Schema[NonNegFloat]].applyValidation(input)

      actual ==== expected
    }

  def testSchemaPosFloat: Property =
    for {
      n <- Gen.double(Range.linearFrac(0.0001d, Float.MaxValue)).map(_.toFloat).log("n")
    } yield {
      val input = PosFloat.unsafeFrom(n)

      val expected = List.empty[ValidationError[?]]
      val actual   = summon[Schema[PosFloat]].applyValidation(input)

      actual ==== expected
    }

  def testSchemaNonPosFloat: Property =
    for {
      n <- Gen.double(Range.linearFrac(0d, Float.MinValue)).map(_.toFloat).log("n")
    } yield {
      val input = NonPosFloat.unsafeFrom(n)

      val expected = List.empty[ValidationError[?]]
      val actual   = summon[Schema[NonPosFloat]].applyValidation(input)

      actual ==== expected
    }

  def testSchemaNegDouble: Property =
    for {
      n <- Gen.double(Range.linearFrac(-0.000001d, Double.MinValue)).log("n")
    } yield {
      val input = NegDouble.unsafeFrom(n)

      val expected = List.empty[ValidationError[?]]
      val actual   = summon[Schema[NegDouble]].applyValidation(input)

      actual ==== expected
    }

  def testSchemaNonNegDouble: Property =
    for {
      n <- Gen.double(Range.linearFrac(0d, Double.MaxValue)).log("n")
    } yield {
      val input = NonNegDouble.unsafeFrom(n)

      val expected = List.empty[ValidationError[?]]
      val actual   = summon[Schema[NonNegDouble]].applyValidation(input)

      actual ==== expected
    }

  def testSchemaPosDouble: Property =
    for {
      n <- Gen.double(Range.linearFrac(0.000001d, Double.MaxValue)).log("n")
    } yield {
      val input = PosDouble.unsafeFrom(n)

      val expected = List.empty[ValidationError[?]]
      val actual   = summon[Schema[PosDouble]].applyValidation(input)

      actual ==== expected
    }

  def testSchemaNonPosDouble: Property =
    for {
      n <- Gen.double(Range.linearFrac(0d, Double.MinValue)).log("n")
    } yield {
      val input = NonPosDouble.unsafeFrom(n)

      val expected = List.empty[ValidationError[?]]
      val actual   = summon[Schema[NonPosDouble]].applyValidation(input)

      actual ==== expected
    }

  def testSchemaNegBigInt: Property =
    for {
      n <- Gen.long(Range.linear(-1L, Long.MinValue)).map(BigInt(_)).log("n")
    } yield {
      val input = NegBigInt.unsafeFrom(n)

      val expected = List.empty[ValidationError[?]]
      val actual   = summon[Schema[NegBigInt]].applyValidation(input)

      actual ==== expected
    }

  def testSchemaNonNegBigInt: Property =
    for {
      n <- Gen.long(Range.linear(0L, Long.MaxValue)).map(BigInt(_)).log("n")
    } yield {
      val input = NonNegBigInt.unsafeFrom(n)

      val expected = List.empty[ValidationError[?]]
      val actual   = summon[Schema[NonNegBigInt]].applyValidation(input)

      actual ==== expected
    }

  def testSchemaPosBigInt: Property =
    for {
      n <- Gen.long(Range.linear(1L, Long.MaxValue)).map(BigInt(_)).log("n")
    } yield {
      val input = PosBigInt.unsafeFrom(n)

      val expected = List.empty[ValidationError[?]]
      val actual   = summon[Schema[PosBigInt]].applyValidation(input)

      actual ==== expected
    }

  def testSchemaNonPosBigInt: Property =
    for {
      n <- Gen.long(Range.linear(0L, Long.MinValue)).map(BigInt(_)).log("n")
    } yield {
      val input = NonPosBigInt.unsafeFrom(n)

      val expected = List.empty[ValidationError[?]]
      val actual   = summon[Schema[NonPosBigInt]].applyValidation(input)

      actual ==== expected
    }

  def testSchemaNegBigDecimal: Property =
    for {
      n <- Gen.double(Range.linearFrac(-0.0000001d, Double.MinValue)).map(BigDecimal(_)).log("n")
    } yield {
      val input = NegBigDecimal.unsafeFrom(n)

      val expected = List.empty[ValidationError[?]]
      val actual   = summon[Schema[NegBigDecimal]].applyValidation(input)

      actual ==== expected
    }

  def testSchemaNonNegBigDecimal: Property =
    for {
      n <- Gen.double(Range.linearFrac(0d, Double.MaxValue)).map(BigDecimal(_)).log("n")
    } yield {
      val input = NonNegBigDecimal.unsafeFrom(n)

      val expected = List.empty[ValidationError[?]]
      val actual   = summon[Schema[NonNegBigDecimal]].applyValidation(input)

      actual ==== expected
    }

  def testSchemaPosBigDecimal: Property =
    for {
      n <- Gen.double(Range.linearFrac(0.0000001d, Double.MaxValue)).map(BigDecimal(_)).log("n")
    } yield {
      val input = PosBigDecimal.unsafeFrom(n)

      val expected = List.empty[ValidationError[?]]
      val actual   = summon[Schema[PosBigDecimal]].applyValidation(input)

      actual ==== expected
    }

  def testSchemaNonPosBigDecimal: Property =
    for {
      n <- Gen.double(Range.linearFrac(0d, Double.MinValue)).map(BigDecimal(_)).log("n")
    } yield {
      val input = NonPosBigDecimal.unsafeFrom(n)

      val expected = List.empty[ValidationError[?]]
      val actual   = summon[Schema[NonPosBigDecimal]].applyValidation(input)

      actual ==== expected
    }

  def testSchemaNonEmptyString: Property =
    for {
      s <- Gen.string(Gen.unicode, Range.linear(1, 10)).log("s")
    } yield {
      val input = NonEmptyString.unsafeFrom(s)

      val expected = List.empty[ValidationError[?]]
      val actual   = summon[Schema[NonEmptyString]].applyValidation(input)

      actual ==== expected
    }

  def testSchemaNonBlankString: Property =
    for {
      nonWhitespaceString <- Gen
                               .string(hedgehog.extra.Gens.genNonWhitespaceChar, Range.linear(1, 10))
                               .map(s => new String(s.getBytes(StandardCharsets.UTF_8), StandardCharsets.UTF_8))
                               .log("nonWhitespaceString")
      whitespaceString    <- Gen
                               .string(
                                 hedgehog.extra.Gens.genCharByRange(refined4s.types.strings.WhitespaceCharRange),
                                 Range.linear(1, 10),
                               )
                               .log("whitespaceString")

      s <- Gen.constant(scala.util.Random.shuffle((nonWhitespaceString + whitespaceString).toList).mkString).log("s")
    } yield {
      val input = NonBlankString.unsafeFrom(s)

      val expected = List.empty[ValidationError[?]]
      val actual   = summon[Schema[NonBlankString]].applyValidation(input)

      actual ==== expected
    }

  def testSchemaUuid: Property =
    for {
      uuid <- Gen.constant(UUID.randomUUID()).log("uuid")
    } yield {
      val input = Uuid(uuid)

      val expected = List.empty[ValidationError[?]]
      val actual   = summon[Schema[Uuid]].applyValidation(input)

      actual ==== expected
    }

  def testSchemaUuidV7: Property =
    for {
      uuid <- Gen.elementUnsafe(UuidV7TestTools.validUuidV7Strings).log("uuid")
    } yield {
      val input = UuidV7.unsafeFromString(uuid)

      val expected = List.empty[ValidationError[?]]
      val actual   = summon[Schema[UuidV7]].applyValidation(input)

      actual ==== expected
    }

  ///

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

  ///

  def testSchemaMyNum: Property =
    for {
      n <- Gen.int(Range.linear(Int.MinValue, Int.MaxValue)).log("n")
    } yield {

      val input = MyNum(n)

      val expected = List.empty[ValidationError[?]]
      val actual   = summon[Schema[MyNum]].applyValidation(input)

      actual ==== expected
    }

  ///

  def testSchemaMyStr: Property =
    for {
      s <- Gen.string(Gen.alphaNum, Range.linear(1, 10)).log("s")
    } yield {

      val input = MyStr.unsafeFrom(s)

      val expected = List.empty[ValidationError[?]]
      val actual   = summon[Schema[MyStr]].applyValidation(input)

      actual ==== expected
    }

  type MyNum = MyNum.Type
  object MyNum extends Newtype[Int]

  type MyStr = MyStr.Type
  @SuppressWarnings(Array("org.wartremover.warts.Equals"))
  object MyStr extends Refined[String] {
    override inline def invalidReason(a: String): String = "It must be a non-empty String value"

    override inline def predicate(a: String): Boolean = a != ""
  }

}
