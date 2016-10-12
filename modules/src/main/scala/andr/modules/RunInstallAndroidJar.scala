package andr.modules

import java.io.File

import org.zeroturnaround.zip.ZipUtil
import toolbox6.packaging.{HasMavenCoordinates, MavenTools}

/**
  * Created by martonpapp on 12/10/16.
  */
object RunInstallAndroidJar {
  val SdkDir = new File("/usr/local/opt/android-sdk")
  val Level = 22

  def main(args: Array[String]): Unit = {

    MavenTools.runMaven(
      MavenTools.pom(
        <build>
          <plugins>
            <plugin>
              <groupId>org.apache.maven.plugins</groupId>
              <artifactId>maven-install-plugin</artifactId>
              <version>2.5.2</version>
              <executions>
                <execution>
                  <id>install-bin</id>
                  <phase>package</phase>
                  <goals>
                    <goal>install-file</goal>
                  </goals>
                  <configuration>
                    <file>{new File(SdkDir, s"platforms/android-${Level}/android.jar").getAbsolutePath}</file>
                    <groupId>android-api</groupId>
                    <artifactId>android-api</artifactId>
                    <version>{Level}</version>
                    <packaging>jar</packaging>
                  </configuration>
                </execution>
                <execution>
                  <id>install-sources</id>
                  <phase>package</phase>
                  <goals>
                    <goal>install-file</goal>
                  </goals>
                  <configuration>
                    <file>target/sources.jar</file>
                    <groupId>android-api</groupId>
                    <artifactId>android-api</artifactId>
                    <version>{Level}</version>
                    <classifier>sources</classifier>
                    <packaging>jar</packaging>
                  </configuration>
                </execution>
              </executions>
            </plugin>
          </plugins>
        </build>
      ),
      "package",
      preBuild = { dir =>
        val dst = new File(dir, "target/sources.jar")
        dst.getParentFile.mkdirs()
        val src = new File(SdkDir, s"sources/android-${Level}")
        ZipUtil.pack(src, dst)

      }
    ){ _ => }

  }
}
