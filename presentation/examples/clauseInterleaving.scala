//> using scala 3.nightly
//> using options -feature -Xfatal-warnings
//> using options -source:future
//> using options -Xprint:erasure

import scala.language.experimental.clauseInterleaving

def pair[A](a: A)[B](b: B): (A, B) = (a, b)

// no type parameter currying
// def curriedTpes[A][B](a: A)(b: B): (A, B) = (a, b)
