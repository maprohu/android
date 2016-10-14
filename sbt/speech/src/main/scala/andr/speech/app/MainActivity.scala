package andr.speech.app

import android.app.Activity
import android.graphics.Color
import android.os.Bundle
import android.view.ViewGroup.LayoutParams._
import android.view.{Gravity, View}
import android.widget.{Button, LinearLayout, ListView, TextView}
import rx.{Rx, Var}

import scala.language.postfixOps

// import macroid stuff
import macroid.FullDsl._
import macroid._
import macroid.contrib._
import macroid.viewable._

import scala.concurrent.ExecutionContext.Implicits.global


class MainActivity extends Activity with Contexts[Activity] {

  sealed trait Status
  case object None extends Status
  case object Recording extends Status
  case object Recorded extends Status
  case object Playing extends Status

  import rx.Ctx.Owner.Unsafe._
  val status = Var[Status](None)
  val canRecord = Rx(status() == None || status() == Recorded)
  val canPlay = Rx(status() == Recorded)
  val canStop = Rx(status() == Recording || status() == Playing)

  def link(st: Rx[Boolean], b: => Option[View]) = {
    st.foreach({ e =>
      (b <~ (if (e) enable else disable)).get
    })
  }

  var record = slot[Button]
  var play = slot[Button]
  var stop = slot[Button]

  override def onCreate(savedInstanceState: Bundle) = {
    super.onCreate(savedInstanceState)


    val view = l[LinearLayout](

      w[Button] <~
        text("Record") <~
        wire(record) <~
        On.click {
          Ui {
            status() = Recording
          }
        } <~
        layoutParams[LinearLayout](MATCH_PARENT, WRAP_CONTENT),

      w[Button] <~
        text("Play") <~
        wire(play) <~
        On.click {
          Ui {
            status() = Playing
          }
        } <~
        layoutParams[LinearLayout](MATCH_PARENT, WRAP_CONTENT),

      w[Button] <~
        text("Stop") <~
        wire(stop) <~
        On.click {
          Ui {
            status() = Recorded
          }
        } <~
        layoutParams[LinearLayout](MATCH_PARENT, WRAP_CONTENT)


    ) <~ vertical

    setContentView(view.get)

    link(canRecord, record)
    link(canPlay, play)
    link(canStop, stop)

  }
}
