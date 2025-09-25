package refined4s.modules.doobie.derivation.types

import cats.effect.IO
import cats.syntax.all.*
import doobie.syntax.all.*
import extras.doobie.RunWithDb
import extras.doobie.ce3.DbTools
import extras.hedgehog.ce3.CatsEffectRunner
import hedgehog.*
import hedgehog.runner.*
import refined4s.*
import refined4s.types.numeric.*

import java.util.concurrent.atomic.AtomicInteger

/** @author Kevin Lee
  * @since 2025-09-03
  */
trait numericSpec extends CatsEffectRunner, RunWithDb {

  val postgresPortNumber: AtomicInteger = AtomicInteger(6532)

  import effectie.instances.ce3.fx.ioFx

  type F[A] = IO[A]
  val F: IO.type = IO

  protected val numericTypeClasses: refined4s.modules.doobie.derivation.types.numeric
  import numericTypeClasses.given

  def allTests: List[Test] = List(
    propertyWithDb("test Get[NegInt] and Put[NegInt]", postgresPortNumber.getAndIncrement(), testGetAndPutNegInt),
    //
    propertyWithDb("test Get[NonNegInt] and Put[NonNegInt]", postgresPortNumber.getAndIncrement(), testGetAndPutNonNegInt),
    //
    propertyWithDb("test Get[PosInt] and Put[PosInt]", postgresPortNumber.getAndIncrement(), testGetAndPutPosInt),
    //
    propertyWithDb("test Get[NonPosInt] and Put[NonPosInt]", postgresPortNumber.getAndIncrement(), testGetAndPutNonPosInt),
    //
    propertyWithDb("test Get[NegLong] and Put[NegLong]", postgresPortNumber.getAndIncrement(), testGetAndPutNegLong),
    //
    propertyWithDb("test Get[NonNegLong] and Put[NonNegLong]", postgresPortNumber.getAndIncrement(), testGetAndPutNonNegLong),
    //
    propertyWithDb("test Get[PosLong] and Put[PosLong]", postgresPortNumber.getAndIncrement(), testGetAndPutPosLong),
    //
    propertyWithDb("test Get[NonPosLong] and Put[NonPosLong]", postgresPortNumber.getAndIncrement(), testGetAndPutNonPosLong),
    //
    propertyWithDb("test Get[NegShort] and Put[NegShort]", postgresPortNumber.getAndIncrement(), testGetAndPutNegShort),
    //
    propertyWithDb("test Get[NonNegShort] and Put[NonNegShort]", postgresPortNumber.getAndIncrement(), testGetAndPutNonNegShort),
    //
    propertyWithDb("test Get[PosShort] and Put[PosShort]", postgresPortNumber.getAndIncrement(), testGetAndPutPosShort),
    //
    propertyWithDb("test Get[NonPosShort] and Put[NonPosShort]", postgresPortNumber.getAndIncrement(), testGetAndPutNonPosShort),
    //
    propertyWithDb("test Get[NegByte] and Put[NegByte]", postgresPortNumber.getAndIncrement(), testGetAndPutNegByte),
    //
    propertyWithDb("test Get[NonNegByte] and Put[NonNegByte]", postgresPortNumber.getAndIncrement(), testGetAndPutNonNegByte),
    //
    propertyWithDb("test Get[PosByte] and Put[PosByte]", postgresPortNumber.getAndIncrement(), testGetAndPutPosByte),
    //
    propertyWithDb("test Get[NonPosByte] and Put[NonPosByte]", postgresPortNumber.getAndIncrement(), testGetAndPutNonPosByte),
    //
    propertyWithDb("test Get[NegFloat] and Put[NegFloat]", postgresPortNumber.getAndIncrement(), testGetAndPutNegFloat),
    //
    propertyWithDb("test Get[NonNegFloat] and Put[NonNegFloat]", postgresPortNumber.getAndIncrement(), testGetAndPutNonNegFloat),
    //
    propertyWithDb("test Get[PosFloat] and Put[PosFloat]", postgresPortNumber.getAndIncrement(), testGetAndPutPosFloat),
    //
    propertyWithDb("test Get[NonPosFloat] and Put[NonPosFloat]", postgresPortNumber.getAndIncrement(), testGetAndPutNonPosFloat),
    //
    propertyWithDb("test Get[NegDouble] and Put[NegDouble]", postgresPortNumber.getAndIncrement(), testGetAndPutNegDouble),
    //
    propertyWithDb("test Get[NonNegDouble] and Put[NonNegDouble]", postgresPortNumber.getAndIncrement(), testGetAndPutNonNegDouble),
    //
    propertyWithDb("test Get[PosDouble] and Put[PosDouble]", postgresPortNumber.getAndIncrement(), testGetAndPutPosDouble),
    //
    propertyWithDb("test Get[NonPosDouble] and Put[NonPosDouble]", postgresPortNumber.getAndIncrement(), testGetAndPutNonPosDouble),
    //
    propertyWithDb("test Get[NegBigInt] and Put[NegBigInt]", postgresPortNumber.getAndIncrement(), testGetAndPutNegBigInt),
    //
    propertyWithDb("test Get[NonNegBigInt] and Put[NonNegBigInt]", postgresPortNumber.getAndIncrement(), testGetAndPutNonNegBigInt),
    //
    propertyWithDb("test Get[PosBigInt] and Put[PosBigInt]", postgresPortNumber.getAndIncrement(), testGetAndPutPosBigInt),
    //
    propertyWithDb("test Get[NonPosBigInt] and Put[NonPosBigInt]", postgresPortNumber.getAndIncrement(), testGetAndPutNonPosBigInt),
    //
    propertyWithDb("test Get[NegBigDecimal] and Put[NegBigDecimal]", postgresPortNumber.getAndIncrement(), testGetAndPutNegBigDecimal),
    //
    propertyWithDb(
      "test Get[NonNegBigDecimal] and Put[NonNegBigDecimal]",
      postgresPortNumber.getAndIncrement(),
      testGetAndPutNonNegBigDecimal,
    ),
    //
    propertyWithDb("test Get[PosBigDecimal] and Put[PosBigDecimal]", postgresPortNumber.getAndIncrement(), testGetAndPutPosBigDecimal),
    //
    propertyWithDb(
      "test Get[NonPosBigDecimal] and Put[NonPosBigDecimal]",
      postgresPortNumber.getAndIncrement(),
      testGetAndPutNonPosBigDecimal,
    ),
  )

  def testGetAndPutNegInt(testName: String, postgresPortNumber: Int): Property =
    for {
      n <- Gen.int(Range.linear(-1, Int.MinValue)).log("n")
    } yield runIO(
      withDb[F](
        testName,
        postgresPortNumber,
        sql"""CREATE SCHEMA IF NOT EXISTS db_tools_test""",
        sql"""
          CREATE TABLE IF NOT EXISTS db_tools_test.example
          (
             id SERIAL PRIMARY KEY,
             value INT NOT NULL
          )
        """,
      ) { transactor =>

        val expected = NegInt.unsafeFrom(n)

        val expectedFetchBefore = none[NegInt]
        val expectedInsert      = 1
        val expectedFetchAfter  = n.some

        val fetch = DbTools.fetchSingleRow[F][NegInt](
          sql"""
              SELECT value
                FROM db_tools_test.example
          """
        )(transactor)

        val insert = DbTools.updateSingle[F](
          sql"""
            INSERT INTO db_tools_test.example (value) VALUES ($expected)
          """
        )(transactor)

        for {
          fetchResultBefore <- fetch.map(_ ==== expectedFetchBefore)
          insertResult      <- insert.map(_ ==== expectedInsert)
          fetchResultAfter  <- fetch.map(_ ==== expectedFetchAfter)
        } yield Result.all(
          List(
            fetchResultBefore.log("Failed: fetch before"),
            insertResult.log("Failed: insert"),
            fetchResultAfter.log("Failed: fetch after"),
          )
        )

      }
    )
  ///

  def testGetAndPutNonNegInt(testName: String, postgresPortNumber: Int): Property =
    for {
      n <- Gen.int(Range.linear(0, Int.MaxValue)).log("n")
    } yield runIO(
      withDb[F](
        testName,
        postgresPortNumber,
        sql"""CREATE SCHEMA IF NOT EXISTS db_tools_test""",
        sql"""
          CREATE TABLE IF NOT EXISTS db_tools_test.example
          (
             id SERIAL PRIMARY KEY,
             value INT NOT NULL
          )
        """,
      ) { transactor =>

        val expected = NonNegInt.unsafeFrom(n)

        val expectedFetchBefore = none[NonNegInt]
        val expectedInsert      = 1
        val expectedFetchAfter  = n.some

        val fetch = DbTools.fetchSingleRow[F][NonNegInt](
          sql"""
            SELECT value
              FROM db_tools_test.example
          """
        )(transactor)

        val insert = DbTools.updateSingle[F](
          sql"""
            INSERT INTO db_tools_test.example (value) VALUES ($expected)
          """
        )(transactor)

        for {
          fetchResultBefore <- fetch.map(_ ==== expectedFetchBefore)
          insertResult      <- insert.map(_ ==== expectedInsert)
          fetchResultAfter  <- fetch.map(_ ==== expectedFetchAfter)
        } yield Result.all(
          List(
            fetchResultBefore.log("Failed: fetch before"),
            insertResult.log("Failed: insert"),
            fetchResultAfter.log("Failed: fetch after"),
          )
        )

      }
    )

  ///

  def testGetAndPutPosInt(testName: String, postgresPortNumber: Int): Property =
    for {
      n <- Gen.int(Range.linear(1, Int.MaxValue)).log("n")
    } yield runIO(
      withDb[F](
        testName,
        postgresPortNumber,
        sql"""CREATE SCHEMA IF NOT EXISTS db_tools_test""",
        sql"""
          CREATE TABLE IF NOT EXISTS db_tools_test.example
          (
             id SERIAL PRIMARY KEY,
             value INT NOT NULL
          )
        """,
      ) { transactor =>

        val expected = PosInt.unsafeFrom(n)

        val expectedFetchBefore = none[PosInt]
        val expectedInsert      = 1
        val expectedFetchAfter  = n.some

        val fetch = DbTools.fetchSingleRow[F][PosInt](
          sql"""
            SELECT value
              FROM db_tools_test.example
          """
        )(transactor)

        val insert = DbTools.updateSingle[F](
          sql"""
             INSERT INTO db_tools_test.example (value) VALUES ($expected)
          """
        )(transactor)

        for {
          fetchResultBefore <- fetch.map(_ ==== expectedFetchBefore)
          insertResult      <- insert.map(_ ==== expectedInsert)
          fetchResultAfter  <- fetch.map(_ ==== expectedFetchAfter)
        } yield Result.all(
          List(
            fetchResultBefore.log("Failed: fetch before"),
            insertResult.log("Failed: insert"),
            fetchResultAfter.log("Failed: fetch after"),
          )
        )

      }
    )

  ///
  def testGetAndPutNonPosInt(testName: String, postgresPortNumber: Int): Property =
    for {
      n <- Gen.int(Range.linear(0, Int.MinValue)).log("n")
    } yield runIO(
      withDb[F](
        testName,
        postgresPortNumber,
        sql"""CREATE SCHEMA IF NOT EXISTS db_tools_test""",
        sql"""
          CREATE TABLE IF NOT EXISTS db_tools_test.example
          (
             id SERIAL PRIMARY KEY,
             value INT NOT NULL
          )
        """,
      ) { transactor =>

        val expected = NonPosInt.unsafeFrom(n)

        val expectedFetchBefore = none[NonPosInt]
        val expectedInsert      = 1
        val expectedFetchAfter  = n.some

        val fetch = DbTools.fetchSingleRow[F][NonPosInt](
          sql"""
            SELECT value
              FROM db_tools_test.example
          """
        )(transactor)

        val insert = DbTools.updateSingle[F](
          sql"""
            INSERT INTO db_tools_test.example (value) VALUES ($expected)
          """
        )(transactor)

        for {
          fetchResultBefore <- fetch.map(_ ==== expectedFetchBefore)
          insertResult      <- insert.map(_ ==== expectedInsert)
          fetchResultAfter  <- fetch.map(_ ==== expectedFetchAfter)
        } yield Result.all(
          List(
            fetchResultBefore.log("Failed: fetch before"),
            insertResult.log("Failed: insert"),
            fetchResultAfter.log("Failed: fetch after"),
          )
        )

      }
    )

  ///

  def testGetAndPutNegLong(testName: String, postgresPortNumber: Int): Property =
    for {
      n <- Gen.long(Range.linear(-1L, Long.MinValue)).log("n")
    } yield runIO(
      withDb[F](
        testName,
        postgresPortNumber,
        sql"""CREATE SCHEMA IF NOT EXISTS db_tools_test""",
        sql"""
          CREATE TABLE IF NOT EXISTS db_tools_test.example
          (
             id SERIAL PRIMARY KEY,
             value BIGINT NOT NULL
          )
        """,
      ) { transactor =>

        val expected = NegLong.unsafeFrom(n)

        val expectedFetchBefore = none[NegLong]
        val expectedInsert      = 1
        val expectedFetchAfter  = n.some

        val fetch = DbTools.fetchSingleRow[F][NegLong](
          sql"""
            SELECT value
              FROM db_tools_test.example
         """
        )(transactor)

        val insert = DbTools.updateSingle[F](
          sql"""
            INSERT INTO db_tools_test.example (value) VALUES ($expected)
          """
        )(transactor)

        for {
          fetchResultBefore <- fetch.map(_ ==== expectedFetchBefore)
          insertResult      <- insert.map(_ ==== expectedInsert)
          fetchResultAfter  <- fetch.map(_ ==== expectedFetchAfter)
        } yield Result.all(
          List(
            fetchResultBefore.log("Failed: fetch before"),
            insertResult.log("Failed: insert"),
            fetchResultAfter.log("Failed: fetch after"),
          )
        )

      }
    )

  ///

  def testGetAndPutNonNegLong(testName: String, postgresPortNumber: Int): Property =
    for {
      n <- Gen.long(Range.linear(0L, Long.MaxValue)).log("n")
    } yield runIO(
      withDb[F](
        testName,
        postgresPortNumber,
        sql"""CREATE SCHEMA IF NOT EXISTS db_tools_test""",
        sql"""
          CREATE TABLE IF NOT EXISTS db_tools_test.example
          (
             id SERIAL PRIMARY KEY,
             value BIGINT NOT NULL
          )
        """,
      ) { transactor =>

        val expected = NonNegLong.unsafeFrom(n)

        val expectedFetchBefore = none[NonNegLong]
        val expectedInsert      = 1
        val expectedFetchAfter  = n.some

        val fetch = DbTools.fetchSingleRow[F][NonNegLong](
          sql"""
            SELECT value
              FROM db_tools_test.example
          """
        )(transactor)

        val insert = DbTools.updateSingle[F](
          sql"""
            INSERT INTO db_tools_test.example (value) VALUES ($expected)
          """
        )(transactor)

        for {
          fetchResultBefore <- fetch.map(_ ==== expectedFetchBefore)
          insertResult      <- insert.map(_ ==== expectedInsert)
          fetchResultAfter  <- fetch.map(_ ==== expectedFetchAfter)
        } yield Result.all(
          List(
            fetchResultBefore.log("Failed: fetch before"),
            insertResult.log("Failed: insert"),
            fetchResultAfter.log("Failed: fetch after"),
          )
        )

      }
    )

  ///

  def testGetAndPutPosLong(testName: String, postgresPortNumber: Int): Property =
    for {
      n <- Gen.long(Range.linear(1L, Long.MaxValue)).log("n")
    } yield runIO(
      withDb[F](
        testName,
        postgresPortNumber,
        sql"""CREATE SCHEMA IF NOT EXISTS db_tools_test""",
        sql"""
          CREATE TABLE IF NOT EXISTS db_tools_test.example
          (
             id SERIAL PRIMARY KEY,
             value BIGINT NOT NULL
          )
        """,
      ) { transactor =>

        val expected = PosLong.unsafeFrom(n)

        val expectedFetchBefore = none[PosLong]
        val expectedInsert      = 1
        val expectedFetchAfter  = n.some

        val fetch = DbTools.fetchSingleRow[F][PosLong](
          sql"""
            SELECT value
              FROM db_tools_test.example
          """
        )(transactor)

        val insert = DbTools.updateSingle[F](
          sql"""
            INSERT INTO db_tools_test.example (value) VALUES ($expected)
          """
        )(transactor)

        for {
          fetchResultBefore <- fetch.map(_ ==== expectedFetchBefore)
          insertResult      <- insert.map(_ ==== expectedInsert)
          fetchResultAfter  <- fetch.map(_ ==== expectedFetchAfter)
        } yield Result.all(
          List(
            fetchResultBefore.log("Failed: fetch before"),
            insertResult.log("Failed: insert"),
            fetchResultAfter.log("Failed: fetch after"),
          )
        )

      }
    )

  ///

  def testGetAndPutNonPosLong(testName: String, postgresPortNumber: Int): Property =
    for {
      n <- Gen.long(Range.linear(0, Long.MinValue)).log("n")
    } yield runIO(
      withDb[F](
        testName,
        postgresPortNumber,
        sql"""CREATE SCHEMA IF NOT EXISTS db_tools_test""",
        sql"""
          CREATE TABLE IF NOT EXISTS db_tools_test.example
          (
             id SERIAL PRIMARY KEY,
             value BIGINT NOT NULL
          )
        """,
      ) { transactor =>

        val expected = NonPosLong.unsafeFrom(n)

        val expectedFetchBefore = none[NonPosLong]
        val expectedInsert      = 1
        val expectedFetchAfter  = n.some

        val fetch = DbTools.fetchSingleRow[F][NonPosLong](
          sql"""
            SELECT value
              FROM db_tools_test.example
          """
        )(transactor)

        val insert = DbTools.updateSingle[F](
          sql"""
            INSERT INTO db_tools_test.example (value) VALUES ($expected)
          """
        )(transactor)

        for {
          fetchResultBefore <- fetch.map(_ ==== expectedFetchBefore)
          insertResult      <- insert.map(_ ==== expectedInsert)
          fetchResultAfter  <- fetch.map(_ ==== expectedFetchAfter)
        } yield Result.all(
          List(
            fetchResultBefore.log("Failed: fetch before"),
            insertResult.log("Failed: insert"),
            fetchResultAfter.log("Failed: fetch after"),
          )
        )

      }
    )

  ///

  def testGetAndPutNegShort(testName: String, postgresPortNumber: Int): Property =
    for {
      n <- Gen.short(Range.linear(-1, Short.MinValue)).log("n")
    } yield runIO(
      withDb[F](
        testName,
        postgresPortNumber,
        sql"""CREATE SCHEMA IF NOT EXISTS db_tools_test""",
        sql"""
        CREATE TABLE IF NOT EXISTS db_tools_test.example
        (
           id SERIAL PRIMARY KEY,
           value INT NOT NULL
        )
      """,
      ) { transactor =>

        val expected = NegShort.unsafeFrom(n)

        val expectedFetchBefore = none[NegShort]
        val expectedInsert      = 1
        val expectedFetchAfter  = n.some

        val fetch = DbTools.fetchSingleRow[F][NegShort](
          sql"""
        SELECT value
          FROM db_tools_test.example
    """
        )(transactor)

        val insert = DbTools.updateSingle[F](
          sql"""
         INSERT INTO db_tools_test.example (value) VALUES ($expected)
    """
        )(transactor)

        for {
          fetchResultBefore <- fetch.map(_ ==== expectedFetchBefore)
          insertResult      <- insert.map(_ ==== expectedInsert)
          fetchResultAfter  <- fetch.map(_ ==== expectedFetchAfter)
        } yield Result.all(
          List(
            fetchResultBefore.log("Failed: fetch before"),
            insertResult.log("Failed: insert"),
            fetchResultAfter.log("Failed: fetch after"),
          )
        )

      }
    )

  ///

  def testGetAndPutNonNegShort(testName: String, postgresPortNumber: Int): Property =
    for {
      n <- Gen.short(Range.linear(0, Short.MaxValue)).log("n")
    } yield runIO(
      withDb[F](
        testName,
        postgresPortNumber,
        sql"""CREATE SCHEMA IF NOT EXISTS db_tools_test""",
        sql"""
        CREATE TABLE IF NOT EXISTS db_tools_test.example
        (
           id SERIAL PRIMARY KEY,
           value INT NOT NULL
        )
      """,
      ) { transactor =>

        val expected = NonNegShort.unsafeFrom(n)

        val expectedFetchBefore = none[NonNegShort]
        val expectedInsert      = 1
        val expectedFetchAfter  = n.some

        val fetch = DbTools.fetchSingleRow[F][NonNegShort](
          sql"""
        SELECT value
          FROM db_tools_test.example
    """
        )(transactor)

        val insert = DbTools.updateSingle[F](
          sql"""
         INSERT INTO db_tools_test.example (value) VALUES ($expected)
    """
        )(transactor)

        for {
          fetchResultBefore <- fetch.map(_ ==== expectedFetchBefore)
          insertResult      <- insert.map(_ ==== expectedInsert)
          fetchResultAfter  <- fetch.map(_ ==== expectedFetchAfter)
        } yield Result.all(
          List(
            fetchResultBefore.log("Failed: fetch before"),
            insertResult.log("Failed: insert"),
            fetchResultAfter.log("Failed: fetch after"),
          )
        )

      }
    )

  ///

  def testGetAndPutPosShort(testName: String, postgresPortNumber: Int): Property =
    for {
      n <- Gen.short(Range.linear(1, Short.MaxValue)).log("n")
    } yield runIO(
      withDb[F](
        testName,
        postgresPortNumber,
        sql"""CREATE SCHEMA IF NOT EXISTS db_tools_test""",
        sql"""
        CREATE TABLE IF NOT EXISTS db_tools_test.example
        (
           id SERIAL PRIMARY KEY,
           value INT NOT NULL
        )
      """,
      ) { transactor =>

        val expected = PosShort.unsafeFrom(n)

        val expectedFetchBefore = none[PosShort]
        val expectedInsert      = 1
        val expectedFetchAfter  = n.some

        val fetch = DbTools.fetchSingleRow[F][PosShort](
          sql"""
        SELECT value
          FROM db_tools_test.example
    """
        )(transactor)

        val insert = DbTools.updateSingle[F](
          sql"""
         INSERT INTO db_tools_test.example (value) VALUES ($expected)
    """
        )(transactor)

        for {
          fetchResultBefore <- fetch.map(_ ==== expectedFetchBefore)
          insertResult      <- insert.map(_ ==== expectedInsert)
          fetchResultAfter  <- fetch.map(_ ==== expectedFetchAfter)
        } yield Result.all(
          List(
            fetchResultBefore.log("Failed: fetch before"),
            insertResult.log("Failed: insert"),
            fetchResultAfter.log("Failed: fetch after"),
          )
        )

      }
    )

  ///

  def testGetAndPutNonPosShort(testName: String, postgresPortNumber: Int): Property =
    for {
      n <- Gen.short(Range.linear(0, Short.MinValue)).log("n")
    } yield runIO(
      withDb[F](
        testName,
        postgresPortNumber,
        sql"""CREATE SCHEMA IF NOT EXISTS db_tools_test""",
        sql"""
        CREATE TABLE IF NOT EXISTS db_tools_test.example
        (
           id SERIAL PRIMARY KEY,
           value INT NOT NULL
        )
      """,
      ) { transactor =>

        val expected = NonPosShort.unsafeFrom(n)

        val expectedFetchBefore = none[NonPosShort]
        val expectedInsert      = 1
        val expectedFetchAfter  = n.some

        val fetch = DbTools.fetchSingleRow[F][NonPosShort](
          sql"""
        SELECT value
          FROM db_tools_test.example
    """
        )(transactor)

        val insert = DbTools.updateSingle[F](
          sql"""
         INSERT INTO db_tools_test.example (value) VALUES ($expected)
    """
        )(transactor)

        for {
          fetchResultBefore <- fetch.map(_ ==== expectedFetchBefore)
          insertResult      <- insert.map(_ ==== expectedInsert)
          fetchResultAfter  <- fetch.map(_ ==== expectedFetchAfter)
        } yield Result.all(
          List(
            fetchResultBefore.log("Failed: fetch before"),
            insertResult.log("Failed: insert"),
            fetchResultAfter.log("Failed: fetch after"),
          )
        )

      }
    )

  ///

  def testGetAndPutNegByte(testName: String, postgresPortNumber: Int): Property =
    for {
      n <- Gen.byte(Range.linear(-1, Byte.MinValue)).log("n")
    } yield runIO(
      withDb[F](
        testName,
        postgresPortNumber,
        sql"""CREATE SCHEMA IF NOT EXISTS db_tools_test""",
        sql"""
        CREATE TABLE IF NOT EXISTS db_tools_test.example
        (
           id SERIAL PRIMARY KEY,
           value INT NOT NULL
        )
      """,
      ) { transactor =>

        val expected = NegByte.unsafeFrom(n)

        val expectedFetchBefore = none[NegByte]
        val expectedInsert      = 1
        val expectedFetchAfter  = n.some

        val fetch = DbTools.fetchSingleRow[F][NegByte](
          sql"""
        SELECT value
          FROM db_tools_test.example
    """
        )(transactor)

        val insert = DbTools.updateSingle[F](
          sql"""
         INSERT INTO db_tools_test.example (value) VALUES ($expected)
    """
        )(transactor)

        for {
          fetchResultBefore <- fetch.map(_ ==== expectedFetchBefore)
          insertResult      <- insert.map(_ ==== expectedInsert)
          fetchResultAfter  <- fetch.map(_ ==== expectedFetchAfter)
        } yield Result.all(
          List(
            fetchResultBefore.log("Failed: fetch before"),
            insertResult.log("Failed: insert"),
            fetchResultAfter.log("Failed: fetch after"),
          )
        )

      }
    )

  ///

  def testGetAndPutNonNegByte(testName: String, postgresPortNumber: Int): Property =
    for {
      n <- Gen.byte(Range.linear(0, Byte.MaxValue)).log("n")
    } yield runIO(
      withDb[F](
        testName,
        postgresPortNumber,
        sql"""CREATE SCHEMA IF NOT EXISTS db_tools_test""",
        sql"""
        CREATE TABLE IF NOT EXISTS db_tools_test.example
        (
           id SERIAL PRIMARY KEY,
           value INT NOT NULL
        )
      """,
      ) { transactor =>

        val expected = NonNegByte.unsafeFrom(n)

        val expectedFetchBefore = none[NonNegByte]
        val expectedInsert      = 1
        val expectedFetchAfter  = n.some

        val fetch = DbTools.fetchSingleRow[F][NonNegByte](
          sql"""
            SELECT value
              FROM db_tools_test.example
        """
        )(transactor)

        val insert = DbTools.updateSingle[F](
          sql"""
             INSERT INTO db_tools_test.example (value) VALUES ($expected)
        """
        )(transactor)

        for {
          fetchResultBefore <- fetch.map(_ ==== expectedFetchBefore)
          insertResult      <- insert.map(_ ==== expectedInsert)
          fetchResultAfter  <- fetch.map(_ ==== expectedFetchAfter)
        } yield Result.all(
          List(
            fetchResultBefore.log("Failed: fetch before"),
            insertResult.log("Failed: insert"),
            fetchResultAfter.log("Failed: fetch after"),
          )
        )

      }
    )

  ///

  def testGetAndPutPosByte(testName: String, postgresPortNumber: Int): Property =
    for {
      n <- Gen.byte(Range.linear(1, Byte.MaxValue)).log("n")
    } yield runIO(
      withDb[F](
        testName,
        postgresPortNumber,
        sql"""CREATE SCHEMA IF NOT EXISTS db_tools_test""",
        sql"""
        CREATE TABLE IF NOT EXISTS db_tools_test.example
        (
           id SERIAL PRIMARY KEY,
           value INT NOT NULL
        )
      """,
      ) { transactor =>

        val expected = PosByte.unsafeFrom(n)

        val expectedFetchBefore = none[PosByte]
        val expectedInsert      = 1
        val expectedFetchAfter  = n.some

        val fetch = DbTools.fetchSingleRow[F][PosByte](
          sql"""
            SELECT value
              FROM db_tools_test.example
        """
        )(transactor)

        val insert = DbTools.updateSingle[F](
          sql"""
             INSERT INTO db_tools_test.example (value) VALUES ($expected)
        """
        )(transactor)

        for {
          fetchResultBefore <- fetch.map(_ ==== expectedFetchBefore)
          insertResult      <- insert.map(_ ==== expectedInsert)
          fetchResultAfter  <- fetch.map(_ ==== expectedFetchAfter)
        } yield Result.all(
          List(
            fetchResultBefore.log("Failed: fetch before"),
            insertResult.log("Failed: insert"),
            fetchResultAfter.log("Failed: fetch after"),
          )
        )

      }
    )

  ///

  def testGetAndPutNonPosByte(testName: String, postgresPortNumber: Int): Property =
    for {
      n <- Gen.byte(Range.linear(0, Byte.MinValue)).log("n")
    } yield runIO(
      withDb[F](
        testName,
        postgresPortNumber,
        sql"""CREATE SCHEMA IF NOT EXISTS db_tools_test""",
        sql"""
        CREATE TABLE IF NOT EXISTS db_tools_test.example
        (
           id SERIAL PRIMARY KEY,
           value INT NOT NULL
        )
      """,
      ) { transactor =>

        val expected = NonPosByte.unsafeFrom(n)

        val expectedFetchBefore = none[NonPosByte]
        val expectedInsert      = 1
        val expectedFetchAfter  = n.some

        val fetch = DbTools.fetchSingleRow[F][NonPosByte](
          sql"""
        SELECT value
          FROM db_tools_test.example
    """
        )(transactor)

        val insert = DbTools.updateSingle[F](
          sql"""
         INSERT INTO db_tools_test.example (value) VALUES ($expected)
    """
        )(transactor)

        for {
          fetchResultBefore <- fetch.map(_ ==== expectedFetchBefore)
          insertResult      <- insert.map(_ ==== expectedInsert)
          fetchResultAfter  <- fetch.map(_ ==== expectedFetchAfter)
        } yield Result.all(
          List(
            fetchResultBefore.log("Failed: fetch before"),
            insertResult.log("Failed: insert"),
            fetchResultAfter.log("Failed: fetch after"),
          )
        )

      }
    )

  ///

  def testGetAndPutNegFloat(testName: String, postgresPortNumber: Int): Property =
    for {
      n <- Gen.double(Range.linearFrac(-0.00001d, Float.MinValue)).map(_.toFloat).log("n")
    } yield runIO(
      withDb[F](
        testName,
        postgresPortNumber,
        sql"""CREATE SCHEMA IF NOT EXISTS db_tools_test""",
        sql"""
        CREATE TABLE IF NOT EXISTS db_tools_test.example
        (
           id SERIAL PRIMARY KEY,
           value DECIMAL NOT NULL
        )
      """,
      ) { transactor =>

        val expected = NegFloat.unsafeFrom(n)

        val expectedFetchBefore = none[NegFloat]
        val expectedInsert      = 1
        val expectedFetchAfter  = n.some

        val fetch = DbTools.fetchSingleRow[F][NegFloat](
          sql"""
        SELECT value
          FROM db_tools_test.example
    """
        )(transactor)

        val insert = DbTools.updateSingle[F](
          sql"""
         INSERT INTO db_tools_test.example (value) VALUES ($expected)
    """
        )(transactor)

        for {
          fetchResultBefore <- fetch.map(_ ==== expectedFetchBefore)
          insertResult      <- insert.map(_ ==== expectedInsert)
          fetchResultAfter  <- fetch.map(_ ==== expectedFetchAfter)
        } yield Result.all(
          List(
            fetchResultBefore.log("Failed: fetch before"),
            insertResult.log("Failed: insert"),
            fetchResultAfter.log("Failed: fetch after"),
          )
        )

      }
    )

  ///

  def testGetAndPutNonNegFloat(testName: String, postgresPortNumber: Int): Property =
    for {
      n <- Gen.double(Range.linearFrac(0d, Float.MaxValue)).map(_.toFloat).log("n")
    } yield runIO(
      withDb[F](
        testName,
        postgresPortNumber,
        sql"""CREATE SCHEMA IF NOT EXISTS db_tools_test""",
        sql"""
        CREATE TABLE IF NOT EXISTS db_tools_test.example
        (
           id SERIAL PRIMARY KEY,
           value DECIMAL NOT NULL
        )
      """,
      ) { transactor =>

        val expected = NonNegFloat.unsafeFrom(n)

        val expectedFetchBefore = none[NonNegFloat]
        val expectedInsert      = 1
        val expectedFetchAfter  = n.some

        val fetch = DbTools.fetchSingleRow[F][NonNegFloat](
          sql"""
        SELECT value
          FROM db_tools_test.example
    """
        )(transactor)

        val insert = DbTools.updateSingle[F](
          sql"""
         INSERT INTO db_tools_test.example (value) VALUES ($expected)
    """
        )(transactor)

        for {
          fetchResultBefore <- fetch.map(_ ==== expectedFetchBefore)
          insertResult      <- insert.map(_ ==== expectedInsert)
          fetchResultAfter  <- fetch.map(_ ==== expectedFetchAfter)
        } yield Result.all(
          List(
            fetchResultBefore.log("Failed: fetch before"),
            insertResult.log("Failed: insert"),
            fetchResultAfter.log("Failed: fetch after"),
          )
        )

      }
    )

  ///

  def testGetAndPutPosFloat(testName: String, postgresPortNumber: Int): Property =
    for {
      n <- Gen.double(Range.linearFrac(0.0001d, Float.MaxValue)).map(_.toFloat).log("n")
    } yield runIO(
      withDb[F](
        testName,
        postgresPortNumber,
        sql"""CREATE SCHEMA IF NOT EXISTS db_tools_test""",
        sql"""
        CREATE TABLE IF NOT EXISTS db_tools_test.example
        (
           id SERIAL PRIMARY KEY,
           value DECIMAL NOT NULL
        )
      """,
      ) { transactor =>

        val expected = PosFloat.unsafeFrom(n)

        val expectedFetchBefore = none[PosFloat]
        val expectedInsert      = 1
        val expectedFetchAfter  = n.some

        val fetch = DbTools.fetchSingleRow[F][PosFloat](
          sql"""
        SELECT value
          FROM db_tools_test.example
    """
        )(transactor)

        val insert = DbTools.updateSingle[F](
          sql"""
         INSERT INTO db_tools_test.example (value) VALUES ($expected)
    """
        )(transactor)

        for {
          fetchResultBefore <- fetch.map(_ ==== expectedFetchBefore)
          insertResult      <- insert.map(_ ==== expectedInsert)
          fetchResultAfter  <- fetch.map(_ ==== expectedFetchAfter)
        } yield Result.all(
          List(
            fetchResultBefore.log("Failed: fetch before"),
            insertResult.log("Failed: insert"),
            fetchResultAfter.log("Failed: fetch after"),
          )
        )

      }
    )

  ///

  def testGetAndPutNonPosFloat(testName: String, postgresPortNumber: Int): Property =
    for {
      n <- Gen.double(Range.linearFrac(0d, Float.MinValue)).map(_.toFloat).log("n")
    } yield runIO(
      withDb[F](
        testName,
        postgresPortNumber,
        sql"""CREATE SCHEMA IF NOT EXISTS db_tools_test""",
        sql"""
        CREATE TABLE IF NOT EXISTS db_tools_test.example
        (
           id SERIAL PRIMARY KEY,
           value DECIMAL NOT NULL
        )
      """,
      ) { transactor =>

        val expected = NonPosFloat.unsafeFrom(n)

        val expectedFetchBefore = none[NonPosFloat]
        val expectedInsert      = 1
        val expectedFetchAfter  = n.some

        val fetch = DbTools.fetchSingleRow[F][NonPosFloat](
          sql"""
        SELECT value
          FROM db_tools_test.example
    """
        )(transactor)

        val insert = DbTools.updateSingle[F](
          sql"""
         INSERT INTO db_tools_test.example (value) VALUES ($expected)
    """
        )(transactor)

        for {
          fetchResultBefore <- fetch.map(_ ==== expectedFetchBefore)
          insertResult      <- insert.map(_ ==== expectedInsert)
          fetchResultAfter  <- fetch.map(_ ==== expectedFetchAfter)
        } yield Result.all(
          List(
            fetchResultBefore.log("Failed: fetch before"),
            insertResult.log("Failed: insert"),
            fetchResultAfter.log("Failed: fetch after"),
          )
        )

      }
    )

  ///

  def testGetAndPutNegDouble(testName: String, postgresPortNumber: Int): Property =
    for {
      n <- Gen.double(Range.linearFrac(-0.000001d, Double.MinValue)).log("n")
    } yield runIO(
      withDb[F](
        testName,
        postgresPortNumber,
        sql"""CREATE SCHEMA IF NOT EXISTS db_tools_test""",
        sql"""
        CREATE TABLE IF NOT EXISTS db_tools_test.example
        (
           id SERIAL PRIMARY KEY,
           value DECIMAL NOT NULL
        )
      """,
      ) { transactor =>

        val expected = NegDouble.unsafeFrom(n)

        val expectedFetchBefore = none[NegDouble]
        val expectedInsert      = 1
        val expectedFetchAfter  = n.some

        val fetch = DbTools.fetchSingleRow[F][NegDouble](
          sql"""
            SELECT value
              FROM db_tools_test.example
        """
        )(transactor)

        val insert = DbTools.updateSingle[F](
          sql"""
             INSERT INTO db_tools_test.example (value) VALUES ($expected)
        """
        )(transactor)

        for {
          fetchResultBefore <- fetch.map(_ ==== expectedFetchBefore)
          insertResult      <- insert.map(_ ==== expectedInsert)
          fetchResultAfter  <- fetch.map(_ ==== expectedFetchAfter)
        } yield Result.all(
          List(
            fetchResultBefore.log("Failed: fetch before"),
            insertResult.log("Failed: insert"),
            fetchResultAfter.log("Failed: fetch after"),
          )
        )

      }
    )

  ///

  def testGetAndPutNonNegDouble(testName: String, postgresPortNumber: Int): Property =
    for {
      n <- Gen.double(Range.linearFrac(0d, Double.MaxValue)).log("n")
    } yield runIO(
      withDb[F](
        testName,
        postgresPortNumber,
        sql"""CREATE SCHEMA IF NOT EXISTS db_tools_test""",
        sql"""
        CREATE TABLE IF NOT EXISTS db_tools_test.example
        (
           id SERIAL PRIMARY KEY,
           value DECIMAL NOT NULL
        )
      """,
      ) { transactor =>

        val expected = NonNegDouble.unsafeFrom(n)

        val expectedFetchBefore = none[NonNegDouble]
        val expectedInsert      = 1
        val expectedFetchAfter  = n.some

        val fetch = DbTools.fetchSingleRow[F][NonNegDouble](
          sql"""
        SELECT value
          FROM db_tools_test.example
    """
        )(transactor)

        val insert = DbTools.updateSingle[F](
          sql"""
         INSERT INTO db_tools_test.example (value) VALUES ($expected)
    """
        )(transactor)

        for {
          fetchResultBefore <- fetch.map(_ ==== expectedFetchBefore)
          insertResult      <- insert.map(_ ==== expectedInsert)
          fetchResultAfter  <- fetch.map(_ ==== expectedFetchAfter)
        } yield Result.all(
          List(
            fetchResultBefore.log("Failed: fetch before"),
            insertResult.log("Failed: insert"),
            fetchResultAfter.log("Failed: fetch after"),
          )
        )

      }
    )

  ///

  def testGetAndPutPosDouble(testName: String, postgresPortNumber: Int): Property =
    for {
      n <- Gen.double(Range.linearFrac(0.000001d, Double.MaxValue)).log("n")
    } yield runIO(
      withDb[F](
        testName,
        postgresPortNumber,
        sql"""CREATE SCHEMA IF NOT EXISTS db_tools_test""",
        sql"""
        CREATE TABLE IF NOT EXISTS db_tools_test.example
        (
           id SERIAL PRIMARY KEY,
           value DECIMAL NOT NULL
        )
      """,
      ) { transactor =>

        val expected = PosDouble.unsafeFrom(n)

        val expectedFetchBefore = none[PosDouble]
        val expectedInsert      = 1
        val expectedFetchAfter  = n.some

        val fetch = DbTools.fetchSingleRow[F][PosDouble](
          sql"""
        SELECT value
          FROM db_tools_test.example
    """
        )(transactor)

        val insert = DbTools.updateSingle[F](
          sql"""
         INSERT INTO db_tools_test.example (value) VALUES ($expected)
    """
        )(transactor)

        for {
          fetchResultBefore <- fetch.map(_ ==== expectedFetchBefore)
          insertResult      <- insert.map(_ ==== expectedInsert)
          fetchResultAfter  <- fetch.map(_ ==== expectedFetchAfter)
        } yield Result.all(
          List(
            fetchResultBefore.log("Failed: fetch before"),
            insertResult.log("Failed: insert"),
            fetchResultAfter.log("Failed: fetch after"),
          )
        )

      }
    )

  ///

  def testGetAndPutNonPosDouble(testName: String, postgresPortNumber: Int): Property =
    for {
      n <- Gen.double(Range.linearFrac(0d, Double.MinValue)).log("n")
    } yield runIO(
      withDb[F](
        testName,
        postgresPortNumber,
        sql"""CREATE SCHEMA IF NOT EXISTS db_tools_test""",
        sql"""
        CREATE TABLE IF NOT EXISTS db_tools_test.example
        (
           id SERIAL PRIMARY KEY,
           value DECIMAL NOT NULL
        )
      """,
      ) { transactor =>

        val expected = NonPosDouble.unsafeFrom(n)

        val expectedFetchBefore = none[NonPosDouble]
        val expectedInsert      = 1
        val expectedFetchAfter  = n.some

        val fetch = DbTools.fetchSingleRow[F][NonPosDouble](
          sql"""
        SELECT value
          FROM db_tools_test.example
    """
        )(transactor)

        val insert = DbTools.updateSingle[F](
          sql"""
         INSERT INTO db_tools_test.example (value) VALUES ($expected)
    """
        )(transactor)

        for {
          fetchResultBefore <- fetch.map(_ ==== expectedFetchBefore)
          insertResult      <- insert.map(_ ==== expectedInsert)
          fetchResultAfter  <- fetch.map(_ ==== expectedFetchAfter)
        } yield Result.all(
          List(
            fetchResultBefore.log("Failed: fetch before"),
            insertResult.log("Failed: insert"),
            fetchResultAfter.log("Failed: fetch after"),
          )
        )

      }
    )

  ///

  def testGetAndPutNegBigInt(testName: String, postgresPortNumber: Int): Property =
    for {
      n <- Gen.long(Range.linear(-1L, Long.MinValue)).map(BigInt(_)).log("n")
    } yield runIO(
      withDb[F](
        testName,
        postgresPortNumber,
        sql"""CREATE SCHEMA IF NOT EXISTS db_tools_test""",
        sql"""
        CREATE TABLE IF NOT EXISTS db_tools_test.example
        (
           id SERIAL PRIMARY KEY,
           value BIGINT NOT NULL
        )
      """,
      ) { transactor =>

        val expected = NegBigInt.unsafeFrom(n)

        val expectedFetchBefore = none[NegBigInt]
        val expectedInsert      = 1
        val expectedFetchAfter  = n.some

        val fetch = DbTools.fetchSingleRow[F][NegBigInt](
          sql"""
            SELECT value
              FROM db_tools_test.example
        """
        )(transactor)

        val insert = DbTools.updateSingle[F](
          sql"""
             INSERT INTO db_tools_test.example (value) VALUES ($expected)
        """
        )(transactor)

        for {
          fetchResultBefore <- fetch.map(_ ==== expectedFetchBefore)
          insertResult      <- insert.map(_ ==== expectedInsert)
          fetchResultAfter  <- fetch.map(_ ==== expectedFetchAfter)
        } yield Result.all(
          List(
            fetchResultBefore.log("Failed: fetch before"),
            insertResult.log("Failed: insert"),
            fetchResultAfter.log("Failed: fetch after"),
          )
        )

      }
    )

  ///

  def testGetAndPutNonNegBigInt(testName: String, postgresPortNumber: Int): Property =
    for {
      n <- Gen.long(Range.linear(0L, Long.MaxValue)).map(BigInt(_)).log("n")
    } yield runIO(
      withDb[F](
        testName,
        postgresPortNumber,
        sql"""CREATE SCHEMA IF NOT EXISTS db_tools_test""",
        sql"""
        CREATE TABLE IF NOT EXISTS db_tools_test.example
        (
           id SERIAL PRIMARY KEY,
           value BIGINT NOT NULL
        )
      """,
      ) { transactor =>

        val expected = NonNegBigInt.unsafeFrom(n)

        val expectedFetchBefore = none[NonNegBigInt]
        val expectedInsert      = 1
        val expectedFetchAfter  = n.some

        val fetch = DbTools.fetchSingleRow[F][NonNegBigInt](
          sql"""
        SELECT value
          FROM db_tools_test.example
    """
        )(transactor)

        val insert = DbTools.updateSingle[F](
          sql"""
         INSERT INTO db_tools_test.example (value) VALUES ($expected)
    """
        )(transactor)

        for {
          fetchResultBefore <- fetch.map(_ ==== expectedFetchBefore)
          insertResult      <- insert.map(_ ==== expectedInsert)
          fetchResultAfter  <- fetch.map(_ ==== expectedFetchAfter)
        } yield Result.all(
          List(
            fetchResultBefore.log("Failed: fetch before"),
            insertResult.log("Failed: insert"),
            fetchResultAfter.log("Failed: fetch after"),
          )
        )

      }
    )

  ///

  def testGetAndPutPosBigInt(testName: String, postgresPortNumber: Int): Property =
    for {
      n <- Gen.long(Range.linear(1L, Long.MaxValue)).map(BigInt(_)).log("n")
    } yield runIO(
      withDb[F](
        testName,
        postgresPortNumber,
        sql"""CREATE SCHEMA IF NOT EXISTS db_tools_test""",
        sql"""
        CREATE TABLE IF NOT EXISTS db_tools_test.example
        (
           id SERIAL PRIMARY KEY,
           value BIGINT NOT NULL
        )
      """,
      ) { transactor =>

        val expected = PosBigInt.unsafeFrom(n)

        val expectedFetchBefore = none[PosBigInt]
        val expectedInsert      = 1
        val expectedFetchAfter  = n.some

        val fetch = DbTools.fetchSingleRow[F][PosBigInt](
          sql"""
        SELECT value
          FROM db_tools_test.example
    """
        )(transactor)

        val insert = DbTools.updateSingle[F](
          sql"""
         INSERT INTO db_tools_test.example (value) VALUES ($expected)
    """
        )(transactor)

        for {
          fetchResultBefore <- fetch.map(_ ==== expectedFetchBefore)
          insertResult      <- insert.map(_ ==== expectedInsert)
          fetchResultAfter  <- fetch.map(_ ==== expectedFetchAfter)
        } yield Result.all(
          List(
            fetchResultBefore.log("Failed: fetch before"),
            insertResult.log("Failed: insert"),
            fetchResultAfter.log("Failed: fetch after"),
          )
        )

      }
    )

  ///

  def testGetAndPutNonPosBigInt(testName: String, postgresPortNumber: Int): Property =
    for {
      n <- Gen.long(Range.linear(0L, Long.MinValue)).map(BigInt(_)).log("n")
    } yield runIO(
      withDb[F](
        testName,
        postgresPortNumber,
        sql"""CREATE SCHEMA IF NOT EXISTS db_tools_test""",
        sql"""
        CREATE TABLE IF NOT EXISTS db_tools_test.example
        (
           id SERIAL PRIMARY KEY,
           value BIGINT NOT NULL
        )
      """,
      ) { transactor =>

        val expected = NonPosBigInt.unsafeFrom(n)

        val expectedFetchBefore = none[NonPosBigInt]
        val expectedInsert      = 1
        val expectedFetchAfter  = n.some

        val fetch = DbTools.fetchSingleRow[F][NonPosBigInt](
          sql"""
        SELECT value
          FROM db_tools_test.example
    """
        )(transactor)

        val insert = DbTools.updateSingle[F](
          sql"""
         INSERT INTO db_tools_test.example (value) VALUES ($expected)
    """
        )(transactor)

        for {
          fetchResultBefore <- fetch.map(_ ==== expectedFetchBefore)
          insertResult      <- insert.map(_ ==== expectedInsert)
          fetchResultAfter  <- fetch.map(_ ==== expectedFetchAfter)
        } yield Result.all(
          List(
            fetchResultBefore.log("Failed: fetch before"),
            insertResult.log("Failed: insert"),
            fetchResultAfter.log("Failed: fetch after"),
          )
        )

      }
    )

  ///

  def testGetAndPutNegBigDecimal(testName: String, postgresPortNumber: Int): Property =
    for {
      n <- Gen.double(Range.linearFrac(-0.0000001d, Double.MinValue)).map(BigDecimal(_)).log("n")
    } yield runIO(
      withDb[F](
        testName,
        postgresPortNumber,
        sql"""CREATE SCHEMA IF NOT EXISTS db_tools_test""",
        sql"""
        CREATE TABLE IF NOT EXISTS db_tools_test.example
        (
           id SERIAL PRIMARY KEY,
           value DECIMAL NOT NULL
        )
      """,
      ) { transactor =>

        val expected = NegBigDecimal.unsafeFrom(n)

        val expectedFetchBefore = none[NegBigDecimal]
        val expectedInsert      = 1
        val expectedFetchAfter  = n.some

        val fetch = DbTools.fetchSingleRow[F][NegBigDecimal](
          sql"""
        SELECT value
          FROM db_tools_test.example
    """
        )(transactor)

        val insert = DbTools.updateSingle[F](
          sql"""
         INSERT INTO db_tools_test.example (value) VALUES ($expected)
    """
        )(transactor)

        for {
          fetchResultBefore <- fetch.map(_ ==== expectedFetchBefore)
          insertResult      <- insert.map(_ ==== expectedInsert)
          fetchResultAfter  <- fetch.map(_ ==== expectedFetchAfter)
        } yield Result.all(
          List(
            fetchResultBefore.log("Failed: fetch before"),
            insertResult.log("Failed: insert"),
            fetchResultAfter.log("Failed: fetch after"),
          )
        )

      }
    )

  ///

  def testGetAndPutNonNegBigDecimal(testName: String, postgresPortNumber: Int): Property =
    for {
      n <- Gen.double(Range.linearFrac(0d, Double.MaxValue)).map(BigDecimal(_)).log("n")
    } yield runIO(
      withDb[F](
        testName,
        postgresPortNumber,
        sql"""CREATE SCHEMA IF NOT EXISTS db_tools_test""",
        sql"""
        CREATE TABLE IF NOT EXISTS db_tools_test.example
        (
           id SERIAL PRIMARY KEY,
           value DECIMAL NOT NULL
        )
      """,
      ) { transactor =>

        val expected = NonNegBigDecimal.unsafeFrom(n)

        val expectedFetchBefore = none[NonNegBigDecimal]
        val expectedInsert      = 1
        val expectedFetchAfter  = n.some

        val fetch = DbTools.fetchSingleRow[F][NonNegBigDecimal](
          sql"""
        SELECT value
          FROM db_tools_test.example
    """
        )(transactor)

        val insert = DbTools.updateSingle[F](
          sql"""
         INSERT INTO db_tools_test.example (value) VALUES ($expected)
    """
        )(transactor)

        for {
          fetchResultBefore <- fetch.map(_ ==== expectedFetchBefore)
          insertResult      <- insert.map(_ ==== expectedInsert)
          fetchResultAfter  <- fetch.map(_ ==== expectedFetchAfter)
        } yield Result.all(
          List(
            fetchResultBefore.log("Failed: fetch before"),
            insertResult.log("Failed: insert"),
            fetchResultAfter.log("Failed: fetch after"),
          )
        )

      }
    )

  ///

  def testGetAndPutPosBigDecimal(testName: String, postgresPortNumber: Int): Property =
    for {
      n <- Gen.double(Range.linearFrac(0.0000001d, Double.MaxValue)).map(BigDecimal(_)).log("n")
    } yield runIO(
      withDb[F](
        testName,
        postgresPortNumber,
        sql"""CREATE SCHEMA IF NOT EXISTS db_tools_test""",
        sql"""
        CREATE TABLE IF NOT EXISTS db_tools_test.example
        (
           id SERIAL PRIMARY KEY,
           value DECIMAL NOT NULL
        )
      """,
      ) { transactor =>

        val expected = PosBigDecimal.unsafeFrom(n)

        val expectedFetchBefore = none[PosBigDecimal]
        val expectedInsert      = 1
        val expectedFetchAfter  = n.some

        val fetch = DbTools.fetchSingleRow[F][PosBigDecimal](
          sql"""
        SELECT value
          FROM db_tools_test.example
    """
        )(transactor)

        val insert = DbTools.updateSingle[F](
          sql"""
         INSERT INTO db_tools_test.example (value) VALUES ($expected)
    """
        )(transactor)

        for {
          fetchResultBefore <- fetch.map(_ ==== expectedFetchBefore)
          insertResult      <- insert.map(_ ==== expectedInsert)
          fetchResultAfter  <- fetch.map(_ ==== expectedFetchAfter)
        } yield Result.all(
          List(
            fetchResultBefore.log("Failed: fetch before"),
            insertResult.log("Failed: insert"),
            fetchResultAfter.log("Failed: fetch after"),
          )
        )

      }
    )

  ///

  def testGetAndPutNonPosBigDecimal(testName: String, postgresPortNumber: Int): Property =
    for {
      n <- Gen.double(Range.linearFrac(0d, Double.MinValue)).map(BigDecimal(_)).log("n")
    } yield runIO(
      withDb[F](
        testName,
        postgresPortNumber,
        sql"""CREATE SCHEMA IF NOT EXISTS db_tools_test""",
        sql"""
        CREATE TABLE IF NOT EXISTS db_tools_test.example
        (
           id SERIAL PRIMARY KEY,
           value DECIMAL NOT NULL
        )
      """,
      ) { transactor =>

        val expected = NonPosBigDecimal.unsafeFrom(n)

        val expectedFetchBefore = none[NonPosBigDecimal]
        val expectedInsert      = 1
        val expectedFetchAfter  = n.some

        val fetch = DbTools.fetchSingleRow[F][NonPosBigDecimal](
          sql"""
        SELECT value
          FROM db_tools_test.example
    """
        )(transactor)

        val insert = DbTools.updateSingle[F](
          sql"""
         INSERT INTO db_tools_test.example (value) VALUES ($expected)
    """
        )(transactor)

        for {
          fetchResultBefore <- fetch.map(_ ==== expectedFetchBefore)
          insertResult      <- insert.map(_ ==== expectedInsert)
          fetchResultAfter  <- fetch.map(_ ==== expectedFetchAfter)
        } yield Result.all(
          List(
            fetchResultBefore.log("Failed: fetch before"),
            insertResult.log("Failed: insert"),
            fetchResultAfter.log("Failed: fetch after"),
          )
        )

      }
    )

  ///

}
object numericSpec extends Properties, numericSpec {

  override protected object numericTypeClasses extends refined4s.modules.doobie.derivation.types.numeric

  override def tests: List[Test] = allTests

}
