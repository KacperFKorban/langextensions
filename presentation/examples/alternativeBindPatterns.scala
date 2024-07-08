//> using scala 3.nightly
//> using options -feature -Xfatal-warnings
//> using options -source:future

// no implementation yet
// import scala.language.experimental.alternativeBindPatterns

enum Command:
  case Get, North, Go, Pick, Up
  case Item(name: String)

import Command.*

def loop(cmds: List[Command]): Unit =
  cmds match
    case North :: Nil =>
      ???
    case North :: Nil | Go :: North :: Nil =>
      ???
    case Pick :: Up :: Item(name) :: Nil =>
      ???
    case Pick :: Up :: Item(name) :: Nil |
         Get :: Item(name) :: Nil =>
      ???
