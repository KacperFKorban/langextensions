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
  // given FromDigits[MyInt] with
  //     def fromDigits(digits: String) = MyInt(digits)
  // given FromDigits.Decimal[MyInt] with
  //     def fromDigits(digits: String) = MyInt(digits.toFloat.toInt.toString())

  // class FromDigits extends FromDigits.Floating[MyInt]:
  //   def fromDigits(digits: String): MyInt = apply(digits)

  given FromDigits[MyInt] with
    override inline def fromDigits(digits: String) =
      ${ fromDigitsImpl('digits) }

val i: MyInt = 1
// val j: MyInt = 100.0

case class MyFloat(value: Float):
  override def toString = value.toString()

object MyFloat:
  def apply(digits: String): MyFloat =
    MyFloat(digits.toFloat)
  given FromDigits.Decimal[MyFloat] with
      def fromDigits(digits: String) = MyFloat(digits)

val x: MyFloat = 1000.0

case class MyFloating(value: Double):
  override def toString = value.toString()

object MyFloating:
  def apply(digits: String): MyFloating =
    MyFloating(digits.toFloat)
  given FromDigits.Floating[MyFloating] with
      def fromDigits(digits: String) =
        val (beforeE, afterE) = digits.splitAt(digits.indexOf('e'))
        MyFloating(beforeE.toFloat * Math.pow(10, afterE.tail.toInt))

val y: MyFloating = 1000.0e-3

case class MyWithRadix(value: Int):
  override def toString = value.toString()

object MyWithRadix:
  def apply(digits: String): MyWithRadix =
    MyWithRadix(digits.toInt)
  given FromDigits.WithRadix[MyWithRadix] with
      def fromDigits(digits: String, radix: Int) =
        MyWithRadix(Integer.parseInt(digits, radix))

val z: MyWithRadix = 0x1000

@main def Test =
  println(i)
  // println(j)
  println(x)
  println(y)
  println(z)
