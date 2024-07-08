//> using scala 3.nightly
//> using options -feature -Xfatal-warnings
//> using options -source:future
//> using options -Xprint:erasure

import scala.language.experimental.erasedDefinitions

sealed trait State
final class On extends State
final class Off extends State

@scala.annotation.implicitNotFound("State must be Off")
erased class IsOff[S <: State]
object IsOff:
  given isOff: IsOff[Off] = new IsOff[Off]

class Machine[S <: State]:
  def turnedOn(using IsOff[S]): Machine[On] = new Machine[On]

def soStuff =
  val m = new Machine[Off]
  m.turnedOn//.turnedOn
