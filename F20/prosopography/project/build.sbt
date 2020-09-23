scalaVersion := "2.12.10"
resolvers += Resolver.jcenterRepo
resolvers += Resolver.bintrayRepo("neelsmith","maven")
libraryDependencies ++=   Seq(
 "edu.holycross.shot" %% "latincorpus" % "7.0.0-pr5"
)
