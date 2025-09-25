package refined4s.modules.circe.derivation.types

import io.circe.{Decoder, Encoder, KeyDecoder, KeyEncoder}
import refined4s.types.time.*

/** @author Kevin Lee
  * @since 2025-08-30
  */
trait time {

  /* Month { */
  given derivedMonthEncoder: Encoder[Month] = time.derivedMonthEncoder
  given derivedMonthDecoder: Decoder[Month] = time.derivedMonthDecoder

  given derivedMonthKeyEncoder: KeyEncoder[Month] = time.derivedMonthKeyEncoder
  given derivedMonthKeyDecoder: KeyDecoder[Month] = time.derivedMonthKeyDecoder
  /* } Month */

  /* Day { */
  given derivedDayEncoder: Encoder[Day] = time.derivedDayEncoder
  given derivedDayDecoder: Decoder[Day] = time.derivedDayDecoder

  given derivedDayKeyEncoder: KeyEncoder[Day] = time.derivedDayKeyEncoder
  given derivedDayKeyDecoder: KeyDecoder[Day] = time.derivedDayKeyDecoder
  /* } Day */

  /* Hour { */
  given derivedHourEncoder: Encoder[Hour] = time.derivedHourEncoder
  given derivedHourDecoder: Decoder[Hour] = time.derivedHourDecoder

  given derivedHourKeyEncoder: KeyEncoder[Hour] = time.derivedHourKeyEncoder
  given derivedHourKeyDecoder: KeyDecoder[Hour] = time.derivedHourKeyDecoder
  /* } Hour */

  /* Minute { */
  given derivedMinuteEncoder: Encoder[Minute] = time.derivedMinuteEncoder
  given derivedMinuteDecoder: Decoder[Minute] = time.derivedMinuteDecoder

  given derivedMinuteKeyEncoder: KeyEncoder[Minute] = time.derivedMinuteKeyEncoder
  given derivedMinuteKeyDecoder: KeyDecoder[Minute] = time.derivedMinuteKeyDecoder
  /* } Minute */

  /* Second { */
  given derivedSecondEncoder: Encoder[Second] = time.derivedSecondEncoder
  given derivedSecondDecoder: Decoder[Second] = time.derivedSecondDecoder

  given derivedSecondKeyEncoder: KeyEncoder[Second] = time.derivedSecondKeyEncoder
  given derivedSecondKeyDecoder: KeyDecoder[Second] = time.derivedSecondKeyDecoder
  /* } Second */

  /* Millis { */
  given derivedMillisEncoder: Encoder[Millis] = time.derivedMillisEncoder
  given derivedMillisDecoder: Decoder[Millis] = time.derivedMillisDecoder

  given derivedMillisKeyEncoder: KeyEncoder[Millis] = time.derivedMillisKeyEncoder
  given derivedMillisKeyDecoder: KeyDecoder[Millis] = time.derivedMillisKeyDecoder
  /* } Millis */
}
object time {

  /* Month { */
  given derivedMonthEncoder: Encoder[Month] = Encoder[Int].contramap[Month](_.value)
  given derivedMonthDecoder: Decoder[Month] = Decoder[Int].emap(Month.from)

  given derivedMonthKeyEncoder: KeyEncoder[Month] = KeyEncoder[Int].contramap[Month](_.value)
  given derivedMonthKeyDecoder: KeyDecoder[Month] with {
    override def apply(key: String): Option[Month] = KeyDecoder.decodeKeyInt.apply(key).flatMap(Month.from(_).toOption)
  }
  /* } Month */

  /* Day { */
  given derivedDayEncoder: Encoder[Day] = Encoder[Int].contramap[Day](_.value)
  given derivedDayDecoder: Decoder[Day] = Decoder[Int].emap(Day.from)

  given derivedDayKeyEncoder: KeyEncoder[Day] = KeyEncoder[Int].contramap[Day](_.value)
  given derivedDayKeyDecoder: KeyDecoder[Day] with {
    override def apply(key: String): Option[Day] = KeyDecoder.decodeKeyInt.apply(key).flatMap(Day.from(_).toOption)
  }
  /* } Day */

  /* Hour { */
  given derivedHourEncoder: Encoder[Hour] = Encoder[Int].contramap[Hour](_.value)
  given derivedHourDecoder: Decoder[Hour] = Decoder[Int].emap(Hour.from)

  given derivedHourKeyEncoder: KeyEncoder[Hour] = KeyEncoder[Int].contramap[Hour](_.value)
  given derivedHourKeyDecoder: KeyDecoder[Hour] with {
    override def apply(key: String): Option[Hour] = KeyDecoder.decodeKeyInt.apply(key).flatMap(Hour.from(_).toOption)
  }
  /* } Hour */

  /* Minute { */
  given derivedMinuteEncoder: Encoder[Minute] = Encoder[Int].contramap[Minute](_.value)
  given derivedMinuteDecoder: Decoder[Minute] = Decoder[Int].emap(Minute.from)

  given derivedMinuteKeyEncoder: KeyEncoder[Minute] = KeyEncoder[Int].contramap[Minute](_.value)
  given derivedMinuteKeyDecoder: KeyDecoder[Minute] with {
    override def apply(key: String): Option[Minute] = KeyDecoder.decodeKeyInt.apply(key).flatMap(Minute.from(_).toOption)
  }
  /* } Minute */

  /* Second { */
  given derivedSecondEncoder: Encoder[Second] = Encoder[Int].contramap[Second](_.value)
  given derivedSecondDecoder: Decoder[Second] = Decoder[Int].emap(Second.from)

  given derivedSecondKeyEncoder: KeyEncoder[Second] = KeyEncoder[Int].contramap[Second](_.value)
  given derivedSecondKeyDecoder: KeyDecoder[Second] with {
    override def apply(key: String): Option[Second] = KeyDecoder.decodeKeyInt.apply(key).flatMap(Second.from(_).toOption)
  }
  /* } Second */

  /* Millis { */
  given derivedMillisEncoder: Encoder[Millis] = Encoder[Int].contramap[Millis](_.value)
  given derivedMillisDecoder: Decoder[Millis] = Decoder[Int].emap(Millis.from)

  given derivedMillisKeyEncoder: KeyEncoder[Millis] = KeyEncoder[Int].contramap[Millis](_.value)
  given derivedMillisKeyDecoder: KeyDecoder[Millis] with {
    override def apply(key: String): Option[Millis] = KeyDecoder.decodeKeyInt.apply(key).flatMap(Millis.from(_).toOption)
  }
  /* } Millis */
}
