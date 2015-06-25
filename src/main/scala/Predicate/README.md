#Predicate

Predicate extends Function `A => Boolean`.

## Usage

Compose with `&&` or `||`.

```scala
import scalautil._

val containsX = Predicate[String](_.contains("x"))
val containsY = Predicate[String](_.contains("y"))

val containsXAndY = containsX && containsY
val containsXOrY = containsX || containsY

println(containsXAndY("x")) //false
println(containsXAndY("y")) //false
println(containsXAndY("xy")) //true

println(containsXOrY("x")) //true
println(containsXOrY("y")) //true
println(containsXOrY("xy")) //true
```

Compose with `for`.

```scala
import scalautil._

val containsX = Predicate[String](_.contains("x"))
val containsY = Predicate[String](_.contains("y"))

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
