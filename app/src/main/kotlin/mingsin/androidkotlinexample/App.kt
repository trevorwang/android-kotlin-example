package mingsin.androidkotlinexample

import android.app.Application
import android.net.ConnectivityManager
import com.orhanobut.logger.AndroidLogAdapter
import com.orhanobut.logger.Logger
import mingsin.androidkotlinexample.di.AppComponent
import mingsin.androidkotlinexample.di.AppModule
import mingsin.androidkotlinexample.di.DaggerAppComponent
import javax.inject.Inject

/**
 * Created by Trevor Wang on 2/16/17.
 */
class App : Application() {
    lateinit var component: AppComponent
    lateinit @Inject
    var cm: ConnectivityManager

    override fun onCreate() {
        super.onCreate()
        Logger.addLogAdapter(AndroidLogAdapter())
        component = DaggerAppComponent.builder().appModule(AppModule(this)).build()
        component.inject(this)
        Logger.d("connectivity manager : ${cm}")
    }
}