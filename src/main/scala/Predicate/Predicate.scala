package scalautil

case class Predicate[A](run: A => Boolean){
	def apply(a: A): Boolean = run(a)

	def map(f: Boolean => Boolean): Predicate[A] = Predicate(a => f(run(a)))

	def flatMap(f: Boolean => Predicate[A]): Predicate[A] = Predicate(a => f(run(a)).run(a))

	def &&(that: Predicate[A]): Predicate[A] = Predicate(a => {
		val b = run(a)
		b && that.run(a)
	})

	def and(that: Predicate[A]): Predicate[A] = &&(that)

	def ||(that: Predicate[A]): Predicate[A] = Predicate(a => {
		val b = run(a)
		b || that.run(a)
	})

	def or(that: Predicate[A]): Predicate[A] = ||(that)

	def unary_!(): Predicate[A] = Predicate(a => !run(a))

	def inverse: Predicate[A] = !this
}

object Predicate{
	import scala.language.implicitConversions

	implicit def predicateAsFunction[A](predicate: Predicate[A]): A => Boolean = predicate.run

	def point[A](b: Boolean): Predicate[A] = Predicate(_ => b)
}

