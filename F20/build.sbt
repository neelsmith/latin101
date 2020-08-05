

scalaVersion := "2.12.10"
resolvers += Resolver.jcenterRepo
resolvers += Resolver.bintrayRepo("neelsmith","maven")
libraryDependencies ++=   Seq(
 "edu.holycross.shot"  %% "coursecal" % "2.0.2"
)
