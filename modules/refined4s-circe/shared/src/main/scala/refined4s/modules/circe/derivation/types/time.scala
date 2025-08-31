package refined4s.modules.circe.derivation.types

import io.circe.{Decoder, Encoder, KeyDecoder, KeyEncoder}
import refined4s.types.time.*

/** @author Kevin Lee
  * @since 2025-08-30
  */
trait time {

  /* Month { */
  inline given derivedMonthEncoder: Encoder[Month] = Encoder[Int].contramap[Month](_.value)
  inline given derivedMonthDecoder: Decoder[Month] = Decoder[Int].emap(Month.from)

  inline given derivedMonthKeyEncoder: KeyEncoder[Month] = KeyEncoder[Int].contramap[Month](_.value)
  inline given derivedMonthKeyDecoder: KeyDecoder[Month] with {
    override def apply(key: String): Option[Month] = KeyDecoder.decodeKeyInt.apply(key).flatMap(Month.from(_).toOption)
  }
  /* } Month */

  /* Day { */
  inline given derivedDayEncoder: Encoder[Day] = Encoder[Int].contramap[Day](_.value)
  inline given derivedDayDecoder: Decoder[Day] = Decoder[Int].emap(Day.from)

  inline given derivedDayKeyEncoder: KeyEncoder[Day] = KeyEncoder[Int].contramap[Day](_.value)
  inline given derivedDayKeyDecoder: KeyDecoder[Day] with {
    override def apply(key: String): Option[Day] = KeyDecoder.decodeKeyInt.apply(key).flatMap(Day.from(_).toOption)
  }
  /* } Day */

  /* Hour { */
  inline given derivedHourEncoder: Encoder[Hour] = Encoder[Int].contramap[Hour](_.value)
  inline given derivedHourDecoder: Decoder[Hour] = Decoder[Int].emap(Hour.from)

  inline given derivedHourKeyEncoder: KeyEncoder[Hour] = KeyEncoder[Int].contramap[Hour](_.value)
  inline given derivedHourKeyDecoder: KeyDecoder[Hour] with {
    override def apply(key: String): Option[Hour] = KeyDecoder.decodeKeyInt.apply(key).flatMap(Hour.from(_).toOption)
  }
  /* } Hour */

  /* Minute { */
  inline given derivedMinuteEncoder: Encoder[Minute] = Encoder[Int].contramap[Minute](_.value)
  inline given derivedMinuteDecoder: Decoder[Minute] = Decoder[Int].emap(Minute.from)

  inline given derivedMinuteKeyEncoder: KeyEncoder[Minute] = KeyEncoder[Int].contramap[Minute](_.value)
  inline given derivedMinuteKeyDecoder: KeyDecoder[Minute] with {
    override def apply(key: String): Option[Minute] = KeyDecoder.decodeKeyInt.apply(key).flatMap(Minute.from(_).toOption)
  }
  /* } Minute */

  /* Second { */
  inline given derivedSecondEncoder: Encoder[Second] = Encoder[Int].contramap[Second](_.value)
  inline given derivedSecondDecoder: Decoder[Second] = Decoder[Int].emap(Second.from)

  inline given derivedSecondKeyEncoder: KeyEncoder[Second] = KeyEncoder[Int].contramap[Second](_.value)
  inline given derivedSecondKeyDecoder: KeyDecoder[Second] with {
    override def apply(key: String): Option[Second] = KeyDecoder.decodeKeyInt.apply(key).flatMap(Second.from(_).toOption)
  }
  /* } Second */

  /* Millis { */
  inline given derivedMillisEncoder: Encoder[Millis] = Encoder[Int].contramap[Millis](_.value)
  inline given derivedMillisDecoder: Decoder[Millis] = Decoder[Int].emap(Millis.from)

  inline given derivedMillisKeyEncoder: KeyEncoder[Millis] = KeyEncoder[Int].contramap[Millis](_.value)
  inline given derivedMillisKeyDecoder: KeyDecoder[Millis] with {
    override def apply(key: String): Option[Millis] = KeyDecoder.decodeKeyInt.apply(key).flatMap(Millis.from(_).toOption)
  }
  /* } Millis */
}
object time extends time
