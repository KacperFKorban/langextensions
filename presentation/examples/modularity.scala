//> using scala 3.nightly
//> using options -feature -Xfatal-warnings
//> using options -source:future

import scala.util.chaining.*

import scala.language.experimental.modularity

trait Ordering:
  type T
  def compare(t1:T, t2: T): Int

class OrderedSet(tracked val ord: Ordering):
  type Set = List[ord.T]

  def empty: Set = Nil

  extension (s: Set)
    def add(x: ord.T): Set = x :: remove(x)
    def remove(x: ord.T): Set = s.filter(e => ord.compare(x, e) != 0)
    def contains(x: ord.T): Boolean = s.exists(e => ord.compare(x, e) == 0)

object intOrdering extends Ordering:
  type T = Int
  def compare(t1: T, t2: T): Int = t1 - t2

val IntSet = new OrderedSet(intOrdering)

def Test =
  import IntSet.*
  val set = IntSet.empty.add(6).add(8).add(23)
  assert(!set.contains(7))
  assert(set.contains(8))

// Type classes

trait Show:
  type Self
  extension (x: Self) def show: String

trait SemiGroup:
  type Self
  extension (x: Self) def combine(y: Self): Self

trait Monoid extends SemiGroup:
  def unit: Self

// Instances

given Int is Show:
  extension (x: Int) def show = x.toString

given [T: Show] => List[T] is Show:
  extension (xs: List[T]) def show = xs.map(_.show).mkString("[", ", ", "]")

// Usages

def combineAll[T: Monoid as m](xs: List[T]): T =
  xs.foldLeft(m.unit)(_.combine(_))

def combineAll1[T: Monoid](xs: List[T]): T =
  xs.foldLeft(T.unit)(_.combine(_))

def combineAllAndPrint[T: {Monoid, Show}](xs: List[T]): T =
  xs.foldLeft(T.unit)(_.combine(_)).tap(x => println(T.show(x)))

def combineAllAndPrint1[T: {Monoid as m, Show as s}](xs: List[T]): T =
  xs.foldLeft(m.unit)(_.combine(_)).tap(x => println(s.show(x)))

// Old style

trait ShowOld[T]:
  extension (x: T) def show: String

trait SemiGroupOld[T]:
  extension (x: T) def combine(y: T): T

trait MonoidOld[T] extends SemiGroupOld[T]:
  def unit: T

given ShowOld[Int] with
  extension (x: Int) def show = x.toString

given [T: ShowOld] => ShowOld[List[T]] with
  extension (xs: List[T]) def show = xs.map(_.show).mkString("[", ", ", "]")

def combineAllOld[T](xs: List[T])(using m: MonoidOld[T]): T =
  xs.foldLeft(m.unit)(_.combine(_))
