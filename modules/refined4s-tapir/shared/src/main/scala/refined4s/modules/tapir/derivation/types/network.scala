package refined4s.modules.tapir.derivation.types

import refined4s.types.network.*
import sttp.tapir.Schema

/** @author Kevin Lee
  * @since 2024-04-03
  */
trait network {

  inline given schemaUri: Schema[Uri] = network.derivedUriSchema

  inline given schemaUrl: Schema[Url] = network.derivedUrlSchema

  inline given schemaPortNumber: Schema[PortNumber] = network.derivedPortNumberSchema

  inline given schemaSystemPortNumber: Schema[SystemPortNumber] = network.derivedSystemPortNumberSchema

  inline given schemaNonSystemPortNumber: Schema[NonSystemPortNumber] = network.derivedNonSystemPortNumberSchema

  inline given schemaUserPortNumber: Schema[UserPortNumber] = network.derivedUserPortNumberSchema

  inline given schemaDynamicPortNumber: Schema[DynamicPortNumber] = network.derivedDynamicPortNumberSchema

}
object network {

  inline given derivedUriSchema(using actualSchema: Schema[String]): Schema[Uri] = actualSchema.map(Uri.from(_).toOption)(_.value)

  inline given derivedUrlSchema(using actualSchema: Schema[String]): Schema[Url] = actualSchema.map(Url.from(_).toOption)(_.value)

  inline given derivedPortNumberSchema(using actualSchema: Schema[Int]): Schema[PortNumber] =
    actualSchema.map(PortNumber.from(_).toOption)(_.value)

  inline given derivedSystemPortNumberSchema(using actualSchema: Schema[Int]): Schema[SystemPortNumber] =
    actualSchema.map(SystemPortNumber.from(_).toOption)(_.value)

  inline given derivedNonSystemPortNumberSchema(using actualSchema: Schema[Int]): Schema[NonSystemPortNumber] =
    actualSchema.map(NonSystemPortNumber.from(_).toOption)(_.value)

  inline given derivedUserPortNumberSchema(using actualSchema: Schema[Int]): Schema[UserPortNumber] =
    actualSchema.map(UserPortNumber.from(_).toOption)(_.value)

  inline given derivedDynamicPortNumberSchema(using actualSchema: Schema[Int]): Schema[DynamicPortNumber] =
    actualSchema.map(DynamicPortNumber.from(_).toOption)(_.value)

}
