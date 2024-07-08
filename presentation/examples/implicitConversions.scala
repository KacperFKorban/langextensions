//> using scala 3.nightly
//> using options -feature -Xfatal-warnings

import scala.language.implicitConversions

case class Msg(msg: String)

implicit def stringToMsg(i: String): Msg = Msg(i)

case class Msg1(msg: String)

given Conversion[String, Msg1] with
  def apply(i: String): Msg1 = Msg1(i)

def usg =
  val str = "hello"
  val msg1: Msg1 = str
  println(msg1)
  val msg: Msg = str
  println(msg)
