name := "boo"
version := "1.0"
scalaVersion := "2.11.7"
enablePlugins(AndroidApp)
platformTarget := "android-22"
javacOptions in Compile ++= Seq("-source", "1.6", "-target", "1.6")

libraryDependencies ++= Seq(
  aar("org.macroid" %% "macroid" % "2.0.0-M5") exclude("com.android.support", "support-v4")
)


proguardOptions ++= Seq("-keepattributes InnerClasses")