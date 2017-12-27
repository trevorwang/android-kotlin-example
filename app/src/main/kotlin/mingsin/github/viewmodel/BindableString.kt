package mingsin.github.viewmodel

import android.databinding.BaseObservable
import android.databinding.BindingConversion

/**
 * Created by trevorwang on 13/12/2016.
 */
class BindableString : BaseObservable() {
    var value: String = ""
        set(v) {
            if (v != value) {
                field = v
                notifyChange()
            }
        }
}

@BindingConversion
fun convertBindableToString(bindableString: BindableString): String {
    return bindableString.value
}