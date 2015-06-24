#Predicate

Predicate extends Function `A => Boolean`.

## Use Case

Compose with && or ||.

```scala
import scalautil._

val containsX = Predicate[String](s => s.contains("x"))
val containsY = Predicate[String](s => s.contains("y"))

val containsXAndY = containsX && containsY
val containsXOrY = containsX || containsY

println(containsXAndY("x")) //false
println(containsXAndY("y")) //false
println(containsXAndY("xy")) //true

println(containsXOrY("x")) //true
println(containsXOrY("y")) //true
println(containsXOrY("xy")) //true
```

Compose with for.

```scala
import scalautil._

val containsX = Predicate[String](s => s.contains("x"))
val containsY = Predicate[String](s => s.contains("y"))

val containsXAndY = for{
	x <- containsX
	y <- containsY
} yield x && y
val containsXOrY = for{
	x <- containsX
	y <- containsY
} yield x || y

println(containsXAndY("x")) //false
println(containsXAndY("y")) //false
println(containsXAndY("xy")) //true

println(containsXOrY("x")) //true
println(containsXOrY("y")) //true
println(containsXOrY("xy")) //true
```
