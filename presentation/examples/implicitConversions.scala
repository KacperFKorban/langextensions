//> using scala 3.nightly
//> using options -feature -Xfatal-warnings

import scala.language.implicitConversions

implicit def intToString(i: Int): String = i.toString
