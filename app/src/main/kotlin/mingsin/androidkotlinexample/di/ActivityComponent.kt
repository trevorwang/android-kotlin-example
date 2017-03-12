package mingsin.androidkotlinexample.di

import android.app.ProgressDialog
import dagger.Component
import mingsin.androidkotlinexample.data.ApiService
import mingsin.androidkotlinexample.ui.MainActivity
import mingsin.androidkotlinexample.ui.WithFragmentActivity

/**
 * Created by Trevor Wang on 2/16/17.
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