package mingsin.github.data

import com.orhanobut.logger.Logger
import kotlinx.coroutines.flow.*
import mingsin.github.model.State

interface Request {
    suspend fun <T> request(call: () -> Flow<T>): Flow<State<T>> = flow {
        call().onStart {
            emit(State.loading<T>())
        }.catch {
            Logger.e(it, "Here's the error message")
            emit(State.error<T>(it))
        }.collect {
            emit(State.success(it))
        }

    }
}