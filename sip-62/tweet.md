At this point it is most likely old news, but I'm happy to share that my first SIP (SIP-62 a.k.a betterFors) has been merged as an experimental feature to Scala 3. It will be part of Scala 3.6.0!

A small thread about the changes. 🧵

1/x

How to use it?

You need a Scala 3 version that includes the feature: `3.6.0-RC1` or later. Right now, those are only nightlies.

Then you can enable it by adding a scala feature import:

import scala.language.experimental.betterFors

or adding a compiler option:

-language:experimental.betterFors

2/x

What does it change/add?

Starting for comprehensions with aliases:

```scala
// Old way
val a = 1
for {
  b <- Some(2)
  c <- doSth(a)
} yield b + c
New Syntax:

// New way
for {
  a = 1
  b <- Some(2)
  c <- doSth(a)
} yield b + c
```

3/x

Simpler desugaring for pure aliases:
Current Desugaring:

```scala
// For the following code
for {
  a <- doSth(arg)
  b = a
} yield a + b

// Old Desugaring:
doSth(arg).map { a =>
  val b = a
  (a, b)
}.map { case (a, b) =>
  a + b
}

// New Desugaring (where possible):
doSth(arg).map { a =>
  val b = a
  a + b
}
```

4/x


Avoiding redundant map calls:

```scala
// For the following code
for {
  a <- List(1, 2, 3)
} yield a

// Old Desugaring:
List(1, 2, 3).map { a =>
  a
}

// New Desugaring:
List(1, 2, 3)
```

5/x

SIP details here: https://github.com/scala/improvement-proposals/pull/79
Implementation details here: https://github.com/scala/scala3/pull/20522

6/x