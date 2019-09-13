import sbt._

object Dependencies {
  lazy val TestScope: Seq[ModuleID] = Seq(
    "org.mockito" % "mockito-core" % "1.10.19",
    "org.scalatest" %% "scalatest" % "3.0.5"
  ).map(_ % Test)
}
