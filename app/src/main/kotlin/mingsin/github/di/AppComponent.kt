package mingsin.github.di

import dagger.Component
import dagger.android.AndroidInjector
import dagger.android.support.AndroidSupportInjectionModule
import mingsin.github.App
import okhttp3.OkHttpClient
import javax.inject.Singleton

/**
 * Created by Trevor Wang on 2/16/17.
 */
@Singleton
@Component(
        modules = [
            AndroidSupportInjectionModule::class,
            AppModule::class,
            ViewModelModule::class,
            InjectorBuilderModule::class]
)
interface AppComponent : AndroidInjector<App> {
    fun okHttpClient(): OkHttpClient
}