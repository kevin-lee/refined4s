package refined4s.modules.chimney.derivation.types

import io.scalaland.chimney
import io.scalaland.chimney.{PartialTransformer, Transformer}
import refined4s.types.network.*

/** @author Kevin Lee
  * @since 2023-12-25
  */
trait network {

  inline given derivedUriToStringTransformer: Transformer[Uri, String] with {
    override def transform(src: Uri): String = src.value
  }
  inline given derivedStringToUriPartialTransformer: PartialTransformer[String, Uri] =
    PartialTransformer(value => chimney.partial.Result.fromEitherString(Uri.from(value)))

  inline given derivedUrlToStringTransformer: Transformer[Url, String] with {
    override def transform(src: Url): String = src.value
  }
  inline given derivedStringToUrlPartialTransformer: PartialTransformer[String, Url] =
    PartialTransformer(value => chimney.partial.Result.fromEitherString(Url.from(value)))

  inline given derivedPortNumberToIntTransformer: Transformer[PortNumber, Int] with {
    override def transform(src: PortNumber): Int = src.value
  }
  inline given derivedIntToPortNumberPartialTransformer: PartialTransformer[Int, PortNumber] =
    PartialTransformer(value => chimney.partial.Result.fromEitherString(PortNumber.from(value)))

  inline given derivedSystemPortNumberToIntTransformer: Transformer[SystemPortNumber, Int] with {
    override def transform(src: SystemPortNumber): Int = src.value
  }
  inline given derivedIntToSystemPortNumberPartialTransformer: PartialTransformer[Int, SystemPortNumber] =
    PartialTransformer(value => chimney.partial.Result.fromEitherString(SystemPortNumber.from(value)))

  inline given derivedNonSystemPortNumberToIntTransformer: Transformer[NonSystemPortNumber, Int] with {
    override def transform(src: NonSystemPortNumber): Int = src.value
  }
  inline given derivedIntToNonSystemPortNumberPartialTransformer: PartialTransformer[Int, NonSystemPortNumber] =
    PartialTransformer(value => chimney.partial.Result.fromEitherString(NonSystemPortNumber.from(value)))

  inline given derivedUserPortNumberToIntTransformer: Transformer[UserPortNumber, Int] with {
    override def transform(src: UserPortNumber): Int = src.value
  }
  inline given derivedIntToUserPortNumberPartialTransformer: PartialTransformer[Int, UserPortNumber] =
    PartialTransformer(value => chimney.partial.Result.fromEitherString(UserPortNumber.from(value)))

  inline given derivedDynamicPortNumberToIntTransformer: Transformer[DynamicPortNumber, Int] with {
    override def transform(src: DynamicPortNumber): Int = src.value
  }
  inline given derivedIntToDynamicPortNumberPartialTransformer: PartialTransformer[Int, DynamicPortNumber] =
    PartialTransformer(value => chimney.partial.Result.fromEitherString(DynamicPortNumber.from(value)))

}
object network extends network
