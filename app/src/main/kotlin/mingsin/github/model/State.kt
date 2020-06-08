package mingsin.github.model

import mingsin.github.tools.AppError
import mingsin.github.tools.DefaultError
import mingsin.github.tools.HttpError
import retrofit2.HttpException

sealed class State<T> {
    companion object {
        fun <T> success(data: T): Success<T> {
            return Success(data)
        }

        fun <T> error(error: Throwable, handler: ((error: Throwable) -> AppError?)? = null): Error<T> {
            return handleError(error, handler)
        }

        private fun <T> handleError(error: Throwable, handler: ((error: Throwable) -> AppError?)? = null): Error<T> {
            handler?.invoke(error)?.let {
                // Just return the error if handler deals with the exception properly
                return Error(it)
            }
            if (error is HttpException) {
                var message = error.message()
                if (error.code() == 404) {
                    message = "Not Found!!!"
                }
                return Error(HttpError(message, error))
            }
            return Error(DefaultError(error.message, error))
        }

        fun <T> loading(): State<T> = Loading()
    }

    class Success<T>(val data: T) : State<T>()

    class Error<T>(val error: AppError) : State<T>()

    class Loading<T> : State<T>()
}