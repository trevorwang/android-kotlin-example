package mingsin.github.data

import com.orhanobut.logger.Logger
import kotlinx.coroutines.flow.*
import mingsin.github.model.State
import mingsin.github.tools.AppError

interface Request {
    fun <T> request(handler: ((error: Throwable) -> AppError?)? = null, call: () -> Flow<T>): Flow<State<T>> = flow {
        call().onStart {
            emit(State.loading<T>())
        }.catch {
            Logger.e(it, "Here's the error message")
            emit(State.error(it, handler))
        }.collect {
            emit(State.success(it))
        }

    }
}