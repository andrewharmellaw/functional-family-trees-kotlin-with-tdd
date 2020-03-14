import com.harmellaw.kotlin.foldRightOverflows
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

class FoldRightTests {

    private fun product(left: Int, right: Int) : Int = left * right

    // 1. Simplest version - empty list
    @Test
    fun `fold on an empty list yields the seed`() {
        val seed = 1;
        assertEquals(seed, foldRightOverflows(emptyList(), seed, ::product))
    }

    // 2. Now we need our first function to pass in - lets test that
    @Test
    fun `Product of two integers`() {
        assertEquals(1 * 2, product(1, 2))
    }

    // 3. Now we can fold on a single-item list
    @Test
    fun `fold on a single-item list yields the result of folding that item into the seed`() {
        val seed = 1;
        assertEquals(seed * 1, foldRightOverflows(listOf(1), seed, ::product))
    }

    // 4. Now we can fold on a two-item list
    @Test
    fun `fold on a two-item list yields the expected`() {
        val seed = 1;
        assertEquals(seed * 1 * 2, foldRightOverflows(listOf(1, 2), seed, ::product))
    }

    // 5. Now we can fold on a very large list - it doesn't stack overflow because we're tail recursing
    @Test
    fun `fold on a very large list`() {
        val seed = 1;
        val massiveList = mutableListOf<Int>()
        for (i in 1..10000) {
            massiveList.add(i)
        }
        assertEquals(500501, foldRightOverflows(massiveList, seed, ::product))
    }
}


