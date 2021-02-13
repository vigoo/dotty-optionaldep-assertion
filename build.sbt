name := "interop-cats"

lazy val root = project
  .in(file("."))  
  .aggregate(interopCats, interopCats)
  .settings(
    skip in publish := true,
  )

val zioVersion = "1.0.4-2"
val commonSettings = Seq(
  scalaVersion := "3.0.0-M3",
  scalacOptions ++= Seq(
    "-deprecation",
    "-encoding",
    "UTF-8",
    "-feature",
    "-unchecked",
    "-Xfatal-warnings",
    "-Ykind-projector", 
    "-noindent",    
  )
)

lazy val interopCats = project
  .in(file("interop-cats"))
  .settings(commonSettings)  
  .settings(
    libraryDependencies ++= Seq(
      "dev.zio"       %% "zio"                  % zioVersion,
    ),
    libraryDependencies ++= Seq(
      "org.typelevel" %% "cats-effect" % "2.3.1"    % Optional, // NOTE: if this dependency is not Optional the code compiles
    )
  )
  .settings(testFrameworks += new TestFramework("zio.test.sbt.ZTestFramework"))

lazy val coreOnlyTest = project
  .in(file("core-only-test"))
  .dependsOn(interopCats)
  .settings(commonSettings)
  .settings(skip in publish := true)
  .settings(
    libraryDependencies ++= Seq(
      "org.typelevel" %% "cats-core"    % "2.3.1"    % Test,
      "dev.zio"       %% "zio-test-sbt" % zioVersion % Test
    )
  )
  .settings(testFrameworks += new TestFramework("zio.test.sbt.ZTestFramework"))
