package mingsin.github

import android.content.Context
import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import kotlinx.coroutines.runBlocking
import mingsin.github.model.KeyValue
import mingsin.github.repo.local.AppDataBase
import mingsin.github.repo.local.KeyValueDao
import org.junit.After
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import java.io.IOException
import kotlin.test.assertEquals

@RunWith(AndroidJUnit4::class)
class DatabaseTest {

    lateinit var kvDao: KeyValueDao
    lateinit var db: AppDataBase

    @Before
    fun createDb() {
        val context = ApplicationProvider.getApplicationContext<Context>()
        db = Room.inMemoryDatabaseBuilder(
                context, AppDataBase::class.java).build()
        kvDao = db.keyValueDao()
    }

    @After
    @Throws(IOException::class)
    fun closeDb() {
        db.close()
    }

    @Test
    @Throws(Exception::class)
    fun keyValueWriteAndReadTest() {
        val kv = KeyValue(key = "abc", value = "def")

        runBlocking {
            kvDao.insertAll(kv)
            val byKey = kvDao.findByKey("abc")
            assertEquals(byKey?.value, kv.value)
        }
    }

    @Test
    @Throws(Exception::class)
    fun keyValueUpdateTest() {
        val kv = KeyValue(key = "abc", value = "def")
        runBlocking {
            kvDao.insertAll(kv)
            val toUpdate = KeyValue(key = "abc", value = "ggg")
            kvDao.update(toUpdate)
            val byKey = kvDao.findByKey("abc")
            assertEquals(byKey?.value, toUpdate.value)
        }
    }
}