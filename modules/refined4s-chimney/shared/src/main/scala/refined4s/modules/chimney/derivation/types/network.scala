package refined4s.modules.chimney.derivation.types

import io.scalaland.chimney
import io.scalaland.chimney.{PartialTransformer, Transformer}
import refined4s.types.network.*

/** @author Kevin Lee
  * @since 2023-12-25
  */
trait network {

  given derivedUriToStringTransformer: Transformer[Uri, String]               = network.derivedUriToStringTransformer
  given derivedStringToUriPartialTransformer: PartialTransformer[String, Uri] = network.derivedStringToUriPartialTransformer

  given derivedUrlToStringTransformer: Transformer[Url, String]               = network.derivedUrlToStringTransformer
  given derivedStringToUrlPartialTransformer: PartialTransformer[String, Url] = network.derivedStringToUrlPartialTransformer

  given derivedPortNumberToIntTransformer: Transformer[PortNumber, Int]               = network.derivedPortNumberToIntTransformer
  given derivedIntToPortNumberPartialTransformer: PartialTransformer[Int, PortNumber] =
    network.derivedIntToPortNumberPartialTransformer

  given derivedSystemPortNumberToIntTransformer: Transformer[SystemPortNumber, Int] = network.derivedSystemPortNumberToIntTransformer
  given derivedIntToSystemPortNumberPartialTransformer: PartialTransformer[Int, SystemPortNumber] =
    network.derivedIntToSystemPortNumberPartialTransformer

  given derivedNonSystemPortNumberToIntTransformer: Transformer[NonSystemPortNumber, Int]               =
    network.derivedNonSystemPortNumberToIntTransformer
  given derivedIntToNonSystemPortNumberPartialTransformer: PartialTransformer[Int, NonSystemPortNumber] =
    network.derivedIntToNonSystemPortNumberPartialTransformer

  given derivedUserPortNumberToIntTransformer: Transformer[UserPortNumber, Int] = network.derivedUserPortNumberToIntTransformer
  given derivedIntToUserPortNumberPartialTransformer: PartialTransformer[Int, UserPortNumber] =
    network.derivedIntToUserPortNumberPartialTransformer

  given derivedDynamicPortNumberToIntTransformer: Transformer[DynamicPortNumber, Int]               =
    network.derivedDynamicPortNumberToIntTransformer
  given derivedIntToDynamicPortNumberPartialTransformer: PartialTransformer[Int, DynamicPortNumber] =
    network.derivedIntToDynamicPortNumberPartialTransformer

}
object network {

  given derivedUriToStringTransformer: Transformer[Uri, String] with {
    override def transform(src: Uri): String = src.value
  }
  given derivedStringToUriPartialTransformer: PartialTransformer[String, Uri] =
    PartialTransformer(value => chimney.partial.Result.fromEitherString(Uri.from(value)))

  given derivedUrlToStringTransformer: Transformer[Url, String] with {
    override def transform(src: Url): String = src.value
  }
  given derivedStringToUrlPartialTransformer: PartialTransformer[String, Url] =
    PartialTransformer(value => chimney.partial.Result.fromEitherString(Url.from(value)))

  given derivedPortNumberToIntTransformer: Transformer[PortNumber, Int] with {
    override def transform(src: PortNumber): Int = src.value
  }
  given derivedIntToPortNumberPartialTransformer: PartialTransformer[Int, PortNumber] =
    PartialTransformer(value => chimney.partial.Result.fromEitherString(PortNumber.from(value)))

  given derivedSystemPortNumberToIntTransformer: Transformer[SystemPortNumber, Int] with {
    override def transform(src: SystemPortNumber): Int = src.value
  }
  given derivedIntToSystemPortNumberPartialTransformer: PartialTransformer[Int, SystemPortNumber] =
    PartialTransformer(value => chimney.partial.Result.fromEitherString(SystemPortNumber.from(value)))

  given derivedNonSystemPortNumberToIntTransformer: Transformer[NonSystemPortNumber, Int] with {
    override def transform(src: NonSystemPortNumber): Int = src.value
  }
  given derivedIntToNonSystemPortNumberPartialTransformer: PartialTransformer[Int, NonSystemPortNumber] =
    PartialTransformer(value => chimney.partial.Result.fromEitherString(NonSystemPortNumber.from(value)))

  given derivedUserPortNumberToIntTransformer: Transformer[UserPortNumber, Int] with {
    override def transform(src: UserPortNumber): Int = src.value
  }
  given derivedIntToUserPortNumberPartialTransformer: PartialTransformer[Int, UserPortNumber] =
    PartialTransformer(value => chimney.partial.Result.fromEitherString(UserPortNumber.from(value)))

  given derivedDynamicPortNumberToIntTransformer: Transformer[DynamicPortNumber, Int] with {
    override def transform(src: DynamicPortNumber): Int = src.value
  }
  given derivedIntToDynamicPortNumberPartialTransformer: PartialTransformer[Int, DynamicPortNumber] =
    PartialTransformer(value => chimney.partial.Result.fromEitherString(DynamicPortNumber.from(value)))

}
