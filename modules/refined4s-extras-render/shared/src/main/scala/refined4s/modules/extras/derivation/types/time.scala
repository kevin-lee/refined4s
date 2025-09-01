package refined4s.modules.extras.derivation.types

import refined4s.types.time.*
import extras.render.Render

/** @author Kevin Lee
  * @since 2025-08-31
  */
trait time {
  inline given derivedMonthRender: Render[Month] = time.derivedMonthRender

  inline given derivedDayRender: Render[Day] = time.derivedDayRender

  inline given derivedHourRender: Render[Hour] = time.derivedHourRender

  inline given derivedMinuteRender: Render[Minute] = time.derivedMinuteRender

  inline given derivedSecondRender: Render[Second] = time.derivedSecondRender

  inline given derivedMillisRender: Render[Millis] = time.derivedMillisRender
}
object time {
  inline given derivedMonthRender(using renderActual: Render[Int]): Render[Month] = renderActual.contramap(_.value)

  inline given derivedDayRender(using renderActual: Render[Int]): Render[Day] = renderActual.contramap(_.value)

  inline given derivedHourRender(using renderActual: Render[Int]): Render[Hour] = renderActual.contramap(_.value)

  inline given derivedMinuteRender(using renderActual: Render[Int]): Render[Minute] = renderActual.contramap(_.value)

  inline given derivedSecondRender(using renderActual: Render[Int]): Render[Second] = renderActual.contramap(_.value)

  inline given derivedMillisRender(using renderActual: Render[Int]): Render[Millis] = renderActual.contramap(_.value)
}
