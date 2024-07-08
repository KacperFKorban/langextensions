# Me at the language extensions Zoo

TLDR: Solve the problem described below using as many language extensions as possible and as little code as possible.

## Problem Description
Implement a program that processes two types of events for a zoo's animal population: update events and query events. The program should maintain internal data about the animals and respond to queries based on this data. All data and events are provided as strings, and the program should use regular expressions to parse and process these strings.

### Functionality

#### Update Event:

Adds or updates information about an animal.
Updates will be given in the form of a string containing the animal's ID, species, age, and height.
If an animal with the given ID already exists, its information should be updated with the new data provided.

#### Query Event:

Responds to a query requesting specific information about the animal population.
Possible queries include:
Sum or average age of animals filtered by species or other criteria.
Sum or average height of animals filtered by species or other criteria.

### Event String Format:

#### Update Event:

"AddAnimal:animalId:<id>,species:<species>,age:<age>,height:<height>"

#### Query Event:

"Query:resultAttribute:<attribute>,aggType:<aggregation>,filters:<filter1>,<filter2>,..."

### Filter String Format:
"Species:<species>"
"Age:<age>"
"Height:<height>"

The desired exported function should have the following signature:

```scala
def resultStream(events: List[String]): List[String] = ...
```

## Actual objective

The base goal of this exercise is to implement the `resultStream` function that solves the problem described above.
However, the solutions will be evaluated based on the amount of language extensions used in the implementation of the solution, while minimizing the amount of code written. Simply put, the best code will be the one that:
- Solves the problem
- Uses the most language extensions (at least 3)
- Uses the least amount of code

## Example solution

To make it easier to focus on the language extensions, here is an example solution that solves the problem without using any language extensions:

```scala
//> using scala 3.nightly

// END HEADER

import scala.util.matching.Regex

case class Animal(id: String, species: String, age: Int, height: Float)

def resultStream(events: List[String]): List[String] = {
  val AddAnimalPattern: Regex = "AddAnimal:animalId:(.*),species:(.*),age:(\\d+),height:(\\d+\\.\\d+)".r
  val QueryPattern: Regex = "Query:resultAttribute:(.*),aggType:(.*),filters:(.*)".r
  val SpeciesFilterPattern: Regex = "Species:(.*)".r
  val AgeFilterPattern: Regex = "Age:(\\d+)".r
  val HeightFilterPattern: Regex = "Height:(\\d+\\.\\d+)".r

  def go(events: List[String], animals: Map[String, Animal]): List[String] = {
    events match {
      case Nil => Nil
      case head :: tail => head match {
        case AddAnimalPattern(id, species, age, height) =>
          val animal = Animal(id, species, age.toInt, height.toFloat)
          go(tail, animals + (id -> animal))

        case QueryPattern(resultAttribute, aggType, filtersStr) =>
          val filters = filtersStr.split(",").toList.map {
            case SpeciesFilterPattern(species) => (animal: Animal) => animal.species == species
            case AgeFilterPattern(age) => (animal: Animal) => animal.age == age.toInt
            case HeightFilterPattern(height) => (animal: Animal) => animal.height == height.toFloat
            case _ => (_: Animal) => true
          }

          val filteredAnimals =
            for {
              animal <- animals.values
              if filters.forall(filter => filter(animal))
            } yield animal

          val result = (resultAttribute, aggType) match {
            case ("Age", "Sum") =>
              s"Age(${filteredAnimals.map(_.age.toFloat).sum})"
            case ("Age", "Average") =>
              if (filteredAnimals.nonEmpty) s"Age(${filteredAnimals.map(_.age.toFloat).sum / filteredAnimals.size})"
              else "Age(0)"
            case ("Height", "Sum") =>
              s"Height(${filteredAnimals.map(_.height).sum})"
            case ("Height", "Average") =>
              if (filteredAnimals.nonEmpty) s"Height(${filteredAnimals.map(_.height).sum / filteredAnimals.size})"
              else "Height(0.0)"
          }

          result :: go(tail, animals)
      }
    }
  }

  go(events, Map.empty)
}
```

## Rules

A solution that solves the problem is one that passes all the attached tests.

Out of all the solutions that solve the problem and use at least (3 language extensions), the solutions will be sorted by (number of language extensions used) divided by (number of characters in the solution). The solution with the highest value will be considered the best solution.

Language imports do not count as part of the code length.

The solution must be submitted as a single Scala file, with the following structure:

```scala
//> using scala 3.nightly

import scala.language.implicitConversions
import scala.language.dynamics
...

// END HEADER

def resultStream(events: List[String]): List[String] = {
  ...
}
```

The header of the file should contain the version of Scala version used (as a using directive), the language extensions used (every extension in a separate line) and the template code. The rest of the file should contain the implementation of the `resultStream` function and any other necessary code.

Hard rules:
- The solution must be submitted as a single Scala file using Scala-CLI (I am lazy)
- The solution has to use at least 3 language extensions
- The solution has to solve the problem (pass all tests). Yes, you can hardcode all the test if you find that to be the best solution :D
- The solution can use any version of Scala 3 (stable or not)
- No unused code. Some language constructs will most likely not be very useful and that's fine. They just need to be part of the solution.
