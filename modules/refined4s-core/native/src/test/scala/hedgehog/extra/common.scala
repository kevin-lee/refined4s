package hedgehog.extra

/** @author Kevin Lee
  * @since 2021-04-06
  */
object common {

  final val NonWhitespaceCharRange: List[(Int, Int)] = List( // scalafix:ok DisableSyntax.noFinalVal
    0     -> 8,
    14    -> 27,
    33    -> 5759,
    5761  -> 8191,
    8199  -> 8199,
    8203  -> 8231,
    8234  -> 8286,
    8288  -> 12287,
    12289 -> Char.MaxValue.toInt,
  )

}
