package refined4s.modules.doobie.derivation.types

import cats.effect.IO
import cats.syntax.all.*
import doobie.syntax.all.*
import extras.doobie.RunWithDb
import extras.doobie.ce3.DbTools
import extras.hedgehog.ce3.CatsEffectRunner
import hedgehog.*
import hedgehog.runner.*
import refined4s.types.time.*
import refined4s.types.TimeGens

import java.util.concurrent.atomic.AtomicInteger

/** @author Kevin Lee
  * @since 2025-09-03
  */
trait timeSpec extends CatsEffectRunner, RunWithDb {

  import effectie.instances.ce3.fx.ioFx

  type F[A] = IO[A]
  val F: IO.type = IO

  val postgresPortNumber: AtomicInteger = AtomicInteger(6832)

  protected val timeTypeClasses: refined4s.modules.doobie.derivation.types.time
  import timeTypeClasses.given

  def allTests: List[Test] = List(
    propertyWithDb("test Get[Month] and Put[Month]", postgresPortNumber.getAndIncrement(), testGetAndPutMonth),
    propertyWithDb("test Get[Day] and Put[Day]", postgresPortNumber.getAndIncrement(), testGetAndPutDay),
    propertyWithDb("test Get[Hour] and Put[Hour]", postgresPortNumber.getAndIncrement(), testGetAndPutHour),
    propertyWithDb("test Get[Minute] and Put[Minute]", postgresPortNumber.getAndIncrement(), testGetAndPutMinute),
    propertyWithDb("test Get[Second] and Put[Second]", postgresPortNumber.getAndIncrement(), testGetAndPutSecond),
    propertyWithDb("test Get[Millis] and Put[Millis]", postgresPortNumber.getAndIncrement(), testGetAndPutMillis),
  )

  @SuppressWarnings(Array("org.wartremover.warts.ToString"))
  def testGetAndPutMonth(testName: String, postgresPortNumber: Int): Property =
    for {
      portNumber <- TimeGens.genMonthInt.log("portNumber")
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

        val expected = Month.unsafeFrom(portNumber)

        val expectedFetchBefore = none[Month]
        val expectedInsert      = 1
        val expectedFetchAfter  = portNumber.some

        val fetch = DbTools.fetchSingleRow[F][Month](
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

  @SuppressWarnings(Array("org.wartremover.warts.ToString"))
  def testGetAndPutDay(testName: String, postgresPortNumber: Int): Property =
    for {
      portNumber <- TimeGens.genDayInt.log("portNumber")
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

        val expected = Day.unsafeFrom(portNumber)

        val expectedFetchBefore = none[Day]
        val expectedInsert      = 1
        val expectedFetchAfter  = portNumber.some

        val fetch = DbTools.fetchSingleRow[F][Day](
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

  @SuppressWarnings(Array("org.wartremover.warts.ToString"))
  def testGetAndPutHour(testName: String, postgresPortNumber: Int): Property =
    for {
      portNumber <- TimeGens.genHourInt.log("portNumber")
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

        val expected = Hour.unsafeFrom(portNumber)

        val expectedFetchBefore = none[Hour]
        val expectedInsert      = 1
        val expectedFetchAfter  = portNumber.some

        val fetch = DbTools.fetchSingleRow[F][Hour](
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

  @SuppressWarnings(Array("org.wartremover.warts.ToString"))
  def testGetAndPutMinute(testName: String, postgresPortNumber: Int): Property =
    for {
      portNumber <- TimeGens.genMinuteInt.log("portNumber")
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

        val expected = Minute.unsafeFrom(portNumber)

        val expectedFetchBefore = none[Minute]
        val expectedInsert      = 1
        val expectedFetchAfter  = portNumber.some

        val fetch = DbTools.fetchSingleRow[F][Minute](
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

  @SuppressWarnings(Array("org.wartremover.warts.ToString"))
  def testGetAndPutSecond(testName: String, postgresPortNumber: Int): Property =
    for {
      portNumber <- TimeGens.genSecondInt.log("portNumber")
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

        val expected = Second.unsafeFrom(portNumber)

        val expectedFetchBefore = none[Second]
        val expectedInsert      = 1
        val expectedFetchAfter  = portNumber.some

        val fetch = DbTools.fetchSingleRow[F][Second](
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

  @SuppressWarnings(Array("org.wartremover.warts.ToString"))
  def testGetAndPutMillis(testName: String, postgresPortNumber: Int): Property =
    for {
      portNumber <- TimeGens.genMillisInt.log("portNumber")
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

        val expected = Millis.unsafeFrom(portNumber)

        val expectedFetchBefore = none[Millis]
        val expectedInsert      = 1
        val expectedFetchAfter  = portNumber.some

        val fetch = DbTools.fetchSingleRow[F][Millis](
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

}
object timeSpec extends Properties, timeSpec {

  override protected object timeTypeClasses extends refined4s.modules.doobie.derivation.types.time

  override def tests: List[Test] = allTests

}
