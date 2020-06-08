package mingsin.github.repo.local

import androidx.room.Database
import androidx.room.RoomDatabase
import mingsin.github.model.KeyValue

@Database(entities = [KeyValue::class], version = 1)
abstract class AppDataBase : RoomDatabase() {
    abstract fun keyValueDao(): KeyValueDao
}