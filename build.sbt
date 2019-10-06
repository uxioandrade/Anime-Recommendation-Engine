name := """anime-recommender-system"""

version := "1.0-SNAPSHOT"

lazy val root = (project in file(".")).enablePlugins(PlayScala).settings(
  watchSources ++= (baseDirectory.value / "public/frontend" ** "*").get
)

resolvers += Resolver.sonatypeRepo("snapshots")

scalaVersion := "2.12.8"
val sparkVersion = "2.4.0"
val akkaVersion = "2.3.6"
val reactiveMongoVer = "0.18.5"

libraryDependencies ++= Seq(
  guice,
  "org.scalatestplus.play" %% "scalatestplus-play" % "4.0.2" % Test,
  "com.h2database" % "h2" % "1.4.199",
  jdbc,
  ehcache,
  ws,
  "com.fasterxml.jackson.module" %% "jackson-module-scala" % "2.9.8",
  "com.fasterxml.jackson.core" % "jackson-core" % "2.9.8",
  "com.fasterxml.jackson.core" % "jackson-databind" % "2.9.8",
  // "org.scala-lang" % "scala-xml" % "2.11.0-M4",
  "org.apache.spark" %% "spark-core" % sparkVersion,         // Spark
  "org.apache.spark" %% "spark-mllib" % sparkVersion,        // Spark MLLIB
  "org.apache.spark" %% "spark-sql" % sparkVersion,  
  "org.mongodb.spark" %% "mongo-spark-connector" % sparkVersion,
  "org.apache.hadoop" % "hadoop-client" % "2.7.2",
   //see https://stackoverflow.com/questions/48590083/guava-dependency-error-for-spark-play-framework-using-scala
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
  "com.typesafe.akka" %% "akka-stream" % akkaVersion,
  "javax.activation" % "javax.activation-api" % "1.2.0",
  "org.postgresql" % "postgresql" % "42.2.5",
  "org.jooq" % "jooq" % "3.11.0",
  "org.jooq" % "jooq-codegen" % "3.11.0",
  "org.jooq" % "jooq-meta" % "3.11.0",
)

//dependencyOverrides += "com.google.guava" % "guava" % "15.0"

enablePlugins(JooqCodegen)

jooqOrganization := "org.jooq"

jooqVersion := "3.11.0"

jooqCodegenConfig := {
  
  <configuration xmlns="http://www.jooq.org/xsd/jooq-codegen-3.11.0.xsd">
    <jdbc>
      <driver>org.postgresql.Driver</driver>
      <url>jdbc:postgresql://localhost:5432/animelist</url>
      <user>postgres</user>
      <password>admin</password>
    </jdbc>
    <generator>
      <name>org.jooq.codegen.ScalaGenerator</name>
      <database>
        <name>org.jooq.meta.postgres.PostgresDatabase</name>
        <inputSchema>public</inputSchema>
        <includes>.*</includes>
        <excludes></excludes>
      </database>
      <target>
        <packageName>generated</packageName>
        <directory>app/models/jooq</directory>
      </target>
    </generator>
  </configuration>
}

jooqCodegenStrategy := CodegenStrategy.IfAbsent