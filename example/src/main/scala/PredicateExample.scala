package scalautil

object PredicateExample{
	def stringPredicate(f: String => Boolean): Predicate[String] = Predicate(s => f(s))

	def main(args: Array[String]): Unit = {
		val isInt = stringPredicate(s => util.Try(s.toInt).isSuccess)
		val containsZero = stringPredicate(_.contains("0"))

		val predicateor = for{
			a <- isInt
			b <- containsZero
		} yield (a || b)

		val predicateAnd = for{
			a <- isInt
			b <- containsZero
		} yield (a && b)

		val predicate1 = (isInt || containsZero) && isInt
		val predicate2 = (isInt || containsZero) || isInt
		val predicate3 = (isInt && containsZero) && isInt
		val predicate4 = (isInt && containsZero) || isInt

		println("when 5")
		println(predicateor("5")) //true
		println(predicateAnd("5")) //false
		println(predicate1("5")) //true
		println(predicate2("5")) //true
		println(predicate3("5")) //false
		println(predicate4("5")) //true

		println("when 0")
		println(predicateor("0")) //true
		println(predicateAnd("0")) //true
		println(predicate1("0")) //true
		println(predicate2("0")) //true
		println(predicate3("0")) //true
		println(predicate4("0")) //true

		println("when a0")
		println(predicateor("a0")) //true
		println(predicateAnd("a0")) //false
		println(predicate1("a0")) //false
		println(predicate2("a0")) //true
		println(predicate3("a0")) //false
		println(predicate4("a0")) //false

		println("when a")
		println(predicateor("a")) //false
		println(predicateAnd("a")) //false
		println(predicate1("a")) //false
		println(predicate2("a")) //false
		println(predicate3("a")) //false
		println(predicate4("a")) //false

	}
}
