package io.github.owken64

import javax.sound.midi.*;

class MidiHandler
{

        //val infos: Array[MidiDevice.Info] = MidiSystem.getMidiDeviceInfo();
        val infos = MidiSystem.getMidiDeviceInfo()
        var device: MidiDevice = MidiSystem.getMidiDevice(infos(0))
        for (i  <- 0 until infos.length) {
            try {
              device = MidiSystem.getMidiDevice(infos(i))
            //does the device have any transmitters?
            //if it does, add it to the device list
              println(infos(i))

            //get all transmitters
            //  val transmitters:List[Transmitter] = device.getTransmitters()
              val transmitters = device.getTransmitters()
            //and for each transmitter

            for(j <- 0 until transmitters.size) {
                //create a new receiver
                transmitters.get(j).setReceiver(
                        //using my own MidiInputReceiver
                        new MidiInputReceiver(device.getDeviceInfo().toString())
                );
            }

            val trans: Transmitter = device.getTransmitter()
            trans.setReceiver(new MidiInputReceiver(device.getDeviceInfo().toString()))

            //open each device
            device.open();
            //if code gets this far without throwing an exception
            //print a success message
            println(device.getDeviceInfo().toString() + " Was Opened")

        } catch  {
            case e:MidiUnavailableException  => ()
        }
    }
}


//tried to write my own class. I thought the send method handles an MidiEvents sent to it
class MidiInputReceiver(nm: String) extends Receiver {
    import cats.effect.IO
    import scala.concurrent.duration._
    val name:String = nm;

    def send(msg: MidiMessage, timeStamp: Long): Unit = {
        val bary = msg.getMessage()
        println("midi received: " + Byte2Hex(bary(0)) + bary(1).toString() + bary(2).toString()  )

        if ( Byte2Hex(bary(0)) == "90") AudioOut.Bit16_44K.unsafePlay(Sine ,400 * Math.pow(2, (bary(1) - 69.0)/12.0), 1.0)
        //AudioOut.Bit16_44K.unsafePlay(Sine ,440.0, 1.0)
    }
    def close():Unit = {}
}

def Byte2Hex(byte:Byte): String = {
    val b = {
        if ( byte < 0) byte + 256
        else byte.toInt 
    }

    val high = (b / 16) match {
        case 0 => "0"
        case 1 => "1"
        case 2 => "2"
        case 3 => "3"
        case 4 => "4"
        case 5 => "5"
        case 6 => "6"
        case 7 => "7"
        case 8 => "8"
        case 9 => "9"
        case 10 => "A"
        case 11 => "B"
        case 12 => "C"
        case 13 => "D"
        case 14 => "E"
        case 15 => "F"
        case _ => "?"
    }

    val low = (b % 16) match {
        case 0 => "0"
        case 1 => "1"
        case 2 => "2"
        case 3 => "3"
        case 4 => "4"
        case 5 => "5"
        case 6 => "6"
        case 7 => "7"
        case 8 => "8"
        case 9 => "9"
        case 10 => "A"
        case 11 => "B"
        case 12 => "C"
        case 13 => "D"
        case 14 => "E"
        case 15 => "F"
        case _ => "?"
    }

    high + low
}
