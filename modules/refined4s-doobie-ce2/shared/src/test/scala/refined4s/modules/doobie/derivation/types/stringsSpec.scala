package refined4s.modules.doobie.derivation.types

import cats.effect.ContextShift
import cats.syntax.all.*
import doobie.syntax.all.*
import extras.core.syntax.all.*
import extras.doobie.RunWithDb
import extras.doobie.ce2.DbTools
import extras.runner.ce2.RunSyncCe2
import hedgehog.*
import hedgehog.runner.*
import refined4s.types.strings.*

import java.nio.charset.StandardCharsets
import java.util.UUID
import java.util.concurrent.atomic.AtomicInteger

/** @author Kevin Lee
  * @since 2025-09-02
  */
trait stringsSpec extends RunSyncCe2, RunWithDb {

  import effectie.instances.ce2.fx.ioFx

  @SuppressWarnings(Array("org.wartremover.warts.GlobalExecutionContext"))
  implicit val cs: ContextShift[F] = F.contextShift(scala.concurrent.ExecutionContext.global)

  val postgresPortNumber: AtomicInteger = AtomicInteger(5632)

  protected val stringsTypeClasses: refined4s.modules.doobie.derivation.types.strings
  import stringsTypeClasses.given

  def allTests: List[Test] = List(
    propertyWithDb("test Get[NonEmptyString] and Put[NonEmptyString]", postgresPortNumber.getAndIncrement(), testGetAndPutNonEmptyString),
    //
    propertyWithDb("test Get[NonBlankString] and Put[NonBlankString]", postgresPortNumber.getAndIncrement(), testGetAndPutNonBlankString),
    //
    propertyWithDb("test Get[Uuid] and Put[Uuid]", postgresPortNumber.getAndIncrement(), testGetAndPutUuid),
  )

  def testGetAndPutNonEmptyString(testName: String, postgresPortNumber: Int): Property =
    for {
      s <- Gen.string(Gen.alphaNum, Range.linear(1, 10)).log("s")
    } yield withDb[F](
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

      val expected = NonEmptyString.unsafeFrom(s)

      val expectedFetchBefore = none[NonEmptyString]
      val expectedInsert      = 1
      val expectedFetchAfter  = s.some

      val fetch = DbTools.fetchSingleRow[F][NonEmptyString](
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

  ///

  def testGetAndPutNonBlankString(testName: String, postgresPortNumber: Int): Property =
    for {
      nonWhitespaceString <- Gen
                               .string(hedgehog.extra.Gens.genNonWhitespaceChar, Range.linear(1, 10))
                               .map(s => if s === "\u0000" then "blah" else s)
                               .map(s => new String(s.getBytes(StandardCharsets.UTF_8), StandardCharsets.UTF_8))
                               .log("nonWhitespaceString")
      whitespaceString    <- Gen
                               .string(
                                 hedgehog.extra.Gens.genCharByRange(refined4s.types.strings.WhitespaceCharRange),
                                 Range.linear(1, 10),
                               )
                               .log("whitespaceString")

      s <- Gen.constant(scala.util.Random.shuffle((nonWhitespaceString + whitespaceString).toList).mkString).log("s")
    } yield withDb[F](
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

      val expected = NonBlankString.unsafeFrom(s)

      val expectedFetchBefore = none[NonBlankString]
      val expectedInsert      = 1
      val expectedFetchAfter  = expected.some

      val fetch = DbTools.fetchSingleRow[F][NonBlankString](
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
        fetchResultAfter  <- fetch.map(actual =>
                               (actual ==== expectedFetchAfter).log(
                                 show"""            actual: ${actual.map(_.value)}
                  |expectedFetchAfter: ${expectedFetchAfter.map(_.value)}
                  |
                  |            actual (unicode): ${actual.map(_.value.encodeToUnicode)}
                  |expectedFetchAfter (unicode): ${expectedFetchAfter.map(_.value.encodeToUnicode)}
                  |""".stripMargin
                               )
                             )
      } yield Result.all(
        List(
          fetchResultBefore.log("Failed: fetch before"),
          insertResult.log("Failed: insert"),
          fetchResultAfter.log("Failed: fetch after"),
        )
      )

    }

  ///

  def testGetAndPutUuid(testName: String, postgresPortNumber: Int): Property =
    for {
      uuid <- Gen.constant(UUID.randomUUID()).log("uuid")
    } yield withDb[F](
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

      val expected = Uuid(uuid)

      val expectedFetchBefore = none[Uuid]
      val expectedInsert      = 1
      val expectedFetchAfter  = expected.some

      val fetch = DbTools.fetchSingleRow[F][Uuid](
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

}
object stringsSpec extends Properties, stringsSpec {

  override protected object stringsTypeClasses extends refined4s.modules.doobie.derivation.types.strings

  override def tests: List[Test] = allTests

}
