package com.harmellaw.kotlin

tailrec fun <A, B> foldLeft(l: List<A>,
                    seed: B,
                    func: (b: B, a: A) -> B) : B {

    return when {
        l.isEmpty() -> seed
        else        -> foldLeft(l.tail, func(seed, l.head), func)
//        else -> throw IllegalArgumentException("The list was not empty")
    }
}

fun <A, B> foldLeftWithoutTailRecursion(l: List<A>,
                            seed: B,
                            func: (b: B, a: A) -> B) : B {

    return when {
        l.isEmpty() -> seed
        else        -> foldLeftWithoutTailRecursion(l.tail, func(seed, l.head), func)
    }
}

fun <A, B> foldRight(l: List<A>,
                     seed: B,
                     func: (b: B, a: A) -> B) : B {

    return foldLeft(l.asReversed(), seed, func)
}

fun <A, B> foldRightOverflows(l: List<A>,
                              seed: B,
                              func: (a: A, b: B) -> B) : B {

    return when {
        l.isEmpty() -> seed
        else -> func(l.head, foldRightOverflows(l.tail, seed, func))
    }
}