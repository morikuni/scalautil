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

	property("flatMap") = forAll{ (p: Int => Boolean, f: Boolean => (Int => Boolean), i: Int) =>
		Predicate(p).flatMap(b => Predicate(f(b))).run(i) == f(p(i))(i)
	}

	property("&&") = forAll{ (p1: Int => Boolean, p2: Int => Boolean, i: Int) =>
		(Predicate(p1) && Predicate(p2)).run(i) == (p1(i) && p2(i))
	}

	property("||") = forAll{ (p1: Int => Boolean, p2: Int => Boolean, i: Int) =>
		(Predicate(p1) || Predicate(p2)).run(i) == (p1(i) || p2(i))
	}

	property("!") = forAll{ (p: Int => Boolean, i: Int) =>
		(!Predicate(p)).run(i) == !(p(i))
	}
}
