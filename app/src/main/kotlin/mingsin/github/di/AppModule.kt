package mingsin.github.di

import android.content.Context
import android.net.ConnectivityManager
import dagger.Module
import dagger.Provides
import mingsin.github.App
import mingsin.github.data.GithubApiService
import mingsin.github.data.RestApi
import javax.inject.Singleton


/**
 * Created by Trevor Wang on 2/16/17.
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
        return app.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
    }

    @Provides
    @Singleton
    fun provideGithubApiService(retrofit: RestApi): GithubApiService {
        return retrofit.createRetrofit().create(GithubApiService::class.java)
    }
}
