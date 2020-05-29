package mingsin.github.extension

import android.content.Context
import android.widget.Toast
import kotlinx.coroutines.flow.*
import mingsin.github.model.State

/**
 * Created by trevorwang on 19/12/2016.
 */


fun Context.toast(message: String, time: Int) {
    Toast.makeText(this, message, time).show()
}

fun Context.toast(message: String) {
    toast(message, Toast.LENGTH_SHORT)
}

fun <T> Flow<T>.toState(): Flow<State<T>> = let { item ->
    item.map { State.success(it) }
            .onStart { State.loading<T>() }
            .catch { e ->
                State.error<T>(e)
            }
}