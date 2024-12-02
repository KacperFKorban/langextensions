//> using scala 3.nightly
//> using options -feature -Xfatal-warnings
//> using options -source:future

import scala.language.experimental.into

enum MyList[+A]:
  case MyNil
  case MyCons(head: A, tail: MyList[A])

  def toList: List[A] = this match
    case MyNil => Nil
    case MyCons(h, t) => h +: t.toList

given [A]: Conversion[MyList[A], List[A]] =
  (x: MyList[A]) => x.toList

def loopOver[A](it: into List[A])(f: A => Unit): Unit =
  it.iterator.foreach(f)

def loopAggr[A](it: List[A])(f: A => into List[A]): List[A] =
  it.iterator.foldLeft(List.empty[A])((acc, x) => acc ++ f(x))

def intoUsage =
  val myLst = MyList.MyCons(1, MyList.MyNil)
  loopOver(myLst)(x => println(x))
  loopAggr(myLst.toList)(x => MyList.MyCons(x, MyList.MyNil))
