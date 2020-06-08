package mingsin.github.tools

sealed class AppError(open val message: String?, open val throwable: Throwable)

data class DefaultError(override val message: String?, override val throwable: Throwable) : AppError(message, throwable)
data class HttpError(override val message: String?, override val throwable: Throwable) : AppError(message, throwable)
