import sbt.Keys._
import sbt._

object NoPublish extends AutoPlugin {
  override def requires: Plugins = plugins.JvmPlugin

  override def projectSettings: Seq[Setting[_]] =
    Seq(
      publishArtifact := false,
      publish         := {},
      publishLocal    := {}
    )
}
