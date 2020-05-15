package mingsin.github.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

abstract class BaseViewModel : ViewModel() {
    val error: MutableLiveData<Throwable> = MutableLiveData()
    suspend inline fun <T> handleExceptions(crossinline body: suspend () -> T?): T? {
        try {
            return withContext(Dispatchers.IO) {
                body()
            }
        } catch (e: Exception) {
            error.value = e
            liveData {
                println("here is in live data scope")
                emit(e)
            }
        }
        return null
    }

}