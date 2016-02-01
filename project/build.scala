import sbt.Keys._
import sbt._

object GameBuild extends Build {

  lazy val libgdxVersion = settingKey[String]("version of Libgdx library")

  lazy val commonSettings = Seq(
    scalaVersion := "2.11.7",
    libgdxVersion := "1.9.1",
    javacOptions ++= Seq("-source", "1.7", "-target", "1.7", "-encoding", "UTF-8"),
    scalacOptions in Compile += "-feature",
    unmanagedResourceDirectories in Compile += file("core/assets")
  )

  lazy val core = (project in file("core"))
    .settings(commonSettings)
    .settings(
      name := "core",
      libraryDependencies ++= Seq(
        "com.badlogicgames.gdx" % "gdx" % libgdxVersion.value,
        "com.badlogicgames.gdx" % "gdx-box2d" % libgdxVersion.value
      )
    )

  lazy val desktop = (project in file("desktop"))
    .dependsOn(core)
    .settings(commonSettings)
    .settings(
      name := "desktop",
      mainClass in(Compile, run) := Some("com.daesap.gdx.launcher.DesktopLauncher"),
      libraryDependencies ++= Seq(
        "com.badlogicgames.gdx" % "gdx-backend-lwjgl" % libgdxVersion.value,
        "com.badlogicgames.gdx" % "gdx-platform" % libgdxVersion.value classifier "natives-desktop",
        "com.badlogicgames.gdx" % "gdx-box2d-platform" % libgdxVersion.value classifier "natives-desktop"
      )
    )

}