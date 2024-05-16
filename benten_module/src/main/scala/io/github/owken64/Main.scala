package io.github.owken64

import cats.effect.IOApp
import cats.effect.IO
import scala.concurrent.duration._

object Main extends IOApp.Simple {

  // This is your new "main"!
  def run: IO[Unit] =
    //HelloWorld.say().flatMap(IO.println)
    val osc=Sine
    //AudioOut.Bit16_44K.play(osc ,440.0, 1.0)
    val mh = new MidiHandler()
    for{
      _ <- IO.sleep(15.seconds)
    } yield ()    
}
