//> using scala 3.nightly
//> using options -feature -Xfatal-warnings
//> using options -source:future

// no implementation yet
// import scala.language.experimental.multipleAssignments

def usage5 =
  var a = 2
  var b = 4
  (a, b) = (b, a)
  println(s"$a$b") // 42
