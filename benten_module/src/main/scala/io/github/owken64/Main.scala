package io.github.owken64

import cats.effect.IOApp
import cats.effect.IO

object Main extends IOApp.Simple {

  // This is your new "main"!
  def run: IO[Unit] =
    //HelloWorld.say().flatMap(IO.println)
    val osc=Sine
    AudioOut.Bit16_44K.play(osc ,440.0, 1.0)
}
