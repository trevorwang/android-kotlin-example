package mingsin.github.viewmodel

import android.databinding.BindingAdapter
import android.graphics.Color
import android.text.Editable
import android.text.TextWatcher
import android.widget.EditText
import android.widget.ImageView
import android.widget.TextView
import com.bumptech.glide.Glide

/**
 * Created by trevorwang on 25/12/2016.
 */
@BindingAdapter("app:binding")
fun bindEditText(view: EditText, bindableString: BindableString) {
    if (view.getTag(0x1111111) == null) {
        view.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(p0: Editable?) {
                bindableString.value = p0.toString()
            }

            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
            }

            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
            }
        })

        if (view.text.toString() != bindableString.value) {
            view.setText(bindableString.value)
        }
    }
}


@BindingAdapter("android:textColor")
fun bindTextColor(view: TextView, color: String?) {
    if (color != null) {
        val colorValue = Color.parseColor(color)
        view.setTextColor(colorValue)
    }
}

@BindingAdapter("android:src")
fun bindImageSrc(view: ImageView, src: String?) {
    if (src == null) {
        return
    }
    Glide.with(view.context).load(src).into(view)
}