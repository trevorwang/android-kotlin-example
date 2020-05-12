package mingsin.github.di

import android.content.Context
import android.net.ConnectivityManager
import dagger.Module
import dagger.Provides
import mingsin.github.App
import mingsin.github.data.GithubApiService
import mingsin.github.data.RestApi
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
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
    fun provideOkHttpClient(): OkHttpClient {
        val interceptor = HttpLoggingInterceptor()
        interceptor.level = HttpLoggingInterceptor.Level.BODY
        return OkHttpClient.Builder()
                .addInterceptor(interceptor)
                .build()
    }

    @Provides
    @Singleton
    fun provideGithubApiService(retrofit: RestApi, okHttpClient: OkHttpClient): GithubApiService {
        return retrofit.createRetrofit(okHttpClient).create(GithubApiService::class.java)
    }
}
