package mingsin.github.extension

import android.content.Context
import android.widget.Toast

/**
 * Created by trevorwang on 19/12/2016.
 */


fun Context.toast(message: String, time: Int) {
    Toast.makeText(this, message, time).show()
}

fun Context.toast(message: String) {
    toast(message, Toast.LENGTH_SHORT)
}