package refined4s.modules.circe.derivation.types

import io.circe.{Decoder, Encoder, KeyDecoder, KeyEncoder}
import refined4s.types
import refined4s.types.all.*

/** @author Kevin Lee
  * @since 2023-12-25
  */
trait network {

  /* Uri */
  inline given derivedUriEncoder: Encoder[Uri] = Encoder[String].contramap[Uri](_.value)
  inline given derivedUriDecoder: Decoder[Uri] = Decoder[String].emap(Uri.from)

  inline given derivedUriKeyEncoder: KeyEncoder[Uri] with {
    override def apply(key: Uri): String = key.value
  }
  inline given derivedUriKeyDecoder: KeyDecoder[Uri] with {
    override def apply(key: String): Option[Uri] = Uri.from(key).toOption
  }

  /* Url */
  inline given derivedUrlEncoder: Encoder[Url] = Encoder[String].contramap[Url](_.value)
  inline given derivedUrlDecoder: Decoder[Url] = Decoder[String].emap(Url.from)

  inline given derivedUrlKeyEncoder: KeyEncoder[Url] with {
    override def apply(key: Url): String = key.value
  }
  inline given derivedUrlKeyDecoder: KeyDecoder[Url] with {
    override def apply(key: String): Option[Url] = Url.from(key).toOption
  }

  /* PortNumber */
  inline given derivedPortNumberEncoder: Encoder[PortNumber] = Encoder[Int].contramap[PortNumber](_.value)
  inline given derivedPortNumberDecoder: Decoder[PortNumber] = Decoder[Int].emap(PortNumber.from)

  inline given derivedPortNumberKeyEncoder: KeyEncoder[PortNumber] with {
    override def apply(key: PortNumber): String = key.value.toString
  }
  inline given derivedPortNumberKeyDecoder: KeyDecoder[PortNumber] with {
    override def apply(key: String): Option[PortNumber] = key.toIntOption.flatMap(PortNumber.from(_).toOption)
  }

  /* SystemPortNumber */
  inline given derivedSystemPortNumberEncoder: Encoder[SystemPortNumber] = Encoder[Int].contramap[SystemPortNumber](_.value)
  inline given derivedSystemPortNumberDecoder: Decoder[SystemPortNumber] = Decoder[Int].emap(SystemPortNumber.from)

  inline given derivedSystemPortNumberKeyEncoder: KeyEncoder[SystemPortNumber] with {
    override def apply(key: SystemPortNumber): String = key.value.toString
  }
  inline given derivedSystemPortNumberKeyDecoder: KeyDecoder[SystemPortNumber] with {
    override def apply(key: String): Option[SystemPortNumber] = key.toIntOption.flatMap(SystemPortNumber.from(_).toOption)
  }

  /* NonSystemPortNumber */
  inline given derivedNonSystemPortNumberEncoder: Encoder[NonSystemPortNumber] = Encoder[Int].contramap[NonSystemPortNumber](_.value)
  inline given derivedNonSystemPortNumberDecoder: Decoder[NonSystemPortNumber] = Decoder[Int].emap(NonSystemPortNumber.from)

  inline given derivedNonSystemPortNumberKeyEncoder: KeyEncoder[NonSystemPortNumber] with {
    override def apply(key: NonSystemPortNumber): String = key.value.toString
  }
  inline given derivedNonSystemPortNumberKeyDecoder: KeyDecoder[NonSystemPortNumber] with {
    override def apply(key: String): Option[NonSystemPortNumber] = key.toIntOption.flatMap(NonSystemPortNumber.from(_).toOption)
  }

  /* UserPortNumber */
  inline given derivedUserPortNumberEncoder: Encoder[UserPortNumber] = Encoder[Int].contramap[UserPortNumber](_.value)
  inline given derivedUserPortNumberDecoder: Decoder[UserPortNumber] = Decoder[Int].emap(UserPortNumber.from)

  inline given derivedUserPortNumberKeyEncoder: KeyEncoder[UserPortNumber] with {
    override def apply(key: UserPortNumber): String = key.value.toString
  }
  inline given derivedUserPortNumberKeyDecoder: KeyDecoder[UserPortNumber] with {
    override def apply(key: String): Option[UserPortNumber] = key.toIntOption.flatMap(UserPortNumber.from(_).toOption)
  }

  /* DynamicPortNumber */
  inline given derivedDynamicPortNumberEncoder: Encoder[DynamicPortNumber] = Encoder[Int].contramap[DynamicPortNumber](_.value)
  inline given derivedDynamicPortNumberDecoder: Decoder[DynamicPortNumber] = Decoder[Int].emap(DynamicPortNumber.from)

  inline given derivedDynamicPortNumberKeyEncoder: KeyEncoder[DynamicPortNumber] with {
    override def apply(key: DynamicPortNumber): String = key.value.toString
  }
  inline given derivedDynamicPortNumberKeyDecoder: KeyDecoder[DynamicPortNumber] with {
    override def apply(key: String): Option[DynamicPortNumber] = key.toIntOption.flatMap(DynamicPortNumber.from(_).toOption)
  }

}
object network extends network
