//> using scala 3.nightly
//> using options -feature -Xfatal-warnings

import scala.language.experimental.namedTypeArguments

def emptyListOf[Elem]: List[Elem] = ???

def construct[Elem, Coll[_]](xs: Elem*): Coll[Elem] = ???

val xs1 = emptyListOf[Elem = Int]

val xs2 = construct[Coll = List](1, 2, 3)

class Person[
  Name <: String
]

// can't use named type arguments in type constructors
// type JohnPaul = Person[Name = "John Paul"] // error

sealed trait Nat
object Zero extends Nat
class Suc[N <: Nat] extends Nat

type Prev[N <: Nat] = N match
  case Suc[prev] => prev

type Zero = Prev[N = Suc[Suc[Zero]]]
