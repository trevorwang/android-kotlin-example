package mingsin.github.data

import kotlinx.coroutines.coroutineScope
import mingsin.github.model.State

interface Repository {
    suspend fun <T> request(call: () -> T): State<T> = coroutineScope {
        try {
            State.loading<T>()
            State.success(call())
        } catch (e: Throwable) {
            State.error(e)
        }
    }
}