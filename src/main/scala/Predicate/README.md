#Predicate

Predicate extends Function `A => Boolean`.

## Usage

```scala
import scalautil._

val containsX = Predicate[String](_.contains("x"))
val containsY = Predicate[String](_.contains("y"))

val containsXAndNotContainsY = containsX && !containsY

println(containsXAndNotContainsY("x")) //true
println(containsXAndNotContainsY("y")) //false
println(containsXAndNotContainsY("xy")) //false

val exclusiveOr = for{
	x <- containsX
	y <- containsY
} yield (x && !y) || (!x && y)

println(exclusiveOr("x")) //true
println(exclusiveOr("y")) //true
println(exclusiveOr("xy")) //false
```
