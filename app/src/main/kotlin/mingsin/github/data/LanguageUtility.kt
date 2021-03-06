package mingsin.github.data

import android.content.Context
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.graphics.drawable.Drawable
import androidx.collection.ArrayMap
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.orhanobut.logger.Logger
import mingsin.github.R
import java.io.InputStreamReader
import javax.inject.Inject
import javax.inject.Singleton

/**
 * Created by trevorwang on 20/12/2016.
 */
@Singleton
class LanguageUtility @Inject constructor(val context: Context) {
    private var colorConfig: ArrayMap<String, String>
    private val gsonType = object : TypeToken<ArrayMap<String, String>>() {}.type

    init {
        val inputStream = context.resources.openRawResource(R.raw.language_colors)
        val cc = InputStreamReader(inputStream)
        colorConfig = Gson().fromJson(cc,gsonType)
        Logger.d(colorConfig)
    }

    fun getColor(lan: String?): String? {
        return colorConfig[lan]
    }

    fun getDrawable(lan: String?): Drawable? {
        val color = colorConfig[lan]
        Logger.v("Color %s", color)
        return if (color == null) ColorDrawable(Color.TRANSPARENT) else ColorDrawable(Color.parseColor(color))
    }
}