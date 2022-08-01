import com.typesafe.sbt.MultiJvmPlugin.MultiJvmKeys.MultiJvm
import sbt.Keys._
import sbt._
import sbt.io.Path

object Settings {

  def addAliases: Seq[Setting[_]] = {
    addCommandAlias(
      "testAll",
      "test; multi-jvm:test"
    ) ++
    addCommandAlias(
      "compileAll",
      "; scalafmtCheck; scalastyle; test:compile; multi-jvm:compile"
    ) ++
    addCommandAlias(
      "buildAll",
      "; scalafmtCheck; scalastyle; clean; makeSite; test:compile; multi-jvm:compile"
    )
  }
}