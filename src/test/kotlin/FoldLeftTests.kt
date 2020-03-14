import com.harmellaw.kotlin.foldLeft
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

class FoldLeftTests {

    private fun sum(l: Int, r: Int) : Int = l + r

    // 1. Simplest version - empty list
    @Test
    fun `fold on an empty list yields the seed`() {
        val seed = 1;
        val emptyList: List<Int> = emptyList()
        assertEquals(seed, foldLeft(emptyList, seed, fun(a, b) = a + b))
    }

    // 2. Now we can fold on a single-item list
    @Test
    fun `fold on a single-item list yields the result of folding that item into the seed`() {
        val seed = 0;
        assertEquals(seed + 1, foldLeft(listOf(1), seed, ::sum))
    }

    // 3. Now we can fold on a two-item list
    @Test
    fun `fold on a two-item list yields the expected`() {
        val seed = 0;
        assertEquals(seed + 1 + -2 + 3, foldLeft(listOf(1, -2, 3), seed, ::sum))
    }

    // 4. Now we can fold on a very large list - it doesn't stack overflow because we're tail recursing
    // TODO - this stack overflows.  Make it stop! :(
    @Test
    fun `fold on a very large list`() {
        val seed = 1;
        val massiveList = mutableListOf<Int>()
        for (i in 1..10000) {
            massiveList.add(i)
        }
        assertEquals(500501, foldLeft(massiveList, seed, ::sum))
    }
}
