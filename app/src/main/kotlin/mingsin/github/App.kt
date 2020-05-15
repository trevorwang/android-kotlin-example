package mingsin.github

import android.net.ConnectivityManager
import android.os.StrictMode
import android.os.StrictMode.VmPolicy
import com.orhanobut.logger.AndroidLogAdapter
import com.orhanobut.logger.Logger
import dagger.android.AndroidInjector
import dagger.android.support.DaggerApplication
import mingsin.github.di.AppModule
import mingsin.github.di.DaggerAppComponent
import okhttp3.OkHttpClient
import javax.inject.Inject


/**
 * Created by Trevor Wang on 2/16/17.
 */
class App : DaggerApplication() {

    public override fun applicationInjector(): AndroidInjector<out DaggerApplication> {
        return DaggerAppComponent.builder().appModule(AppModule(this)).build()
    }

    @Inject
    lateinit var cm: ConnectivityManager

    @Inject
    lateinit var okHttpClient: OkHttpClient

    override fun onCreate() {
        super.onCreate()

        if (BuildConfig.DEBUG) {
            StrictMode.setThreadPolicy(StrictMode.ThreadPolicy.Builder()
                    .detectAll()
                    .penaltyLog()
                    .build())
            StrictMode.setVmPolicy(VmPolicy.Builder()
                    .detectLeakedSqlLiteObjects()
                    .detectLeakedClosableObjects()
                    .penaltyLog()
                    .penaltyDeath()
                    .build())
        }

        Logger.addLogAdapter(AndroidLogAdapter())
        Logger.d("connectivity manager : $cm")
    }
}