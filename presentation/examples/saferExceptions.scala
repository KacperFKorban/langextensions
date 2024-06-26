//> using scala 3.nightly
//> using options -feature -Xfatal-warnings
//> using options -source:future
//> using options -Xprint:erasure

import scala.language.experimental.saferExceptions

class MyException() extends Exception

def f(x: Double, limit: Double = 100.0): Double throws MyException =
  if x < limit then x * x else throw MyException()
