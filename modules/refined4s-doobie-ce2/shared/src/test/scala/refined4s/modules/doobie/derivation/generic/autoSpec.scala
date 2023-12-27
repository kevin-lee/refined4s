package refined4s.modules.doobie.derivation.generic

import cats.effect.ContextShift
import cats.syntax.all.*
import doobie.syntax.all.*
import extras.doobie.RunWithDb
import extras.doobie.ce2.DbTools
import extras.runner.ce2.RunSyncCe2
import hedgehog.*
import hedgehog.runner.*
import refined4s.*
import refined4s.modules.doobie.derivation.generic.auto.given
import refined4s.modules.doobie.derivation.{Example, Gens}
import refined4s.types.all.*

import java.util.concurrent.atomic.AtomicInteger

/** @author Kevin Lee
  * @since 2023-12-16
  */
object autoSpec extends Properties with RunSyncCe2 with RunWithDb {

  val postgresPortNumber: AtomicInteger = AtomicInteger(5432)

  import effectie.instances.ce2.fx.ioFx

  override def tests: List[Test] = List(
    propertyWithDb(
      "test refined4s.modules.doobie.derivation.instances by fetching and updating data",
      postgresPortNumber.getAndIncrement(),
      testFetchUpdateFetch,
    )
  )

  @SuppressWarnings(Array("org.wartremover.warts.GlobalExecutionContext"))
  implicit val cs: ContextShift[F] = F.contextShift(scala.concurrent.ExecutionContext.global)

  def testFetchUpdateFetch(testName: String, postgresPortNumber: Int): Property =
    for {
      example <- Gens.genExample.log("example")
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

}
