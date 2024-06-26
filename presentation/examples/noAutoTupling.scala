//> using scala 3.nightly
//> using options -feature -Xfatal-warnings

import scala.language.noAutoTupling

case class B(x: (Int, Int))

def usage1(b: B) = b match
  case B(x, y) => x + y
