package mingsin.androidkotlinexample.di

import dagger.Component
import mingsin.androidkotlinexample.MainActivity

/**
 * Created by wangta on 2/16/17.
 */
@ForActivity
@Component(
        modules = arrayOf(ActivityModule::class),
        dependencies = arrayOf(AppComponent::class)
)
interface ActivityComponent {
    fun inject(activity: MainActivity)
}