//> using scala 3.nightly
//> using options -feature -Xfatal-warnings
//> using options -source:future

// https://contributors.scala-lang.org/t/pre-sip-named-tuples/6403/110

import scala.language.experimental.namedTuples

type Person = (name: String, age: Int)
val bob: Person = (name = "Bob", age = 33)

def usage2 =
  bob match
    case (name = n, age = 22) => n

def analyzeString(input: String): (length: Int, vowelsCount: Int) = {
  val it = input.iterator
  var l = 0
  var count = 0
  while (it.hasNext) {
    val c = it.next()
    l += 1
    if ("AEIOUaeiou".contains(c)) count += 1
  }
  (length = l, vowelsCount = count)
}

case class PersonRow(id: Int, name: String, age: Int)
case class AddressRow(id: Int, address: String)

type Table1 = List[PersonRow]
type Table2 = List[AddressRow]

def joinTables(t1: Table1, t2: Table2): List[(id: Int, name: String, age: Int, address: String)] =
  for
    person <- t1
    address <- t2
    if person.id == address.id
  yield (id = person.id, name = person.name, age = person.age, address = address.address)

case class City(name: String, population: Int)

def getCityInfo(city: City) =
  city match
    case c @ City(name = "Paris") => "Paris is the capital of France"
    case City(population = 0) => "This city is uninhabited"
    case City(population = p, name = n) => s"$n has $p inhabitants"

@main
def useCityInfo =
  println(getCityInfo(City("Paris", 0)))
  println(getCityInfo(City("London", 9_000_000)))
  println(getCityInfo(City("New York", 8_000_000)))
