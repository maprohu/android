package andr.speech.app

import android.app.Activity
import android.os.Bundle
import android.widget.{ListView, TextView}

/**
  * Created by martonpapp on 10/10/16.
  */
class AndrSpeech extends Activity {
  override def onCreate(savedInstanceState: Bundle): Unit = {
    super.onCreate(savedInstanceState)

    val list = new ListView(this)

    (1 to 100)
      .foreach({ i =>
        val t = new TextView(this)
        t.setText(s"item ${i}")
        list.addView(t)
      })

    setContentView(
      list
    )
  }
}
