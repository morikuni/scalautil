package scalautil

case class Predicate[A](run: A => Boolean){
	import Predicate._

	def apply(a: A): Boolean = run(a)

	def map(f: Boolean => Boolean): Predicate[A] = Predicate(a => f(run(a)))

	def flatMap(f: Boolean => Predicate[A]): Predicate[A] = Predicate(a => f(run(a)).run(a))

	def &&(that: => Predicate[A]): Predicate[A] = Predicate(a => {
		val b = run(a)
		lazy val p = that
		b && p.run(a)
	})

	def and(that: => Predicate[A]): Predicate[A] = &&(that)

	def ||(that: => Predicate[A]): Predicate[A] = Predicate(a => {
		val b = run(a)
		lazy val p = that
		b || p.run(a)
	})

	def or(that: => Predicate[A]): Predicate[A] = ||(that)

	def unary_!(): Predicate[A] = Predicate(a => !run(a))

	def inverse: Predicate[A] = !this

	def !&&(that: => Predicate[A]): Predicate[A] = !(&&(that))

	def nand(that: => Predicate[A]): Predicate[A] = !&&(that)

	def !||(that: => Predicate[A]): Predicate[A] = !(||(that))

	def nor(that: => Predicate[A]): Predicate[A] = !||(that)
}

object Predicate{
	import scala.language.implicitConversions

	implicit def predicateAsFunction[A](predicate: Predicate[A]): A => Boolean = predicate.run

	def point[A](b: Boolean): Predicate[A] = Predicate(_ => b)

	def map2[A](p1: Predicate[A], p2: Predicate[A])(f: (Boolean, Boolean) => Boolean) = for{
		b1 <- p1
		b2 <- p2
	} yield f(b1, b2)
}

