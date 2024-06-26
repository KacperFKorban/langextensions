//> using scala 3.nightly
//> using options -feature -Xfatal-warnings
//> using options -source:future

import scala.language.experimental.genericNumberLiterals

import scala.util.FromDigits

case class MyInt(value: Int):
  override def toString = value.toString()

object MyInt:

  def apply(digits: String): MyInt =
    MyInt(digits.toInt)

  given FromDigits.Decimal[MyInt] with
      def fromDigits(digits: String) = apply(digits)

val x: MyInt = 1000
