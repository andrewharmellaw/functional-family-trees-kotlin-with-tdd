import com.harmellaw.kotlin.foldLeft
import com.harmellaw.kotlin.foldLeftWithoutTailRecursion
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import kotlin.test.assertFailsWith

class `1-FoldLeftTests` {

    // We use this as a shorthand to avoid needing a function literal after the first test.  We use it with "::sum"
    private fun sum(l: Int, r: Int) : Int = l + r

    // 0. Before we fold, lets test out our new "sum" function
    @Test
    fun `sum of two integers`() {
        assertEquals(1 + 2, sum(1, 2))
    }

    // 1. The simplest call to our foldLeft - on an empty list with an "add" function
    @Test
    fun `folding left on an empty list yields the seed no matter what the function`() {
        val seed = 1
        val emptyList: List<Int> = emptyList()
        assertEquals(seed, foldLeft(emptyList, seed, fun(a, b) = a + b))
    }

    // 2. Next we can fold on a single-item list
    @Test
    fun `folding left on a single-item list yields the result of folding that item into the seed`() {
        val seed = 0
        assertEquals(seed + 1, foldLeft(listOf(1), seed, ::sum))
    }

    // 3. Now we can fold on a three-item list
    @Test
    fun `folding left on a three-item list yields the seed plus the first item plus the second item plus the third`() {
        val seed = 0
        assertEquals(seed + 1 + -2 + 3, foldLeft(listOf(1, -2, 3), seed, ::sum))
    }

    // 4. Now we can fold on a very large list - it'll' stack overflow as we're not tail-recursing
    @Test
    fun `folding left without tail recursion on a very large list stack overflows`() {
        val seed = 1
        val massiveList = mutableListOf<Int>()
        for (i in 1..10000) {
            massiveList.add(i)
        }

        assertFailsWith(StackOverflowError::class) {
            foldLeftWithoutTailRecursion(massiveList, seed, ::sum)
        }
    }

    // 5. If we make foldLeft tail-recursive we won't stack overflow on large lists
    @Test
    fun `folding left on a very large list doesn't stack overflow when we tail-recurse`() {
        val seed = 1
        val massiveList = mutableListOf<Int>()
        for (i in 1..10000) {
            massiveList.add(i)
        }
        assertEquals(50005001, foldLeft(massiveList, seed, ::sum))
    }
}
