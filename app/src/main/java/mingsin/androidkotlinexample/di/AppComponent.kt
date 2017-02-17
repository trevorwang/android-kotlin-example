package mingsin.androidkotlinexample.di

import android.net.ConnectivityManager
import dagger.Component
import mingsin.androidkotlinexample.ApiService
import mingsin.androidkotlinexample.App
import javax.inject.Singleton

/**
 * Created by wangta on 2/16/17.
 */
@Singleton
@Component(
        modules = arrayOf(AppModule::class)
)
interface AppComponent {
    fun inject(app: App)

    fun connectivity(): ConnectivityManager
    fun api(): ApiService
}