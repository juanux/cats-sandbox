resolvers ++= {
  implicit val basePatterns = Resolver.ivyStylePatterns
  Seq(
    Resolver.sonatypeRepo("releases"),
    Resolver.typesafeRepo("releases"),
    Resolver.bintrayRepo("websudos", "oss-releases"),
    Resolver.url("Websudos OSS", url("http://dl.bintray.com/websudos/oss-releases")),
  )
}

addSbtPlugin("com.lucidchart" % "sbt-scalafmt" % "1.14")