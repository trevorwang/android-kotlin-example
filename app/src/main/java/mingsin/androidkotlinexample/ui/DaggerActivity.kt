package mingsin.androidkotlinexample.ui

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import mingsin.androidkotlinexample.App
import mingsin.androidkotlinexample.di.ActivityComponent
import mingsin.androidkotlinexample.di.ActivityModule
import mingsin.androidkotlinexample.di.DaggerActivityComponent

/**
 * Created by wangta on 2/16/17.
 */
abstract class DaggerActivity : AppCompatActivity() {
    var component: ActivityComponent? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val app = application as App
        component = DaggerActivityComponent.builder()
                .activityModule(ActivityModule(this))
                .appComponent(app.component)
                .build()
        onInject()
    }

    abstract fun onInject()

    override fun onDestroy() {
        component = null
        super.onDestroy()
    }
}