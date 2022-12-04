//import com.harmellaw.kotlin.foldLeft
import com.harmellaw.kotlin.reverse
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

class `2-ReverseTests` {

    // 1. Now we have fold left, we can use this to reverse. Firstly on an empty list
    @Test
    fun `reversing an empty list gives an empty list`() {
        val emptyList: List<Int> = emptyList()
        assertEquals(emptyList, reverse(emptyList))
    }

    // 2. Now we can try reversing a single-item list
    @Test
    fun `reversing a single-item list gives the same list`() {
        val listOfOne = listOf(1)
        assertEquals(listOfOne, reverse(listOfOne))
    }

    // 3. Now we can reverse a three-item list
    @Test
    fun `reversing a three-item list works`() {
        val listOfOneTwoThree = listOf(1, 2, 3)
        assertEquals(listOf(3, 2, 1), reverse(listOfOneTwoThree))
    }

    // 4. Let's try reversing a very large list (we'll use the Kotlin standard lib "reversed" function to check)
    @Test
    fun `reversing a very large list works too`() {
        val massiveList = mutableListOf<Int>()
        for (i in 1..10000) {
            massiveList.add(i)
        }
        assertEquals(massiveList.reversed(), reverse(massiveList))
    }
}

