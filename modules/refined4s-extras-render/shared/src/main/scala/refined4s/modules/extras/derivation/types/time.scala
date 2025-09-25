package refined4s.modules.extras.derivation.types

import refined4s.types.time.*
import extras.render.Render

/** @author Kevin Lee
  * @since 2025-08-31
  */
trait time {
  given derivedMonthRender: Render[Month] = time.derivedMonthRender

  given derivedDayRender: Render[Day] = time.derivedDayRender

  given derivedHourRender: Render[Hour] = time.derivedHourRender

  given derivedMinuteRender: Render[Minute] = time.derivedMinuteRender

  given derivedSecondRender: Render[Second] = time.derivedSecondRender

  given derivedMillisRender: Render[Millis] = time.derivedMillisRender
}
object time {
  given derivedMonthRender(using renderActual: Render[Int]): Render[Month] = renderActual.contramap(_.value)

  given derivedDayRender(using renderActual: Render[Int]): Render[Day] = renderActual.contramap(_.value)

  given derivedHourRender(using renderActual: Render[Int]): Render[Hour] = renderActual.contramap(_.value)

  given derivedMinuteRender(using renderActual: Render[Int]): Render[Minute] = renderActual.contramap(_.value)

  given derivedSecondRender(using renderActual: Render[Int]): Render[Second] = renderActual.contramap(_.value)

  given derivedMillisRender(using renderActual: Render[Int]): Render[Millis] = renderActual.contramap(_.value)
}
