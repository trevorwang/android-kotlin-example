package mingsin.github.repo.local

import androidx.room.*
import kotlinx.coroutines.flow.Flow
import mingsin.github.model.KeyValue

@Dao
interface KeyValueDao {

    @Query("SELECT * FROM KeyValue")
    suspend fun keyValues(): List<KeyValue>

    @Query("SELECT * FROM KeyValue WHERE id IN (:ids)")
    suspend fun keyValues(ids: IntArray): List<KeyValue>

    @Query("SELECT * FROM KeyValue WHERE `key` == (:key)")
    suspend fun findByKey(key: String): KeyValue?

    @Update
    suspend fun update(kv: KeyValue)

    @Insert
    suspend fun insertAll(vararg kvs: KeyValue)

    @Delete
    suspend fun delete(kv: KeyValue)
}