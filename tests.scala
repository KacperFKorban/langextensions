
def assertEqualResults(results: List[Result], expected: List[Result]) = {
  assert(results.toList == expected.toList, s"Expected: $expected, got: $results")
}

def test1 = {
  val events = List(
    Event.AddAnimal("1", "dog", 1, 1.0f),
    Event.AddAnimal("2", "dog", 2, 2.0f),
    Event.AddAnimal("3", "cat", 3, 3.0f),
    Event.Query(AttributeType.Age, AggregationType.Sum, List(QueryFilter.Species("dog"))) // 3
  )
  val results = resultStream(events)
  assertEqualResults(results, List(Result.Age(3)))
}

def test2 = {
  val events = List(
    Event.AddAnimal("1", "dog", 1, 1.0f),
    Event.AddAnimal("2", "dog", 2, 2.0f),
    Event.AddAnimal("3", "cat", 3, 3.0f),
    Event.Query(AttributeType.Age, AggregationType.Average, List(QueryFilter.Species("dog"))) // 1.5
  )
  val results = resultStream(events)
  assertEqualResults(results, List(Result.Age(1)))
}

def test3 = {
  val events = List(
    Event.AddAnimal("1", "dog", 1, 1.0f),
    Event.AddAnimal("2", "dog", 2, 2.0f),
    Event.AddAnimal("3", "cat", 3, 3.0f),
    Event.Query(AttributeType.Height, AggregationType.Sum, List(QueryFilter.Species("dog"))) // 3.0
  )
  val results = resultStream(events)
  assertEqualResults(results, List(Result.Height(3.0f)))
}

def test4 = {
  val events = List(
    Event.AddAnimal("1", "dog", 1, 1.0f),
    Event.AddAnimal("2", "dog", 2, 2.0f),
    Event.AddAnimal("3", "cat", 3, 3.0f),
    Event.Query(AttributeType.Height, AggregationType.Average, List(QueryFilter.Species("dog"))) // 1.5
  )
  val results = resultStream(events)
  assertEqualResults(results, List(Result.Height(1.5f)))
}

def test5 = {
  val events = List(
    Event.AddAnimal("1", "dog", 1, 1.0f),
    Event.AddAnimal("2", "dog", 2, 2.0f),
    Event.AddAnimal("3", "cat", 3, 3.0f),
    Event.Query(AttributeType.Age, AggregationType.Sum, List(QueryFilter.Species("dog"), QueryFilter.Age(1))), // 1
    Event.Query(AttributeType.Age, AggregationType.Sum, List(QueryFilter.Species("dog"), QueryFilter.Age(2))), // 2
    Event.AddAnimal("4", "dog", 1, 1.0f),
    Event.Query(AttributeType.Age, AggregationType.Sum, List(QueryFilter.Species("dog"), QueryFilter.Age(1))) // 2
  )
  val results = resultStream(events)
  assertEqualResults(results, List(Result.Age(1), Result.Age(2), Result.Age(2)))
}

@main
def test =
  test1
  test2
  test3
  test4
  test5
  println("All tests passed")

