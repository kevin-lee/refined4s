package refined4s.modules.doobie.derivation.generic

import cats.*
import cats.effect.*
import cats.syntax.all.*
import doobie.implicits.*
import extras.doobie.RunWithDb
import extras.doobie.ce3.DbTools
import extras.hedgehog.ce3.CatsEffectRunner
import hedgehog.*
import hedgehog.runner.*
import refined4s.*
import refined4s.modules.doobie.derivation.Example
import refined4s.modules.doobie.derivation.generic.auto.given
import refined4s.types.all.*

import java.util.concurrent.atomic.AtomicInteger

/** @author Kevin Lee
  * @since 2023-12-16
  */
object autoSpec extends Properties with CatsEffectRunner with RunWithDb {

  val postgresPortNumber: AtomicInteger = AtomicInteger(5632)

  import effectie.instances.ce3.fx.ioFx

  type F[A] = IO[A]
  val F: IO.type = IO

  override def tests: List[Test] = List(
    propertyWithDb(
      "test refined4s.modules.doobie.derivation.instances by fetching and updating data",
      postgresPortNumber.getAndIncrement(),
      testFetchUpdateFetch,
    )
  )

  def testFetchUpdateFetch(testName: String, postgresPortNumber: Int): Property =
    for {
      example <- genExample.log("example")
    } yield runIO(
      withDb[F](
        testName,
        postgresPortNumber,
        sql"""CREATE SCHEMA IF NOT EXISTS db_tools_test""",
        sql"""
        CREATE TABLE IF NOT EXISTS db_tools_test.example
        (
            id SERIAL PRIMARY KEY,
            name TEXT NOT NULL,
            note TEXT NULL,
            count INT NOT NULL
        )
      """,
      ) { transactor =>
        val expectedFetchBefore = none[Example]
        val expectedInsert      = 1
        val expectedFetchAfter  = example.some

        val fetch = DbTools.fetchSingleRow[F][Example](
          sql"""
          SELECT id, name, note, count
            FROM db_tools_test.example
        """
        )(transactor)

        val insert = DbTools.updateSingle[F](
          sql"""
          INSERT INTO db_tools_test.example (id, name, note, count) VALUES (${example.id}, ${example.name}, ${example.note}, ${example.count})
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

  def genExample: Gen[Example] = for {
    id    <- Gen.int(Range.linear(1, Int.MaxValue)).map(Example.Id(_))
    name  <- Gen.string(Gen.alphaNum, Range.linear(5, 20)).map(s => Example.Name(NonEmptyString.unsafeFrom(s)))
    note  <- Gen.string(Gen.alphaNum, Range.linear(5, 20)).map(s => Example.Note(Example.MyString.unsafeFrom(s)))
    count <- Gen.int(Range.linear(0, Int.MaxValue)).map(Example.Count.unsafeFrom)
  } yield Example(id, name, note, count)
}
