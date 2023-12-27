package extras.doobie

import cats.effect.*
import cats.syntax.all.*
import doobie.Fragment
import doobie.implicits.*
import doobie.util.transactor.Transactor
import effectie.core.*
import effectie.resource.Ce2Resource
import effectie.syntax.all.*
import extras.runner.RunSync
import extras.tools.*
import hedgehog.*
import hedgehog.runner.*
import io.zonky.test.db.postgres.embedded.EmbeddedPostgres

import java.nio.file.Files

/** Copied from [extras](https://github.com/kevin-lee/extras/tree/d7d64a7276fc2041463323109ee7dcd3c2bd2b69/modules/extras-doobie-tools-ce2/shared/src/test/scala/extras)
  * @author Kevin Lee
  * @since 2022-11-27
  */
trait RunWithDb {

  def propertyWithDb(
    name: String,
    portNumber: Int,
    stringToProperty: (String, Int) => Property,
  ): Test =
    property(name, stringToProperty(name, portNumber)).withTests(count = 1).noShrinking

  def withDb[F[*]: Fx: Async: ContextShift: RunSync](
    testName: String,
    portNumber: Int,
    createSchemaFragment: Fragment,
    createTableFragment: Fragment,
  )(test: Transactor[F] => F[Result]): Result =
    RunSync[F].runSync(
      Ce2Resource
        .fromAutoCloseable(effectOf(AutoDeletingFile(Files.createTempDirectory("pg-test").toFile)))
        .use { autoDeletingWorkingDir =>
          val workingDir = autoDeletingWorkingDir.file
          Ce2Resource
            .fromAutoCloseable(
              effectOf(
                EmbeddedPostgres
                  .builder()
                  .setOverrideWorkingDirectory(workingDir)
                  .setCleanDataDirectory(true)
                  .setPort(portNumber)
                  .start()
              )
            )
            .use { postgres =>
              val user     = "postgres"
              val password = "postgres"
              val url      = postgres.getJdbcUrl(user, password)
              for {
                transactor <- effectOf(
                                Transactor.fromDriverManager[F](
                                  "org.postgresql.Driver",
                                  url,
                                  user,
                                  password,
                                )
                              )

                _ <- createSchemaFragment.update.run.transact(transactor)
                _ <- createTableFragment.update.run.transact(transactor)

                result <- test(transactor)
                            .handleNonFatal { err =>
                              Result
                                .failure
                                .log(
                                  s""""$testName": Test failed due to error
                                      |> ERROR: ${err.getMessage.split("\n").mkString("\n>      ")}
                                      |> Type: ${err.getClass.getName}
                                      |> StackTrace: ${err.stackTraceString}
                                      |""".stripMargin
                                )
                            }
              } yield result
            }
        }
    )

}
