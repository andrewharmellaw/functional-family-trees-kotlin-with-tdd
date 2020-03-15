import com.harmellaw.kotlin.foldRightOverflows
import com.harmellaw.kotlin.foldRight
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import java.math.BigInteger
import kotlin.test.*

class FoldRightTests {

    // We use this as a shorthand to avoid needing a function literal after the first test.  We use it with "::sum"
    private fun product(left: Int, right: Int) : Int = left * right

    // 1. Before we fold at all, lets quickly test our new "product" function
    @Test
    fun `Product of two integers`() {
        assertEquals(1 * 2, product(1, 2))
    }

    // 2. The simplest way to call our fold right: on an empty list with our "product" function
    @Test
    fun `folding right on an empty list yields the seed no matter what the function`() {
        val seed = 1
        val emptyList: List<Int> = emptyList()
        assertEquals(seed, foldRightOverflows(emptyList, seed, ::product))
    }

    // 3. Next we can fold right on a single-item list
    @Test
    fun `folding right on a single-item list yields the result of folding that item into the seed`() {
        val seed = 1
        assertEquals(seed * 1, foldRightOverflows(listOf(1), seed, ::product))
    }

    // 4. Now we can fold right on a two-item list
    @Test
    fun `folding on a two-item list yields the seed times the first item times the second list`() {
        val seed = 1
        assertEquals(seed * 1 * 2, foldRightOverflows(listOf(1, 2), seed, ::product))
    }

    // 5. Sadly, when we use this current fold right implementation on a very large list it *will* stack overflow because
    // pure fold right cannot tail recurse
    @Test
    fun `folding right on a very large list stack overflows`() {
        val seed = 1
        val massiveList = mutableListOf<Int>()
        for (i in 1..10000) {
            massiveList.add(i)
        }

        assertFailsWith(java.lang.StackOverflowError::class) {
            foldRightOverflows(massiveList, seed, ::product)
        }
    }

    // 6. However, we can make fold right stack-safe by implementing it by using fold left
    @Test
    fun `folding right when we implement using fold left doesn't stack overflow`() {
        val seed = 1
        val massiveList = mutableListOf<Int>()
        for (i in 1..10000) {
            massiveList.add(i)
        }
        assertEquals(50005001, foldRight(massiveList, seed, fun(a, b) = a + b))
    }
}
