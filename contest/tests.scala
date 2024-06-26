def assertEqualResults(results: List[String], expected: List[String]) = {
  assert(results == expected, s"Expected: $expected, got: $results")
}

def test1 = {
  val events = List(
    "AddAnimal:animalId:1,species:dog,age:1,height:1.0",
    "AddAnimal:animalId:2,species:dog,age:2,height:2.0",
    "AddAnimal:animalId:3,species:cat,age:3,height:3.0",
    "Query:resultAttribute:Age,aggType:Sum,filters:Species:dog" // Age(3)
  )
  val results = resultStream(events)
  assertEqualResults(results, List("Age(3.0)"))
}

def test2 = {
  val events = List(
    "AddAnimal:animalId:1,species:dog,age:1,height:1.0",
    "AddAnimal:animalId:2,species:dog,age:2,height:2.0",
    "AddAnimal:animalId:3,species:cat,age:3,height:3.0",
    "Query:resultAttribute:Age,aggType:Average,filters:Species:dog" // Age(1.5)
  )
  val results = resultStream(events)
  assertEqualResults(results, List("Age(1.5)"))
}

def test3 = {
  val events = List(
    "AddAnimal:animalId:1,species:dog,age:1,height:1.0",
    "AddAnimal:animalId:2,species:dog,age:2,height:2.0",
    "AddAnimal:animalId:3,species:cat,age:3,height:3.0",
    "Query:resultAttribute:Height,aggType:Sum,filters:Species:dog" // Height(3.0)
  )
  val results = resultStream(events)
  assertEqualResults(results, List("Height(3.0)"))
}

def test4 = {
  val events = List(
    "AddAnimal:animalId:1,species:dog,age:1,height:1.0",
    "AddAnimal:animalId:2,species:dog,age:2,height:2.0",
    "AddAnimal:animalId:3,species:cat,age:3,height:3.0",
    "Query:resultAttribute:Height,aggType:Average,filters:Species:dog" // Height(1.5)
  )
  val results = resultStream(events)
  assertEqualResults(results, List("Height(1.5)"))
}

def test5 = {
  val events = List(
    "AddAnimal:animalId:1,species:dog,age:1,height:1.0",
    "AddAnimal:animalId:2,species:dog,age:2,height:2.0",
    "AddAnimal:animalId:3,species:cat,age:3,height:3.0",
    "Query:resultAttribute:Age,aggType:Sum,filters:Species:dog,Age:1", // Age(1)
    "Query:resultAttribute:Age,aggType:Sum,filters:Species:dog,Age:2", // Age(2)
    "AddAnimal:animalId:4,species:dog,age:1,height:1.0",
    "Query:resultAttribute:Age,aggType:Sum,filters:Species:dog,Age:1" // Age(2)
  )
  val results = resultStream(events)
  assertEqualResults(results, List("Age(1.0)", "Age(2.0)", "Age(2.0)"))
}

@main
def test =
  test1
  test2
  test3
  test4
  test5
  println("All tests passed")
