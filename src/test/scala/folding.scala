def foldRight[A,B](as: List[A], b: B)(f: (A, B) => B): B =
  as match {
    case Nil => b
    case x :: xs => f(x, foldRight(xs, b)(f))
  }

foldRight(List(1, 2, 3))


def foldLeft[A,B](as: List[A], b: B)(f: (B, A) => B): B =
as match {
  case Nil   => b
  case h :: t => foldLeft(t, f(b, h))(f)
}