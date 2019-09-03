name := "recommender-system"

version := "1.0-SNAPSHOT"

scalaVersion := "2.12.0"

scalaVersion in ThisBuild := "2.12.0"

val sparkVersion = "2.4.0"
val akkaVersion = "2.3.6"

val reactiveMongoVer = "0.18.5"
val playVer = "2.7.1" // or greater

lazy val root = (project in file(".")).enablePlugins(PlayScala)

libraryDependencies ++= Seq(
  "com.typesafe.play" %% "play-json" % playVer,
  jdbc,
  ehcache,
  ws,
  guice,
  "com.fasterxml.jackson.module" %% "jackson-module-scala" % "2.8.8",
  "com.fasterxml.jackson.core" % "jackson-core" % "2.8.8",
  "com.fasterxml.jackson.core" % "jackson-databind" % "2.8.8",
  // "org.scala-lang" % "scala-xml" % "2.11.0-M4",
  "org.apache.spark" %% "spark-core" % sparkVersion,         // Spark
  "org.apache.spark" %% "spark-mllib" % sparkVersion,        // Spark MLLIB
  "org.apache.spark" %% "spark-sql" % sparkVersion,  
  "org.mongodb.spark" %% "mongo-spark-connector" % sparkVersion,
   
  // "com.typesafe.akka" %% "akka-actor" % akkaVersion,         // Akka Actor
  // "com.typesafe.akka" %% "akka-slf4j" % akkaVersion,         // Akka SLF4J
  "com.typesafe.play" %% "anorm" % "2.6.0-M1",  
  "org.webjars" %% "webjars-play" % "2.7.3",
  "org.webjars" % "bootstrap" % "3.1.1",
  "org.webjars" % "html5shiv" % "3.7.0",
  "org.webjars" % "respond" % "1.4.2",
  "com.twitter" %% "algebird-core" % "0.13.5",  
  "net.databinder.dispatch" %% "dispatch-core" % "0.13.4",  
  "org.reactivemongo" %% "reactivemongo" % reactiveMongoVer,
  "org.reactivemongo" %% "reactivemongo-iteratees" %  reactiveMongoVer,
  "org.mongodb" %% "casbah-core" % "3.1.1",  
  "org.elasticsearch" 		% "elasticsearch" % "1.7.6",  
  "com.sksamuel.elastic4s" % "elastic4s-core_2.12" % "5.0.0",
  "org.elasticsearch.client" % "transport" % "5.0.0",
  "com.typesafe.akka" %% "akka-actor" % akkaVersion,
"com.typesafe.akka" %% "akka-slf4j" % akkaVersion,
"com.typesafe.akka" %% "akka-stream" % akkaVersion
)


