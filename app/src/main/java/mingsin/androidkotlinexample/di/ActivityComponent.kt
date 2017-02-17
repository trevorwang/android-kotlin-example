package mingsin.androidkotlinexample.di

import android.app.ProgressDialog
import dagger.Component
import mingsin.androidkotlinexample.ApiService
import mingsin.androidkotlinexample.MainActivity
import mingsin.androidkotlinexample.WithFragmentActivity

/**
 * Created by wangta on 2/16/17.
 */
@ForActivity
@Component(
        modules = arrayOf(ActivityModule::class),
        dependencies = arrayOf(AppComponent::class)
)
interface ActivityComponent {
    fun api(): ApiService
    fun progress(): ProgressDialog

    fun inject(activity: MainActivity)
    fun inject(activity: WithFragmentActivity)
}