
version := "0.1.0"

shellPrompt := { s =>
  Project.extract(s).currentProject.id + " > "
}

val disciplineScalacOptions = Seq(
  "-Ywarn-numeric-widen",
  "-deprecation",
  "-Xlint",
  "-Ywarn-dead-code",
  "-Ywarn-unused:_",
  "-Ywarn-extra-implicit")

val buildSettings = Seq(
  scalaVersion := "2.13.3",
  organizationHomepage := Some(url("http://complex-human.com")),
  developers := List(Developer("helena", "Helena Edelson", "@helena", url("https://github.com/helena"))),
  projectInfoVersion := (if (isSnapshot.value) "snapshot" else version.value),
  Compile / scalacOptions := disciplineScalacOptions,
  Compile / scalacOptions := Seq(
    "-encoding",
    "UTF-8",
    "-feature",
    "-unchecked",
    "-deprecation",
    "-Xlint"))

lazy val docs = project
  .in(file("docs"))
  .settings(buildSettings)
  .settings(com.`complex-human`.Paradox.settings)
  .enablePlugins(com.lightbend.paradox.sbt.ParadoxPlugin)

lazy val root = project
  .in(file("."))
  .settings(buildSettings)
  .aggregate(docs)
 

 
