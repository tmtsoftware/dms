import Common._
import org.tmt.sbt.docs.{Settings => DocSettings}

/* ================= Root Project ============== */
lazy val `dms` = project
  .in(file("."))
  .enablePlugins(NoPublish, UnidocSitePlugin, GithubPublishPlugin, GitBranchPrompt, GithubRelease)
  .settings(DocSettings.makeSiteMappings(docs))
  .settings(Settings.addAliases)
  .settings(DocSettings.docExclusions(unidocExclusions))
  .settings(GithubRelease.githubReleases)
  .settings(
    ghreleaseRepoOrg  := "tmtsoftware",
    ghreleaseRepoName := "dms"
  )


/* ================= Paradox Docs ============== */
lazy val docs = project
  .enablePlugins(NoPublish, ParadoxMaterialSitePlugin)
  .settings(
    paradoxRoots := List(
      "index.html"
	  )
	)