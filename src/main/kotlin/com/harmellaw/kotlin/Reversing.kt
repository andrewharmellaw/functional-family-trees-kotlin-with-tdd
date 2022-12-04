package com.harmellaw.kotlin

fun <T> reverse(list: List<T>): Any {
    return foldLeft(list, emptyList<T>(),
        fun (acc: List<T>, head: T): List<T> = listOf(head) + acc)
}