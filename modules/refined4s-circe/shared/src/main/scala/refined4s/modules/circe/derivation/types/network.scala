package refined4s.modules.circe.derivation.types

import io.circe.{Decoder, Encoder}
import refined4s.types.all.*

/** @author Kevin Lee
  * @since 2023-12-25
  */
trait network {

  inline given derivedUriEncoder: Encoder[Uri] = Encoder[String].contramap[Uri](_.value)
  inline given derivedUriDecoder: Decoder[Uri] = Decoder[String].emap(Uri.from)

  inline given derivedUrlEncoder: Encoder[Url] = Encoder[String].contramap[Url](_.value)
  inline given derivedUrlDecoder: Decoder[Url] = Decoder[String].emap(Url.from)

  inline given derivedPortNumberEncoder: Encoder[PortNumber] = Encoder[Int].contramap[PortNumber](_.value)
  inline given derivedPortNumberDecoder: Decoder[PortNumber] = Decoder[Int].emap(PortNumber.from)

  inline given derivedSystemPortNumberEncoder: Encoder[SystemPortNumber] = Encoder[Int].contramap[SystemPortNumber](_.value)
  inline given derivedSystemPortNumberDecoder: Decoder[SystemPortNumber] = Decoder[Int].emap(SystemPortNumber.from)

  inline given derivedNonSystemPortNumberEncoder: Encoder[NonSystemPortNumber] = Encoder[Int].contramap[NonSystemPortNumber](_.value)
  inline given derivedNonSystemPortNumberDecoder: Decoder[NonSystemPortNumber] = Decoder[Int].emap(NonSystemPortNumber.from)

  inline given derivedUserPortNumberEncoder: Encoder[UserPortNumber] = Encoder[Int].contramap[UserPortNumber](_.value)
  inline given derivedUserPortNumberDecoder: Decoder[UserPortNumber] = Decoder[Int].emap(UserPortNumber.from)

  inline given derivedDynamicPortNumberEncoder: Encoder[DynamicPortNumber] = Encoder[Int].contramap[DynamicPortNumber](_.value)
  inline given derivedDynamicPortNumberDecoder: Decoder[DynamicPortNumber] = Decoder[Int].emap(DynamicPortNumber.from)

}
