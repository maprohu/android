package andr.modules

import maven.modules.builder.{ScalaModule, SubModuleContainer}
import maven.modules.utils.MavenCentralModule
import toolbox6.modules.Toolbox6Modules


/**
  * Created by pappmar on 29/08/2016.
  */
object AndrSpeechModules {

  implicit val Container = SubModuleContainer(AndrModules.Root, "speech")

  object App extends ScalaModule(
    "app",
    "1.0.0-SNAPSHOT",
//    mvn.`android-api:android-api:jar:22`
    mvn.`com.google.android:android:jar:4.1.1.4`
//    new MavenCentralModule(
//      "org.macroid:macroid_2.11:aar:2.0.0-M5"
//    )

  )

  object Packaging extends ScalaModule(
    "packaging",
    "1.0.0-SNAPSHOT",
    AndrModules.Packaging,
    AndrModules
  )
}
