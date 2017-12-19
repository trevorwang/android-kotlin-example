package mingsin.androidkotlinexample

import android.net.ConnectivityManager
import com.orhanobut.logger.AndroidLogAdapter
import com.orhanobut.logger.Logger
import dagger.android.AndroidInjector
import dagger.android.support.DaggerApplication
import mingsin.androidkotlinexample.di.AppModule
import mingsin.androidkotlinexample.di.DaggerAppComponent
import javax.inject.Inject

/**
 * Created by Trevor Wang on 2/16/17.
 */
class App : DaggerApplication() {

    override fun applicationInjector(): AndroidInjector<out DaggerApplication> {
        return DaggerAppComponent.builder().appModule(AppModule(this)).build()
    }

    @Inject
    lateinit var cm: ConnectivityManager

    override fun onCreate() {
        super.onCreate()
        Logger.addLogAdapter(AndroidLogAdapter())
        Logger.d("connectivity manager : $cm")
    }
}