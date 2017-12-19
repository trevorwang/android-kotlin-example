package mingsin.androidkotlinexample

import org.junit.Test

import org.junit.Assert.*
import org.junit.runner.RunWith
import org.robolectric.RobolectricTestRunner

/**
 * Example local unit test, which will execute on the development machine (host).

 * @see [Testing documentation](http://d.android.com/tools/testing)
 */
@RunWith(RobolectricTestRunner::class)
class ExampleUnitTest {
    @Test
    @Throws(Exception::class)
    fun additionIsCorrect() {
        assertEquals(4, (2 + 2).toLong())
    }
}