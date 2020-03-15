# Functional Family Trees

In functional programming there a few core constructs which you'll keep coming back to - either directly (they keep popping up) or indirectly (you find them when you lift the lid on something else).  I've found it super-useful in the last few months to keep a visual track of the hierarchies of these ur-elements, referring to it whenever I wanted to lash some current concept back to something simple and concrete.

Here is the hierarchy I've built up so far:

## foldLeft: 
- takes an algebraic data structure (DEFINITION) and a bi-arg function (DEFINIITON) with a seed value, and applies the function to each element in the datastructure, associating from the left hand end
- produces an new accumulated output value with the same type as the seed value
- use when ...
- implemented in terms of case / match
- can be implemented in a tail recursive (stack-safe) fashion
  - e.g. 

## foldRight:
- takes an algebraic data structure and a bi-arg function with a seed value, and applies the function to each element in the data-structure, associating from the right hand end
- produces an new accumulated output value with the same type as the seed value
- use when ...
- can be implemented in terms of case / match but this isn't stack safe as it's not tail-recursive
- can be also be implemented in terms of foldLeft which means it can be stack safe
  - e.g. 

##  append:
- takes two collections ...
- use when ...
- implemented in terms of foldRight, and simply replaces the `Nil` constructor of the first list with the second list
  - e.g. def append[A](l: List[A], r: List[A]): List[A] = foldRight(l, r)(Cons(_,_))

## concat:
- takes a collection, ...
- use when ...
- implemented in terms of foldRight, and append
  - e.g. def concat[A](l: List[List[A]]): List[A] = foldRight(l, Nil:List[A])(append)
	 
## map:
- takes an algebraic data structure and a uni-arg function, and applies the function to each element in the datastructure.
- produces a new output value with the same type as the input algebraic data structure
- contained value type is determined by the supplied uni-arg function
- use when ...
- typically implemented in terms of foldLeft (or foldRight)
  - seed value is an empty algebraic data type with the same type as the input algebraic data structure
  - e.g. def map[A,B](l: List[A])(f: A => B): List[B] = foldRight(l, Nil:List[B])((h,t) => Cons(f(h),t))

## sum:
- takes a collection, summing each value in turn to produce a result
- use when ...
- implemented in terms of foldLeft, with a seed value of '0' / '0.0' and a bi-arg function '_ + _'
  - e.g. def sum(l: List[Int]) = foldLeft(l, 0)(_ + _)

## product:
- takes a collection, multiplying each value in turn to produce a result
- use when ...
- implemented in terms of foldLeft, with a seed value of '1' / '1.0' and a bi-arg function '_ * _' 
  - e.g. def product(l: List[Double]) = foldLeft(l, 1.0)(_ * _)

## length:
- takes a collection, and increments a counter by one for each element to produce a result
- use when ...
- implemented in terms of foldLeft, with a seed value of '0' and a uni-arg incrementing function '(acc, head) => acc + 1' 
  - e.g. def length[A](l: List[A]): Int = foldLeft(l, 0)((acc,h) => acc + 1)

## reverse:
- takes a collection, and ...
- use when ...
- implemented in terms of foldLeft, with a seed value of an empty collection of the same type as the input collection and a function which puts the current value as the head of the accumulator '((acc, head) => Cons(head, acc))'
  - e.g. def reverse[A](l: List[A]): List[A] = foldLeft(l, List[A]())((acc, head) => Cons(head ,acc))

## flatMap:
- takes a collection of collections, and ...
- use when ...
- implemented in terms of concat and map, with ...
  - e.g. def flatMap[A,B](l: List[A])(f: A => List[B]): List[B] = concat(map(l)(f))

## filter:
- takes a collection and ...
- use when ...
- implemented in terms of foldLeft with ...
  - e.g. def filter[A](l: List[A])(f: A => Boolean): List[A] = foldRight(l, Nil:List[A])((h,t) => if (f(h)) Cons(h,t) else t)  **** NOTE THIS EXAMPLE IS FOLDRIGHT!!! ****

## zipWith:
  - takes ...
  - produces ...
  - use when ...
  - can be implemented in terms of case / match
    - e.g. 






