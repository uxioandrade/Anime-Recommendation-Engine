name := "recommender-system"

version := "1.0-SNAPSHOT"

scalaVersion := "2.12.0"

scalaVersion in ThisBuild := "2.12.0"

val sparkVersion = "1.3.0"

val akkaVersion = "2.3.6"

lazy val root = (project in file(".")).enablePlugins(PlayScala)

libraryDependencies ++= Seq(
  "com.typesafe.play" %% "play-json" % "2.7.1",
  jdbc,
  ehcache,
  // "org.scala-lang" % "scala-xml" % "2.11.0-M4",
  // "org.apache.spark" %% "spark-core" % sparkVersion,         // Spark
  // "org.apache.spark" %% "spark-mllib" % sparkVersion,        // Spark MLLIB
  // "com.typesafe.akka" %% "akka-actor" % akkaVersion,         // Akka Actor
  // "com.typesafe.akka" %% "akka-slf4j" % akkaVersion,         // Akka SLF4J
"com.typesafe.play" %% "anorm" % "2.6.0-M1",  
"org.webjars" %% "webjars-play" % "2.7.0-1",  
"org.webjars" % "bootstrap" % "3.1.1",
  "org.webjars" % "html5shiv" % "3.7.0",
  "org.webjars" % "respond" % "1.4.2",
	"com.twitter" %% "algebird-core" % "0.13.5",  
"net.databinder.dispatch" %% "dispatch-core" % "0.13.4",  
"org.reactivemongo" %% "reactivemongo" % "0.18.5",  
"org.mongodb" %% "casbah-core" % "3.1.1",  
 "com.sksamuel.elastic4s" % "elastic4s-core_2.12" % "7.3.0"
)


