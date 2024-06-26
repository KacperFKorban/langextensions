//> using scala 3.3.3

/**
  * Links:
  * - https://scala-lang.org/files/archive/spec/2.13/06-expressions.html#assignments
  * - https://github.com/scala-native/scala-native/blob/main/nativelib/src/main/scala/scala/scalanative/unsafe/Ptr.scala
  * - https://www.scala-lang.org/files/archive/spec/2.11/06-expressions.html
  */ 

class ClassWithAGetterAndASetter:
  private var _value: Int = 0
  def value: Int = _value
  def value_=(newValue: Int): Unit = _value = newValue

class XD(private var i: Int):
  def unary_! : Int = i
  def `unary_!_=`(i: Int): Unit = this.i = i

def eqUsage =
  val c = ClassWithAGetterAndASetter()

  println(c.value)

  c.value = 2 // c.value_=(2)

  println(c.value)

  val xd = XD(1)

  println(!xd)

  !xd = 2 // xd.`unary_!_=`(2)

  println(!xd)
