//> using scala 3.nightly
//> using options -feature -Xfatal-warnings

import scala.language.strictEquality

class C

def usage2(c1: C, c2: C) =
  c1 == c2
