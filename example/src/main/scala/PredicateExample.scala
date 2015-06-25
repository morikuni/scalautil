import scalautil.Predicate

object PredicateExample{
	def stringPredicate(f: String => Boolean): Predicate[String] = Predicate(s => f(s))

	def main(args: Array[String]): Unit = {
		val isInt = stringPredicate(s => util.Try(s.toInt).isSuccess)
		val containsZero = stringPredicate(_.contains("0"))

		val predicateOr = for{
			a <- isInt
			b <- containsZero
		} yield !(a || b)

		val predicateAnd = !isInt && !containsZero

		//predicateOr == predicateAnd (De Morgan's laws)

		println("when 5")
		println(predicateOr("5")) //false
		println(predicateAnd("5")) //false

		println("when 0")
		println(predicateOr("0")) //false
		println(predicateAnd("0")) //false

		println("when a0")
		println(predicateOr("a0")) //false
		println(predicateAnd("a0")) //false

		println("when a")
		println(predicateOr("a")) //true
		println(predicateAnd("a")) //true
	}
}
