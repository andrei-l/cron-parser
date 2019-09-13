
organization := "com.dl"
version := "1.0"
name := "cron-parser"

mainClass := Some("com.dl.CronParser")
libraryDependencies ++=  Dependencies.TestScope

scalaVersion := "2.12.7"
scalacOptions += "-Ypartial-unification"


assemblyJarName in assembly := s"${name.value}.jar"
