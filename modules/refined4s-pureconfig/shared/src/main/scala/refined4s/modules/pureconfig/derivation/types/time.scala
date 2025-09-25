package refined4s.modules.pureconfig.derivation.types

import pureconfig.{ConfigReader, ConfigWriter}
import refined4s.types.time.*

/** @author Kevin Lee
  * @since 2025-09-03
  */
trait time {

  given derivedMonthConfigReader: ConfigReader[Month] = time.derivedMonthConfigReader
  given derivedMonthConfigWriter: ConfigWriter[Month] = time.derivedMonthConfigWriter

  given derivedDayConfigReader: ConfigReader[Day] = time.derivedDayConfigReader
  given derivedDayConfigWriter: ConfigWriter[Day] = time.derivedDayConfigWriter

  given derivedHourConfigReader: ConfigReader[Hour] = time.derivedHourConfigReader
  given derivedHourConfigWriter: ConfigWriter[Hour] = time.derivedHourConfigWriter

  given derivedMinuteConfigReader: ConfigReader[Minute] = time.derivedMinuteConfigReader
  given derivedMinuteConfigWriter: ConfigWriter[Minute] = time.derivedMinuteConfigWriter

  given derivedSecondConfigReader: ConfigReader[Second] = time.derivedSecondConfigReader
  given derivedSecondConfigWriter: ConfigWriter[Second] = time.derivedSecondConfigWriter

  given derivedMillisConfigReader: ConfigReader[Millis] = time.derivedMillisConfigReader
  given derivedMillisConfigWriter: ConfigWriter[Millis] = time.derivedMillisConfigWriter

}
object time {
  import refined4s.internal.typeTools.*

  given derivedMonthConfigReader: ConfigReader[Month] = ConfigReader[Int].emap { a =>
    Month.from(a).left.map { err =>
      val expectedType = getTypeName[Month]
      internalDef.userValidationFailed(value = a, expectedType = expectedType, err = err)
    }
  }
  given derivedMonthConfigWriter: ConfigWriter[Month] = ConfigWriter[Int].contramap(_.value)

  given derivedDayConfigReader: ConfigReader[Day] = ConfigReader[Int].emap { a =>
    Day.from(a).left.map { err =>
      val expectedType = getTypeName[Day]
      internalDef.userValidationFailed(value = a, expectedType = expectedType, err = err)
    }
  }
  given derivedDayConfigWriter: ConfigWriter[Day] = ConfigWriter[Int].contramap(_.value)

  given derivedHourConfigReader: ConfigReader[Hour] = ConfigReader[Int].emap { a =>
    Hour.from(a).left.map { err =>
      val expectedType = getTypeName[Hour]
      internalDef.userValidationFailed(value = a, expectedType = expectedType, err = err)
    }
  }
  given derivedHourConfigWriter: ConfigWriter[Hour] = ConfigWriter[Int].contramap(_.value)

  given derivedMinuteConfigReader: ConfigReader[Minute] = ConfigReader[Int].emap { a =>
    Minute.from(a).left.map { err =>
      val expectedType = getTypeName[Minute]
      internalDef.userValidationFailed(value = a, expectedType = expectedType, err = err)
    }
  }
  given derivedMinuteConfigWriter: ConfigWriter[Minute] = ConfigWriter[Int].contramap(_.value)

  given derivedSecondConfigReader: ConfigReader[Second] = ConfigReader[Int].emap { a =>
    Second.from(a).left.map { err =>
      val expectedType = getTypeName[Second]
      internalDef.userValidationFailed(value = a, expectedType = expectedType, err = err)
    }
  }
  given derivedSecondConfigWriter: ConfigWriter[Second] = ConfigWriter[Int].contramap(_.value)

  given derivedMillisConfigReader: ConfigReader[Millis] = ConfigReader[Int].emap { a =>
    Millis.from(a).left.map { err =>
      val expectedType = getTypeName[Millis]
      internalDef.userValidationFailed(value = a, expectedType = expectedType, err = err)
    }
  }
  given derivedMillisConfigWriter: ConfigWriter[Millis] = ConfigWriter[Int].contramap(_.value)

}
