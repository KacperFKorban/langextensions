//> using scala 3.5.1-RC1-bin-SNAPSHOT
//> using options -feature -Xfatal-warnings
//> using options -source:future
//> using options -Xprint:typer

import scala.language.experimental.betterFors

// new syntax allowed
val aFor1 =
  for
    a = 1
    b <- 2 to 3
  yield a + b

// simpler desugaring
val aFor2 =
  for
    a <- 1 to 2
    b = 3
  yield a + b

// trailing map eliminatin (if the last generator symbol is syntactically equal to `yield`)
// works for identifiers and tuples
val aFor3 =
  for
    a <- 1 to 2
    (b, c) <- List(3 -> 4)
  yield (b, c)
