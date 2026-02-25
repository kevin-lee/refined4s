package refined4s.types

object UuidV7TestTools {
  /* UUIDv7 test strings to be used in macros or other compile-time validations
   */

  /*Example valid v7 UUID
   */
  @SuppressWarnings(Array("org.wartremover.warts.FinalVal"))
  final val ValidUuidV7 = "018f2f2c-e160-7000-8000-000000000000" // scalafix:ok DisableSyntax.noFinalVal

  /* UUID Version 4 */
  @SuppressWarnings(Array("org.wartremover.warts.FinalVal"))
  final val InvalidUuidVersion = "3108715a-6477-4cd1-9ace-85b1260be03d" // scalafix:ok DisableSyntax.noFinalVal

  val validUuidV7Strings: List[String] = List(
    "019ab6f1-4c2d-7a3e-8b91-2f6c4d8e1a73",
    "019ab6f1-8d77-7f24-9a0c-6b2e1d4f8c95",
    "019ab6f1-b2a9-7c68-a4f2-3d9e7b1c5a20",
    "019ab6f1-d4e3-71bf-b8c5-9f2a6d3e4b17",
    "019ab6f2-05ac-7e19-8d34-1b7f2c9a6e58",
    "019ab6f2-2e41-74c7-9f85-5c1d8b3a7e90",
    "019ab6f2-58d2-7b6a-a1e3-7d4c2f9b5a61",
    "019ab6f2-7a0f-70d4-b2c9-8e1a3d6f4b25",
    "019ab6f2-9cbe-7f83-8a47-2c5d9e1b6f34",
    "019ab6f2-c13a-732e-9b6d-4f8a2c7e1d59",
    "019ab6f2-e7f4-7d15-a8c2-6d3b9f4e1a70",
    "019ab6f3-12b8-76af-b4e1-9a5c2d7f3e86",
    "019ab6f3-3d6c-7e42-8f9a-1c4b7d2e6a53",
    "019ab6f3-5f91-79d8-9c31-3e6a1f8b4d27",
    "019ab6f3-84e5-7b20-a7d4-5b9e3c1f6a72",
    "019ab6f3-a9d0-75f6-b1a8-7c2d4e9f3b65",
    "019ab6f3-cf27-7c9b-8e52-2a7d1f4b6c93",
    "019ab6f3-f1ba-7184-9d6f-4c8b2e7a1d50",
    "019ab6f4-1c4e-7a6d-a3b9-6e1f5c2d8b74",
    "019ab6f4-46f9-7d31-b7c4-8a2e6f1d3c95",
  )
}
