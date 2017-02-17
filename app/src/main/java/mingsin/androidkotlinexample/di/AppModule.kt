package mingsin.androidkotlinexample.di

import android.content.Context
import android.net.ConnectivityManager
import dagger.Module
import dagger.Provides
import mingsin.androidkotlinexample.data.ApiService
import mingsin.androidkotlinexample.App
import mingsin.androidkotlinexample.data.RestClient
import javax.inject.Singleton

/**
 * Created by wangta on 2/16/17.
 */
@Module
class AppModule(val app: App) {

    @Provides
    @Singleton
    fun app(): App {
        return app
    }

    @Provides
    @Singleton
    fun context(): Context {
        return app
    }

    @Provides
    @Singleton
    fun connectivity(): ConnectivityManager {
        val service = app.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        return service
    }

    @Provides
    @Singleton
    fun api(): ApiService {
        return RestClient().createApi()
    }
}