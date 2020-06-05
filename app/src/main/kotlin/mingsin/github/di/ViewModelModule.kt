package mingsin.github.di;

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import dagger.Binds
import dagger.Module;
import dagger.multibindings.IntoMap
import mingsin.github.viewmodel.LoginViewModel
import mingsin.github.viewmodel.TrendingRepoViewModel

@Module
abstract class ViewModelModule {

    @Binds
    @IntoMap
    @ViewModelKey(TrendingRepoViewModel::class) //key
    abstract fun bindMainViewModel(viewModel: TrendingRepoViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(LoginViewModel::class)
    abstract fun bindLoginViewModel(viewModel: LoginViewModel): ViewModel

    @Binds
    abstract fun bindViewModelFactory(factory: ViewModelFactory): ViewModelProvider.Factory

}
