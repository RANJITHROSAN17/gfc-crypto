name := "gfc-crypto"

organization := "org.gfccollective"

scalaVersion := "2.13.7"

crossScalaVersions := Seq(scalaVersion.value, "2.12.15", "3.1.0")

scalacOptions += "-target:jvm-1.8"

javacOptions ++= Seq("-source", "1.8", "-target", "1.8")

libraryDependencies ++= Seq(
  "org.scalatest" %% "scalatest" % "3.2.10" % Test,
  "org.scalatestplus" %% "scalacheck-1-15" % "3.2.10.0" % Test,
  "org.scalacheck" %% "scalacheck" % "1.15.4" % Test,
)

releaseCrossBuild := true

releasePublishArtifactsAction := PgpKeys.publishSigned.value

publishMavenStyle := true

publishTo := {
  val nexus = "https://oss.sonatype.org/"
  if (isSnapshot.value)
    Some("snapshots" at nexus + "content/repositories/snapshots")
  else
    Some("releases"  at nexus + "service/local/staging/deploy/maven2")
}

Test / publishArtifact := false

pomIncludeRepository := { _ => false }

licenses := Seq("Apache-style" -> url("https://raw.githubusercontent.com/gfc-collective/gfc-crypto/main/LICENSE"))

homepage := Some(url("https://github.com/gfc-collective/gfc-crypto"))

pomExtra := (
  <scm>
    <url>https://github.com/gfc-collective/gfc-crypto.git</url>
    <connection>scm:git:git@github.com:gfc-collective/gfc-crypto.git</connection>
  </scm>
  <developers>
    <developer>
      <id>gheine</id>
      <name>Gregor Heine</name>
      <url>https://github.com/gheine</url>
    </developer>
    <developer>
      <id>ebowman</id>
      <name>Eric Bowman</name>
      <url>https://github.com/ebowman</url>
    </developer>
    <developer>
      <id>andreyk0</id>
      <name>Andrey Kartashov</name>
      <url>https://github.com/andreyk0</url>
    </developer>
  </developers>
)
