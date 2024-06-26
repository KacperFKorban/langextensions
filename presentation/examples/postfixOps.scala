//> using scala 3.nightly
//> using options -feature -Xfatal-warnings

import scala.language.postfixOps

case class A(x: Int):
  def ++ = x + 1

def usage(a: A) =
  a ++
