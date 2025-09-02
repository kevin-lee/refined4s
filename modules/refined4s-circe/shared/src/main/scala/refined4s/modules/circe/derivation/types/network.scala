package refined4s.modules.circe.derivation.types

import io.circe.{Decoder, Encoder, KeyDecoder, KeyEncoder}
import refined4s.types
import refined4s.types.all.*

/** @author Kevin Lee
  * @since 2023-12-25
  */
trait network {

  /* Uri */
  inline given derivedUriEncoder: Encoder[Uri] = network.derivedUriEncoder
  inline given derivedUriDecoder: Decoder[Uri] = network.derivedUriDecoder

  inline given derivedUriKeyEncoder: KeyEncoder[Uri] = network.derivedUriKeyEncoder
  inline given derivedUriKeyDecoder: KeyDecoder[Uri] = network.derivedUriKeyDecoder

  /* Url */
  inline given derivedUrlEncoder: Encoder[Url] = network.derivedUrlEncoder
  inline given derivedUrlDecoder: Decoder[Url] = network.derivedUrlDecoder

  inline given derivedUrlKeyEncoder: KeyEncoder[Url] = network.derivedUrlKeyEncoder
  inline given derivedUrlKeyDecoder: KeyDecoder[Url] = network.derivedUrlKeyDecoder

  /* PortNumber */
  inline given derivedPortNumberEncoder: Encoder[PortNumber] = network.derivedPortNumberEncoder
  inline given derivedPortNumberDecoder: Decoder[PortNumber] = network.derivedPortNumberDecoder

  inline given derivedPortNumberKeyEncoder: KeyEncoder[PortNumber] = network.derivedPortNumberKeyEncoder
  inline given derivedPortNumberKeyDecoder: KeyDecoder[PortNumber] = network.derivedPortNumberKeyDecoder

  /* SystemPortNumber */
  inline given derivedSystemPortNumberEncoder: Encoder[SystemPortNumber] = network.derivedSystemPortNumberEncoder
  inline given derivedSystemPortNumberDecoder: Decoder[SystemPortNumber] = network.derivedSystemPortNumberDecoder

  inline given derivedSystemPortNumberKeyEncoder: KeyEncoder[SystemPortNumber] = network.derivedSystemPortNumberKeyEncoder
  inline given derivedSystemPortNumberKeyDecoder: KeyDecoder[SystemPortNumber] = network.derivedSystemPortNumberKeyDecoder

  /* NonSystemPortNumber */
  inline given derivedNonSystemPortNumberEncoder: Encoder[NonSystemPortNumber] = network.derivedNonSystemPortNumberEncoder
  inline given derivedNonSystemPortNumberDecoder: Decoder[NonSystemPortNumber] = network.derivedNonSystemPortNumberDecoder

  inline given derivedNonSystemPortNumberKeyEncoder: KeyEncoder[NonSystemPortNumber] = network.derivedNonSystemPortNumberKeyEncoder
  inline given derivedNonSystemPortNumberKeyDecoder: KeyDecoder[NonSystemPortNumber] = network.derivedNonSystemPortNumberKeyDecoder

  /* UserPortNumber */
  inline given derivedUserPortNumberEncoder: Encoder[UserPortNumber] = network.derivedUserPortNumberEncoder
  inline given derivedUserPortNumberDecoder: Decoder[UserPortNumber] = network.derivedUserPortNumberDecoder

  inline given derivedUserPortNumberKeyEncoder: KeyEncoder[UserPortNumber] = network.derivedUserPortNumberKeyEncoder
  inline given derivedUserPortNumberKeyDecoder: KeyDecoder[UserPortNumber] = network.derivedUserPortNumberKeyDecoder

  /* DynamicPortNumber */
  inline given derivedDynamicPortNumberEncoder: Encoder[DynamicPortNumber] = network.derivedDynamicPortNumberEncoder
  inline given derivedDynamicPortNumberDecoder: Decoder[DynamicPortNumber] = network.derivedDynamicPortNumberDecoder

  inline given derivedDynamicPortNumberKeyEncoder: KeyEncoder[DynamicPortNumber] = network.derivedDynamicPortNumberKeyEncoder
  inline given derivedDynamicPortNumberKeyDecoder: KeyDecoder[DynamicPortNumber] = network.derivedDynamicPortNumberKeyDecoder

}
object network {

  /* Uri */
  given derivedUriEncoder: Encoder[Uri] = Encoder[String].contramap[Uri](_.value)
  given derivedUriDecoder: Decoder[Uri] = Decoder[String].emap(Uri.from)

  given derivedUriKeyEncoder: KeyEncoder[Uri] with {
    override def apply(key: Uri): String = key.value
  }
  given derivedUriKeyDecoder: KeyDecoder[Uri] with {
    override def apply(key: String): Option[Uri] = Uri.from(key).toOption
  }

  /* Url */
  given derivedUrlEncoder: Encoder[Url] = Encoder[String].contramap[Url](_.value)
  given derivedUrlDecoder: Decoder[Url] = Decoder[String].emap(Url.from)

  given derivedUrlKeyEncoder: KeyEncoder[Url] with {
    override def apply(key: Url): String = key.value
  }
  given derivedUrlKeyDecoder: KeyDecoder[Url] with {
    override def apply(key: String): Option[Url] = Url.from(key).toOption
  }

  /* PortNumber */
  given derivedPortNumberEncoder: Encoder[PortNumber] = Encoder[Int].contramap[PortNumber](_.value)
  given derivedPortNumberDecoder: Decoder[PortNumber] = Decoder[Int].emap(PortNumber.from)

  given derivedPortNumberKeyEncoder: KeyEncoder[PortNumber] with {
    override def apply(key: PortNumber): String = key.value.toString
  }
  given derivedPortNumberKeyDecoder: KeyDecoder[PortNumber] with {
    override def apply(key: String): Option[PortNumber] = key.toIntOption.flatMap(PortNumber.from(_).toOption)
  }

  /* SystemPortNumber */
  given derivedSystemPortNumberEncoder: Encoder[SystemPortNumber] = Encoder[Int].contramap[SystemPortNumber](_.value)
  given derivedSystemPortNumberDecoder: Decoder[SystemPortNumber] = Decoder[Int].emap(SystemPortNumber.from)

  given derivedSystemPortNumberKeyEncoder: KeyEncoder[SystemPortNumber] with {
    override def apply(key: SystemPortNumber): String = key.value.toString
  }
  given derivedSystemPortNumberKeyDecoder: KeyDecoder[SystemPortNumber] with {
    override def apply(key: String): Option[SystemPortNumber] = key.toIntOption.flatMap(SystemPortNumber.from(_).toOption)
  }

  /* NonSystemPortNumber */
  given derivedNonSystemPortNumberEncoder: Encoder[NonSystemPortNumber] = Encoder[Int].contramap[NonSystemPortNumber](_.value)
  given derivedNonSystemPortNumberDecoder: Decoder[NonSystemPortNumber] = Decoder[Int].emap(NonSystemPortNumber.from)

  given derivedNonSystemPortNumberKeyEncoder: KeyEncoder[NonSystemPortNumber] with {
    override def apply(key: NonSystemPortNumber): String = key.value.toString
  }
  given derivedNonSystemPortNumberKeyDecoder: KeyDecoder[NonSystemPortNumber] with {
    override def apply(key: String): Option[NonSystemPortNumber] = key.toIntOption.flatMap(NonSystemPortNumber.from(_).toOption)
  }

  /* UserPortNumber */
  given derivedUserPortNumberEncoder: Encoder[UserPortNumber] = Encoder[Int].contramap[UserPortNumber](_.value)
  given derivedUserPortNumberDecoder: Decoder[UserPortNumber] = Decoder[Int].emap(UserPortNumber.from)

  given derivedUserPortNumberKeyEncoder: KeyEncoder[UserPortNumber] with {
    override def apply(key: UserPortNumber): String = key.value.toString
  }
  given derivedUserPortNumberKeyDecoder: KeyDecoder[UserPortNumber] with {
    override def apply(key: String): Option[UserPortNumber] = key.toIntOption.flatMap(UserPortNumber.from(_).toOption)
  }

  /* DynamicPortNumber */
  given derivedDynamicPortNumberEncoder: Encoder[DynamicPortNumber] = Encoder[Int].contramap[DynamicPortNumber](_.value)
  given derivedDynamicPortNumberDecoder: Decoder[DynamicPortNumber] = Decoder[Int].emap(DynamicPortNumber.from)

  given derivedDynamicPortNumberKeyEncoder: KeyEncoder[DynamicPortNumber] with {
    override def apply(key: DynamicPortNumber): String = key.value.toString
  }
  given derivedDynamicPortNumberKeyDecoder: KeyDecoder[DynamicPortNumber] with {
    override def apply(key: String): Option[DynamicPortNumber] = key.toIntOption.flatMap(DynamicPortNumber.from(_).toOption)
  }

}
