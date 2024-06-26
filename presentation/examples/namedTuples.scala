//> using scala 3.nightly
//> using options -feature -Xfatal-warnings
//> using options -source:future

// https://contributors.scala-lang.org/t/pre-sip-named-tuples/6403/110

import scala.language.experimental.namedTuples

type Person = (name: String, age: Int)
val bob: Person = (name = "Bob", age = 33)

def usage2 =
  bob match
    case (name = n, age = 22) => n
