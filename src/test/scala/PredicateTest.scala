import org.scalacheck.Properties
import org.scalacheck.Prop.forAll

import scalautil._

object PredicateTest extends Properties("Predicate"){
	property("apply") = forAll{ (p: Int => Boolean, i: Int) =>
		Predicate(p)(i) == p(i)
	}

	property("map") = forAll{ (p: Int => Boolean, f: Boolean => Boolean, i: Int) =>
		Predicate(p).map(f).run(i) == f(p(i))
	}

	property("map2") = forAll{ (p1: Int => Boolean, p2: Int => Boolean, f: (Boolean, Boolean) => Boolean, i: Int) =>
		Predicate.map2(Predicate(p1), Predicate(p2))(f).run(i) == f(p1(i), p2(i))
	}

	property("flatMap") = forAll{ (p: Int => Boolean, f: Boolean => (Int => Boolean), i: Int) =>
		Predicate(p).flatMap(b => Predicate(f(b))).run(i) == f(p(i))(i)
	}

	property("and") = forAll{ (p1: Int => Boolean, p2: Int => Boolean, i: Int) =>
		(Predicate(p1) and Predicate(p2)).run(i) == (p1(i) && p2(i))
	}

	property("or") = forAll{ (p1: Int => Boolean, p2: Int => Boolean, i: Int) =>
		(Predicate(p1) or Predicate(p2)).run(i) == (p1(i) || p2(i))
	}

	property("inverse") = forAll{ (p: Int => Boolean, i: Int) =>
		Predicate(p).inverse.run(i) == !(p(i))
	}

	property("nand") = forAll{ (p1: Int => Boolean, p2: Int => Boolean, i: Int) =>
		(Predicate(p1) nand Predicate(p2)).run(i) == !(p1(i) && p2(i))
	}

	property("nor") = forAll{ (p1: Int => Boolean, p2: Int => Boolean, i: Int) =>
		(Predicate(p1) nor Predicate(p2)).run(i) == !(p1(i) || p2(i))
	}
}
