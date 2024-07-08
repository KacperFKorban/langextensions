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
