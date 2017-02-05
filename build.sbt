
scalaVersion in ThisBuild := "2.11.7"

organization in ThisBuild := "com.hakim"

publishArtifact := false

coverageEnabled in ThisBuild := true

coverageMinimum in ThisBuild := 80.0

coverageFailOnMinimum in ThisBuild := true

fork in ThisBuild := true

parallelExecution in ThisBuild := false


lazy val `hakim-data-bill` = project

lazy val `hakim-data-clac` = project

lazy val `hakim-data-ext-web` = project
