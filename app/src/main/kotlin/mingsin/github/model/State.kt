package mingsin.github.model

sealed class State<T> {
    companion object {
        fun <T> success(data: T): Success<T> {
            return Success(data)
        }

        fun <T> error(error: Throwable): Error<T> {
            return Error(error)
        }

        fun <T> loading(): State<T> = Loading()
    }
}

class Success<T>(val data: T) : State<T>()

class Error<T>(val error: Throwable) : State<T>()

class Loading<T> : State<T>()