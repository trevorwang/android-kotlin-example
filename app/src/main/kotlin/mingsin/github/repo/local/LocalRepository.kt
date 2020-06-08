package mingsin.github.repo.local

import mingsin.github.model.KeyValue

interface LocalRepository {
    suspend fun saveKeyValue(keyValue: KeyValue)
    suspend fun getAccessToken(): String?
}