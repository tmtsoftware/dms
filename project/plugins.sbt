addSbtPlugin("com.typesafe.sbt" % "sbt-git" % "1.0.2")

resolvers += "jitpack" at "https://jitpack.io"
libraryDependencies += "com.github.tmtsoftware" % "sbt-docs" % "115000a"

classpathTypes += "maven-plugin"

scalacOptions ++= Seq(
  "-encoding",
  "UTF-8",
  "-feature",
  "-unchecked",
  "-deprecation",
  // "-Xfatal-warnings",
  "-Xlint:-unused,_",
  "-Ywarn-dead-code"
)
