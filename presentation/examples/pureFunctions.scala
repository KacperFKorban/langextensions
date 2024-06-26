//> using scala 3.nightly
//> using options -feature -Xfatal-warnings
//> using options -source:future

import scala.language.experimental.pureFunctions

val fib: Int -> Int = (i: Int) => {
  if i < 2 then i
  else fib(i - 1) + fib(i - 2)
}
