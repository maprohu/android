package andr.speech.packaging

import java.io.File

import andr.modules.AndrSpeechModules
import andr.packaging.AndrPackager
import toolbox6.packaging.MavenTools

import scala.io.StdIn

/**
  * Created by martonpapp on 10/10/16.
  */
object RunPackageSpeechApp {

  val manifest =
    <manifest xmlns:android="http://schemas.android.com/apk/res/android"
              package="andr.speech.app"
              android:versionCode="1"
              android:versionName="1.0">
      <application>
        <activity android:name=".AndrSpeech">
          <intent-filter>
            <action android:name="android.intent.action.MAIN" />
            <category android:name="android.intent.category.LAUNCHER" />
          </intent-filter>
        </activity>
      </application>
      <uses-sdk android:minSdkVersion="22" />
    </manifest>

  def main(args: Array[String]): Unit = {
    MavenTools.runMavenProject(
      new File("../android/speech/app"),
      Seq("install")
    )

    AndrPackager.run(
      AndrSpeechModules.App,
      manifest
    )({ f =>
      StdIn.readLine()
    })
  }

}
