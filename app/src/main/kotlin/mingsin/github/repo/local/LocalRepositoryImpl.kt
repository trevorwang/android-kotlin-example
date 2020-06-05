package mingsin.github.repo.local

import com.orhanobut.logger.Logger
import kotlinx.coroutines.flow.flow
import mingsin.github.model.KeyValue
import javax.inject.Inject

class LocalRepositoryImpl @Inject constructor(private val database: AppDataBase) : LocalRepository {
    override suspend fun saveKeyValue(keyValue: KeyValue) {
        val kv = database.keyValueDao().findByKey(keyValue.key)
        if (kv == null) {
            database.keyValueDao().insertAll(keyValue)
        } else {
            database.keyValueDao().update(keyValue)
        }
    }

    override suspend fun getAccessToken(): String? {
        val value = database.keyValueDao().findByKey("access_token")?.value
        Logger.v("get accessToken : $value")
        return if (value?.isBlank() == true) "c3ffb3687fec2408c3e98bd9843061b0727dad45" else value!!
    }
}