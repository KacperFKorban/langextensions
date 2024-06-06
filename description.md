# Me at the language extensions Zoo

TLDR: Solve the problem described below using as many language extensions as possible and as little code as possible.

## Problem Description

Implement a program that processes two types of events for a zoo's animal population: update events and query events. The program should maintain internal data about the animals and respond to queries based on this data.

### Functionality

Update Event:
Adds or updates information about an animal.
Updates will be given in the form of an event containing the animal's ID, species, age, and height.
If an animal with the given ID already exists, its information should be updated with the new data provided.

Query Event:
Responds to a query requesting specific information about the animal population.
Possible queries include:
Sum or average age of animals filtered by species or other criteria.
Sum or average height of animals filtered by species or other criteria.

The given model of events and data is as follows:

```scala
case class Animal(id: String, species: String, age: Int, height: Float)
enum AttributeType:
  case Age, Height
enum AggregationType:
  case Sum, Average
enum QueryFilter:
  case Species(species: String)
  case Age(age: Int)
  case Height(height: Float)
enum Event:
  case AddAnimal(animalId: String, species: String, age: Int, height: Float)
  case Query(resultAttribute: AttributeType, aggType: AggregationType, filters: List[QueryFilter])
enum Result:
  case Height(value: Float)
  case Age(value: Int)
  case Unit
```

and the desired exported function should heve the following signature:

```scala
def resultStream(events: List[Event]): List[Result] = ...
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
// TODO copy the solution here
```

## Rules

A solution that solves the problem is one that passes all the attached tests.

Out of all the solutions that solve the problem and use at least (3 language extensions), the solutions will be sorted by (number of language extensions used) divided by (number of characters in the solution). The solution with the highest value will be considered the best solution.

Language imports do not count as part of the code length.

The solution must be submitted as a single Scala file, with the following structure:

```scala
//> using scala 3.3.3

import scala.language.implicitConversions
import scala.language.dynamics
...

case class Animal(id: String, species: String, age: Int, height: Float)
enum AttributeType:
  case Age, Height
enum AggregationType:
  case Sum, Average
enum QueryFilter:
  case Species(species: String)
  case Age(age: Int)
  case Height(height: Float)
enum Event:
  case AddAnimal(animalId: String, species: String, age: Int, height: Float)
  case Query(resultAttribute: AttributeType, aggType: AggregationType, filters: List[QueryFilter])
enum Result:
  case Height(value: Float)
  case Age(value: Int)
  case Unit

// END HEADER

def resultStream(events: List[Event]): List[Result] = {
  ...
}
```

The header of the file should contain the version of Scala version used (as a using directive), the language extensions used (every extension in a separate line) and the template code. The rest of the file should contain the implementation of the `resultStream` function and any other necessary code.

Hard rules:
- The solution must be submitted as a single Scala file using Scala-CLI (I am lazy)
- The solution has to use at least 3 language extensions
- The solution has to solve the problem (pass all tests). Yes, you can hardcode all the test if you find that to be the best solution :D
- The solution can use any version of Scala 3 (stable or not)
