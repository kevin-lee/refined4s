package refined4s.modules.doobie.derivation.types

import cats.effect.IO
import cats.syntax.all.*
import doobie.syntax.all.*
import extras.doobie.RunWithDb
import extras.doobie.ce3.DbTools
import extras.hedgehog.ce3.CatsEffectRunner
import hedgehog.*
import hedgehog.runner.*
import refined4s.types.all.*
import refined4s.types.networkGens

import java.util.concurrent.atomic.AtomicInteger

/** @author Kevin Lee
  * @since 2025-09-03
  */
trait networkSpec extends CatsEffectRunner, RunWithDb {

  import effectie.instances.ce3.fx.ioFx

  type F[A] = IO[A]
  val F: IO.type = IO

  val postgresPortNumber: AtomicInteger = AtomicInteger(6732)

  protected val networkTypeClasses: refined4s.modules.doobie.derivation.types.network
  import networkTypeClasses.given

  def allTests: List[Test] = List(
    //
    propertyWithDb("test Get[Uri] and Put[Uri]", postgresPortNumber.getAndIncrement(), testGetAndPutUri),

    //
    propertyWithDb("test Get[Url] and Put[Url]", postgresPortNumber.getAndIncrement(), testGetAndPutUrl),

    //
    propertyWithDb("test Get[PortNumber] and Put[PortNumber]", postgresPortNumber.getAndIncrement(), testGetAndPutPortNumber),
    //
    propertyWithDb(
      "test Get[SystemPortNumber] and Put[SystemPortNumber]",
      postgresPortNumber.getAndIncrement(),
      testGetAndPutSystemPortNumber,
    ),
    //
    propertyWithDb(
      "test Get[NonSystemPortNumber] and Put[NonSystemPortNumber]",
      postgresPortNumber.getAndIncrement(),
      testGetAndPutNonSystemPortNumber,
    ),
    //
    propertyWithDb("test Get[UserPortNumber] and Put[UserPortNumber]", postgresPortNumber.getAndIncrement(), testGetAndPutUserPortNumber),
    //
    propertyWithDb(
      "test Get[DynamicPortNumber] and Put[DynamicPortNumber]",
      postgresPortNumber.getAndIncrement(),
      testGetAndPutDynamicPortNumber,
    ),
  )

  ///
  /* network.Uri */

  @SuppressWarnings(Array("org.wartremover.warts.ToString"))
  def testGetAndPutUri(testName: String, postgresPortNumber: Int): Property =
    for {
      uriString <- networkGens.genUriString.log("uri")
    } yield runIO(
      withDb[F](
        testName,
        postgresPortNumber,
        sql"""CREATE SCHEMA IF NOT EXISTS db_tools_test""",
        sql"""
        CREATE TABLE IF NOT EXISTS db_tools_test.example
        (
           id SERIAL PRIMARY KEY,
           value TEXT NOT NULL
        )
      """,
      ) { transactor =>

        val uri = Uri.unsafeFrom(uriString)

        val expectedFetchBefore = none[Uri]
        val expectedInsert      = 1
        val expectedFetchAfter  = uri.some

        val fetch = DbTools.fetchSingleRow[F][Uri](
          sql"""
            SELECT value
              FROM db_tools_test.example
        """
        )(transactor)

        val insert = DbTools.updateSingle[F](
          sql"""
             INSERT INTO db_tools_test.example (value) VALUES ($uri)
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

  /* network.Url */

  @SuppressWarnings(Array("org.wartremover.warts.ToString"))
  def testGetAndPutUrl(testName: String, postgresPortNumber: Int): Property =
    for {
      urlString <- networkGens.genUrlString.log("url")
    } yield runIO(
      withDb[F](
        testName,
        postgresPortNumber,
        sql"""CREATE SCHEMA IF NOT EXISTS db_tools_test""",
        sql"""
        CREATE TABLE IF NOT EXISTS db_tools_test.example
        (
           id SERIAL PRIMARY KEY,
           value TEXT NOT NULL
        )
      """,
      ) { transactor =>

        val url = Url.unsafeFrom(urlString)

        val expectedFetchBefore = none[Url]
        val expectedInsert      = 1
        val expectedFetchAfter  = url.some

        val fetch = DbTools.fetchSingleRow[F][Url](
          sql"""
            SELECT value
              FROM db_tools_test.example
        """
        )(transactor)

        val insert = DbTools.updateSingle[F](
          sql"""
             INSERT INTO db_tools_test.example (value) VALUES ($url)
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

  /* network ports */

  @SuppressWarnings(Array("org.wartremover.warts.ToString"))
  def testGetAndPutPortNumber(testName: String, postgresPortNumber: Int): Property =
    for {
      portNumber <- networkGens.genPortNumberInt.log("portNumber")
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

        val expected = PortNumber.unsafeFrom(portNumber)

        val expectedFetchBefore = none[PortNumber]
        val expectedInsert      = 1
        val expectedFetchAfter  = portNumber.some

        val fetch = DbTools.fetchSingleRow[F][PortNumber](
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

  def testGetAndPutSystemPortNumber(testName: String, postgresPortNumber: Int): Property =
    for {
      systemPortNumber <- networkGens.genSystemPortNumberInt.log("systemPortNumber")
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

        val expected = SystemPortNumber.unsafeFrom(systemPortNumber)

        val expectedFetchBefore = none[SystemPortNumber]
        val expectedInsert      = 1
        val expectedFetchAfter  = systemPortNumber.some

        val fetch = DbTools.fetchSingleRow[F][SystemPortNumber](
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

  def testGetAndPutNonSystemPortNumber(testName: String, postgresPortNumber: Int): Property =
    for {
      nonSystemPortNumber <- networkGens.genNonSystemPortNumberInt.log("nonSystemPortNumber")
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

        val expected = NonSystemPortNumber.unsafeFrom(nonSystemPortNumber)

        val expectedFetchBefore = none[NonSystemPortNumber]
        val expectedInsert      = 1
        val expectedFetchAfter  = nonSystemPortNumber.some

        val fetch = DbTools.fetchSingleRow[F][NonSystemPortNumber](
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

  def testGetAndPutUserPortNumber(testName: String, postgresPortNumber: Int): Property =
    for {
      userPortNumber <- networkGens.genUserPortNumberInt.log("userPortNumber")
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

        val expected = UserPortNumber.unsafeFrom(userPortNumber)

        val expectedFetchBefore = none[UserPortNumber]
        val expectedInsert      = 1
        val expectedFetchAfter  = userPortNumber.some

        val fetch = DbTools.fetchSingleRow[F][UserPortNumber](
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

  def testGetAndPutDynamicPortNumber(testName: String, postgresPortNumber: Int): Property =
    for {
      dynamicPortNumber <- networkGens.genDynamicPortNumberInt.log("dynamicPortNumber")
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

        val expected = DynamicPortNumber.unsafeFrom(dynamicPortNumber)

        val expectedFetchBefore = none[DynamicPortNumber]
        val expectedInsert      = 1
        val expectedFetchAfter  = dynamicPortNumber.some

        val fetch = DbTools.fetchSingleRow[F][DynamicPortNumber](
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
object networkSpec extends Properties, networkSpec {

  override protected object networkTypeClasses extends refined4s.modules.doobie.derivation.types.network

  override def tests: List[Test] = allTests

}
