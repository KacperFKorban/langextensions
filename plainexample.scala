//> using scala 3.3.3

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
  def go(events: List[Event], animals: Map[String, Animal]): List[Result] = {
    events match {
      case List() => List.empty
      case Event.AddAnimal(animalId, species, age, height) :: tail =>
        go(tail, animals + (animalId -> Animal(animalId, species, age, height)))
      case Event.Query(res, aggTpe, filters) :: tail =>
        val filteredAnimals = animals.filter { case (_, animal) =>
          filters.forall {
            case QueryFilter.Species(species) => animal.species == species
            case QueryFilter.Age(age) => animal.age == age
            case QueryFilter.Height(height) => animal.height == height
          }
        }
        (res, aggTpe) match {
          case (AttributeType.Age, AggregationType.Sum) =>
            Result.Age(filteredAnimals.values.map(_.age).sum) :: go(tail, animals)
          case (AttributeType.Age, AggregationType.Average) =>
            Result.Age(filteredAnimals.values.map(_.age).sum / filteredAnimals.size) :: go(tail, animals)
          case (AttributeType.Height, AggregationType.Sum) =>
            Result.Height(filteredAnimals.values.map(_.height).sum) :: go(tail, animals)
          case (AttributeType.Height, AggregationType.Average) =>
            Result.Height(filteredAnimals.values.map(_.height).sum / filteredAnimals.size) :: go(tail, animals)
        }
    }
  }
  go(events, Map.empty)
}
