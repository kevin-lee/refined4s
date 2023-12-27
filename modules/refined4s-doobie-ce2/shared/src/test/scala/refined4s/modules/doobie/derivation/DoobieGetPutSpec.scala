package refined4s.modules.doobie.derivation

import cats.effect.ContextShift
import cats.syntax.all.*
import doobie.syntax.all.*
import extras.doobie.RunWithDb
import extras.doobie.ce2.DbTools
import extras.runner.ce2.RunSyncCe2
import hedgehog.*
import hedgehog.runner.*
import refined4s.*
import refined4s.types.all.*

import java.util.concurrent.atomic.AtomicInteger

/** @author Kevin Lee
  * @since 2023-12-16
  */
object DoobieGetPutSpec extends Properties with RunSyncCe2 with RunWithDb {

  val postgresPortNumber: AtomicInteger = AtomicInteger(5632)

  import effectie.instances.ce2.fx.ioFx

  override def tests: List[Test] = List(
    propertyWithDb(
      "test DoobiePut, DoobieNewtypeGet, DoobieRefinedGet, DoobieNewtypeGetPut and DoobieRefinedGetPut all together by fetching and updating data",
      postgresPortNumber.getAndIncrement(),
      testFetchUpdateFetch,
    )
  )

  @SuppressWarnings(Array("org.wartremover.warts.GlobalExecutionContext"))
  implicit val cs: ContextShift[F] = F.contextShift(scala.concurrent.ExecutionContext.global)

  def testFetchUpdateFetch(testName: String, postgresPortNumber: Int): Property =
    for {
      example <- genExampleWithDoobieGetPut.log("example")
    } yield withDb[F](
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
      val expectedFetchBefore = none[ExampleWithDoobieGetPut]
      val expectedInsert      = 1
      val expectedFetchAfter  = example.some

      val fetch = DbTools.fetchSingleRow[F][ExampleWithDoobieGetPut](
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

  def genExampleWithDoobieGetPut: Gen[ExampleWithDoobieGetPut] = for {
    id    <- Gen.int(Range.linear(1, Int.MaxValue)).map(ExampleWithDoobieGetPut.Id(_))
    name  <- Gen.string(Gen.alphaNum, Range.linear(5, 20)).map(s => ExampleWithDoobieGetPut.Name(NonEmptyString.unsafeFrom(s)))
    note  <-
      Gen.string(Gen.alphaNum, Range.linear(5, 20)).map(s => ExampleWithDoobieGetPut.Note(ExampleWithDoobieGetPut.MyString.unsafeFrom(s)))
    count <- Gen.int(Range.linear(0, Int.MaxValue)).map(ExampleWithDoobieGetPut.Count.unsafeFrom)
  } yield ExampleWithDoobieGetPut(id, name, note, count)
}
