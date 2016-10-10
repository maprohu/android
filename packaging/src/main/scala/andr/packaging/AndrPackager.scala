package andr.packaging

import java.io.File

import toolbox6.packaging.{HasMavenCoordinates, MavenTools}

import scala.xml.{Node, XML}

/**
  * Created by martonpapp on 10/10/16.
  */
object AndrPackager {

  def run(
    app: HasMavenCoordinates,
    manifest: Node
  )(fn : File => Unit) = {
    MavenTools.runMavens(
      MavenTools.pom(
        <packaging>apk</packaging>
        <build>
          <finalName>app</finalName>
          <plugins>
            <plugin>
              <groupId>com.simpligility.maven.plugins</groupId>
              <artifactId>android-maven-plugin</artifactId>
              <version>4.4.1</version>
              <extensions>true</extensions>
              <configuration>
                <sdk>
                  <platform>22</platform>
                </sdk>
              </configuration>
            </plugin>
          </plugins>
        </build>
        <dependencies>
          <dependency>
            <groupId>com.google.android</groupId>
            <artifactId>android</artifactId>
            <version>4.1.1.4</version>
            <scope>provided</scope>
          </dependency>
          {app.asPomDependency}
        </dependencies>
      ),
      Seq("package", "android:deploy", "android:run"),
      preBuild = { dir =>
        val mf = new File(dir, "src/main/AndroidManifest.xml")
        mf.getParentFile.mkdirs()
        XML.save(
          mf.getCanonicalFile.getAbsolutePath,
          manifest
        )
      }
    )(fn)
  }

}
