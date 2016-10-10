package andr.modules

import maven.modules.builder.{RootModuleContainer, ScalaModule}
import maven.modules.utils.MavenCentralModule
import toolbox6.modules.Toolbox6Modules


/**
  * Created by martonpapp on 29/08/16.
  */
object AndrModules extends MavenCentralModule(
  "andr-modules",
  "andr-modules",
  "1.0.0-SNAPSHOT"
){

  val Root = RootModuleContainer("andr")

  object Packaging extends ScalaModule(
    "packaging",
    "1.0.0-SNAPSHOT",
    Toolbox6Modules.Packaging,
    mvn.`org.scala-lang.modules:scala-xml_2.11:jar:1.0.6`
  )(Root)
}

