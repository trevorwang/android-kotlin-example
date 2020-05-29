package mingsin.github

import org.junit.Test

import org.junit.Assert.*
import org.junit.runner.RunWith
import org.junit.runners.JUnit4

/**
 * Example local unit test, which will execute on the development machine (host).

 * @see [Testing documentation](http://d.android.com/tools/testing)
 */
@RunWith(JUnit4::class)
class ExampleUnitTest {
    @Test
    @Throws(Exception::class)
    fun additionIsCorrect() {
        assertEquals(4, (2 + 2).toLong())
    }
}