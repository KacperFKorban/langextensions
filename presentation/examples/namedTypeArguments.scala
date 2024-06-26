//> using scala 3.nightly
//> using options -feature -Xfatal-warnings

import scala.language.experimental.namedTypeArguments

def emptyListOf[Elem]: List[Elem] = ???

val xs1 = emptyListOf[Elem = Int]
