package mingsin.androidkotlinexample.di

import dagger.Module
import dagger.android.ContributesAndroidInjector
import mingsin.androidkotlinexample.ui.ExampleFragment
import mingsin.androidkotlinexample.ui.MainActivity
import mingsin.androidkotlinexample.ui.WithFragmentActivity

/**
 * Created by wangta on 19/12/2017.
 */
@Module
abstract class InjectorBuilderModule {

    @ContributesAndroidInjector
    abstract fun injectMainActivity(): MainActivity

    @ContributesAndroidInjector
    abstract fun injectWithFragmentActivity(): WithFragmentActivity

    @ContributesAndroidInjector
    abstract fun injectExampleFragment(): ExampleFragment

}