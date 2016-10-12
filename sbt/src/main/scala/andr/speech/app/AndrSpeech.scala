package andr.speech.app

import android.app.Activity
import android.os.Bundle
import android.widget.{Button, LinearLayout, ListView, TextView}

/**
  * Created by martonpapp on 10/10/16.
  */
class AndrSpeech extends Activity {
  override def onCreate(savedInstanceState: Bundle): Unit = {
    super.onCreate(savedInstanceState)

    val l = new LinearLayout(this)
    l.setOrientation(LinearLayout.VERTICAL)

    val record = new Button(this)
    record.setText("Record")
    l.addView(record)


    val t = new TextView(this)
    t.setText("csuf")
    l.addView(t)

    setContentView(l)

//    val list = new ListView(this)
//    list.setAdapter()
//
//
//    (1 to 100)
//      .foreach({ i =>
//        val t = new TextView(this)
//        t.setText(s"item ${i}")
//        list.addView(t)
//      })
//
//    setContentView(
//      list
//    )
  }
}
