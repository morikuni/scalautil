package scalautil

case class Predicate[A](run: A => Boolean){
	def apply(a: A): Boolean = run(a)

	def map(f: Boolean => Boolean): Predicate[A] = Predicate(a => f(run(a)))
	def flatMap(f: Boolean => Predicate[A]): Predicate[A] = Predicate((a: A) => f(run(a)).run(a))

	def &&(that: Predicate[A]): Predicate[A] = Predicate(a => {
		val b = run(a)
		b && that.run(a)
	})

	def ||(that: Predicate[A]): Predicate[A] = Predicate(a => {
		val b = run(a)
		b || that.run(a)
	})
}

object Predicate{
	def point[A](b: Boolean): Predicate[A] = Predicate(a => b)
}

