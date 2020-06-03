package mingsin.github.data

import kotlinx.coroutines.flow.*
import mingsin.github.model.State

interface Request {
    suspend fun <T> request(call: () -> Flow<T>): Flow<State<T>> = flow {
        call().onStart {
            emit(State.loading<T>())
        }.catch {
            emit(State.error<T>(it))
        }.collect {
            emit(State.success(it))
        }

    }
}