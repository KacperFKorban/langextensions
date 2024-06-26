//> using scala 3.nightly
//> using options -feature -Xfatal-warnings
//> using options -source:future

import scala.language.experimental.betterMatchTypeExtractors

class Base {
  type Y
}

type YExtractor[t] = Base { type Y = t }

type ExtractY[B <: Base] = B match
  case YExtractor[t] => t

class Sub1 extends Base:
  type Y = Alias
  type Alias = Int
class Sub2[T] extends Base:
  type Y = T
class Sub3 extends Base:
  val elem: Sub1 = new Sub1
  type Y = elem.Y

def usage4 =
  summon[ExtractY[Base { type Y = Int }] =:= Int] // OK
  summon[ExtractY[Sub1] =:= Int] // error
  summon[ExtractY[Sub2[Int]] =:= Int] // OK
  summon[ExtractY[Sub3] =:= Int] // error
