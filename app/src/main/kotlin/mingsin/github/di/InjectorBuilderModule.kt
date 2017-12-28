package mingsin.github.di

import dagger.Module
import dagger.android.ContributesAndroidInjector
import mingsin.github.ui.BaseFragment
import mingsin.github.ui.MainActivity
import mingsin.github.ui.TrendingFragment
import mingsin.github.ui.WithFragmentActivity

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
    abstract fun injectTrendingFragmeng(): TrendingFragment

}