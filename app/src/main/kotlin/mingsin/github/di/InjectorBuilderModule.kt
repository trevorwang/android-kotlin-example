package mingsin.github.di

import dagger.Module
import dagger.android.ContributesAndroidInjector
import mingsin.github.tools.OkHttpGlideModule
import mingsin.github.ui.*

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
    abstract fun injectBaseFragment(): BaseFragment

    @ContributesAndroidInjector
    abstract fun injectTrendingFragment(): TrendingFragment

    @ContributesAndroidInjector
    abstract fun injectLoginActivity(): LoginActivity

    @ContributesAndroidInjector
    abstract fun injectGlideModule(): OkHttpGlideModule

}