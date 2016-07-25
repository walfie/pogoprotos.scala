scalaVersion := "2.11.8"

organization := "com.github.walfie"

name := "pogoprotos"

// Required for publishing on Bintray
licenses += ("MIT", url("http://opensource.org/licenses/MIT"))

libraryDependencies ++= Seq(
  // Enable custom package options
  "com.trueaccord.scalapb" %% "scalapb-runtime" % "0.5.34" % ScalaPbPlugin.protobufConfig
)

// ScalaPB settings
import com.trueaccord.scalapb.ScalaPbPlugin

ScalaPbPlugin.protobufSettings
inConfig(ScalaPbPlugin.protobufConfig) {
  import ScalaPbPlugin.{runProtoc, flatPackage}

  Seq(
    flatPackage := true,
    runProtoc := { args =>
      com.github.os72.protocjar.Protoc.runProtoc("-v300" +: args.toArray)
    }
  )
}

// sbt-site plugin
enablePlugins(SiteScaladocPlugin)

// sbt-release
import ReleaseTransformations._
releaseProcess -= pushChanges

