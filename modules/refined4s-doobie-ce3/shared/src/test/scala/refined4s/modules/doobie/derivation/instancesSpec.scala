package refined4s.modules.doobie.derivation

import cats.*
import cats.effect.*
import cats.syntax.all.*
import doobie.syntax.all.*
import extras.doobie.RunWithDb
import extras.doobie.ce3.DbTools
import extras.hedgehog.ce3.CatsEffectRunner
import hedgehog.*
import hedgehog.runner.*
import refined4s.*
import refined4s.modules.cats.derivation.*
import refined4s.modules.doobie.derivation.instances.given
import refined4s.types.all.*

/** @author Kevin Lee
  * @since 2023-12-16
  */
object instancesSpec extends Properties with CatsEffectRunner with RunWithDb {

  import effectie.instances.ce3.fx.ioFx

  type F[A] = IO[A]
  val F: IO.type = IO

  override def tests: List[Test] = List(
    propertyWithDb("test refined4s.modules.doobie.derivation.instances by fetching and updating data", testFetchUpdateFetch)
  )

  def testFetchUpdateFetch(testName: String): Property =
    for {
      example <- genExample.log("example")
    } yield runIO(withDb[F](testName) { transactor =>
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
    })

  def genExample: Gen[Example] = for {
    id    <- Gen.int(Range.linear(1, Int.MaxValue)).map(Example.Id(_))
    name  <- Gen.string(Gen.alphaNum, Range.linear(5, 20)).map(s => Example.Name(NonEmptyString.unsafeFrom(s)))
    note  <- Gen.string(Gen.alphaNum, Range.linear(5, 20)).map(s => Example.Note(Example.MyString.unsafeFrom(s)))
    count <- Gen.int(Range.linear(0, Int.MaxValue)).map(Example.Count.unsafeFrom)
  } yield Example(id, name, note, count)
}
