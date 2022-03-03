

package com.`complex-human`

import com.lightbend.paradox.sbt.ParadoxPlugin
import com.lightbend.paradox.sbt.ParadoxPlugin.autoImport._
import com.lightbend.paradox.apidoc.ApidocPlugin
import sbt.Keys._
import sbt._

object Paradox {

  val propertiesSettings = Seq(
    Compile / paradoxProperties ++= Map(
        "project.description" -> "Helena Edelson: wildlife conservation hactivist: complex adaptive systems, endangered species, wildlife biology & climate change.",
        "canonical.base_url" -> "http://complex-human.com/", // TODO https
        "github.base_url" -> GitHub.url(version.value), // for links like this: @github[#1](#1) or @github[83986f9](83986f9)
        "image.base_url" -> ".../assets/images",
        "extref.github.base_url" -> (GitHub.url(version.value) + "/%s"), // for links to sources
        "scala.version" -> scalaVersion.value,
        "scala.binary_version" -> scalaBinaryVersion.value))

  // Exclude the includes directory from being compiled directly
  // https://github.com/lightbend/paradox/issues/350
  val includesSettings = Seq(
    (Compile / paradoxMarkdownToHtml / excludeFilter) := (Compile / paradoxMarkdownToHtml / excludeFilter).value ||
      ParadoxPlugin.InDirectoryFilter((Compile / paradox / sourceDirectory).value / "includes"))

  val settings =
    propertiesSettings ++
    includesSettings ++
    Seq(
      name in (Compile, paradox) := "Helena Edelson",
      resolvers += Resolver.jcenterRepo,
      ApidocPlugin.autoImport.apidocRootPackage := "root",
      paradoxRoots := List("index.html"),
      paradoxTheme := Some(builtinParadoxTheme("generic")))

}

object GitHub {
 
  def url(v: String): String = {
    val branch = if (v.endsWith("SNAPSHOT")) "master" else "v" + v
    "https://github.com/helena/complex-human.com/tree/" + branch
  }
}
