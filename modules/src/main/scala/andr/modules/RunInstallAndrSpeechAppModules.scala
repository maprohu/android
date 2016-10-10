package andr.modules

import java.io.File

import toolbox6.packaging.MavenTools

/**
  * Created by pappmar on 05/10/2016.
  */
object RunInstallAndrSpeechAppModules {

  def main(args: Array[String]): Unit = {

    val root = new File("../../../../..")

    MavenTools.runMaven(
      MavenTools.pom {
        <packaging>pom</packaging>
        <modules>
          <module>{root}/maven-modules</module>
          <module>{root}/toolbox6/modules</module>
          <module>{root}/toolbox6</module>
          <module>{root}/android/modules</module>
          <module>{root}/android</module>
        </modules>
      },
      "install"
    )(_ => ())


  }

}
