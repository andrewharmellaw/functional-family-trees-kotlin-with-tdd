package com.harmellaw.kotlin

val <T> List<T>.tail: List<T> get() = drop(1)
val <T> List<T>.head: T get() = first()
