import com.typesafe.sbt.site.SitePlugin.autoImport.siteDirectory
import org.tmt.sbt.docs.DocKeys.{docsParentDir, docsRepo, gitCurrentRepo}
import sbt.Keys._
import sbt._
import sbtunidoc.GenJavadocPlugin.autoImport.unidocGenjavadocVersion

object Common {
  private val enableFatalWarnings: Boolean = sys.props.get("enableFatalWarnings").contains("true")

lazy val CommonSettings: Seq[Setting[_]] = Seq(
    
    docsRepo                                        := "https://github.com/tmtsoftware/tmtsoftware.github.io.git",
    docsParentDir                                   := "dms",
    gitCurrentRepo                                  := "https://github.com/tmtsoftware/dms",
    organization                                    := "com.github.tmtsoftware.dms",
    organizationName                                := "Thirty Meter Telescope International Observatory",
    scalaVersion                                    := Libs.ScalaVersion,
    homepage                                        := Some(url("https://github.com/tmtsoftware/dms")),
    resolvers += "jitpack" at "https://jitpack.io",
    scmInfo := Some(
      ScmInfo(url("https://github.com/tmtsoftware/dms"), "git@github.com:tmtsoftware/dms.git")
    ),
    licenses := Seq(("Apache-2.0", url("http://www.apache.org/licenses/LICENSE-2.0"))),
    scalacOptions ++= Seq(
      "-encoding",
      "UTF-8",
      "-feature",
      "-unchecked",
      "-deprecation",
      // -W Options
      "-Wdead-code",
      if (enableFatalWarnings) "-Wconf:any:error" else "-Wconf:any:warning-verbose",
      // -X Options
      "-Xlint:_,-missing-interpolator",
      "-Xsource:3",
      "-Xcheckinit",
      "-Xasync"
      // -Y options are rarely needed, please look for -W equivalents
    ),
    javacOptions ++= Seq(
      "-Xlint:unchecked"
    ),
    javaOptions += "-Xmx2G",
    Compile / doc / javacOptions ++= Seq("-Xdoclint:none"),
    doc / javacOptions ++= Seq("--ignore-source-errors"),
    // jitpack provides the env variable VERSION=<version being built> # A tag or commit. We have aliased VERSION to JITPACK_VERSION
    // we make use of it so that the version in class metadata (e.g. classOf[LocationService].getPackage.getSpecificationVersion)
    // and the maven repo match
    version := sys.env.getOrElse("JITPACK_VERSION", "0.1.0-SNAPSHOT"),
    fork    := true,
    Test / javaOptions ++= Seq("-Dakka.actor.serialize-messages=on"),
    autoCompilerPlugins     := true,
    Global / cancelable     := true, // allow ongoing test(or any task) to cancel with ctrl + c and still remain inside sbt
    commands += Command.command("openSite") { state =>
      val uri = s"file://${Project.extract(state).get(siteDirectory)}/${docsParentDir.value}/${version.value}/index.html"
      state.log.info(s"Opening browser at $uri ...")
      java.awt.Desktop.getDesktop.browse(new java.net.URI(uri))
      state
    },
    Global / excludeLintKeys := Set(
      SettingKey[Boolean]("ide-skip-project"),
      aggregate // verify if this needs to be here or our configuration is wrong
    )
  )
}