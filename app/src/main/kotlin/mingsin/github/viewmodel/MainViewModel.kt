package mingsin.github.viewmodel

import com.orhanobut.logger.Logger
import mingsin.github.ui.MainActivity
import javax.inject.Inject

/**
 * Created by trevorwang on 24/12/2017.
 */
class MainViewModel @Inject constructor() {

    @Inject lateinit var act: MainActivity

    fun sayHello() {
        Logger.d(act)
    }
}