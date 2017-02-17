package mingsin.androidkotlinexample

import android.app.ProgressDialog
import android.databinding.DataBindingUtil
import android.net.ConnectivityManager
import android.os.Bundle
import android.support.design.widget.FloatingActionButton
import android.support.design.widget.Snackbar
import android.support.v7.widget.Toolbar
import com.orhanobut.logger.Logger
import mingsin.androidkotlinexample.databinding.ActivityMainBinding
import rx.android.schedulers.AndroidSchedulers
import rx.schedulers.Schedulers
import rx.subscriptions.CompositeSubscription
import javax.inject.Inject

class MainActivity : DaggerActivity() {
    @Inject lateinit var apiService: ApiService
    @Inject lateinit var cm: ConnectivityManager
    @Inject lateinit var progressDialog: ProgressDialog

    private val subscriptions = CompositeSubscription()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val binding = DataBindingUtil.setContentView<ActivityMainBinding>(this, R.layout.activity_main)
        binding.greating = "Hello Kotlin Data Binding!!!"

        val toolbar = findViewById(R.id.toolbar) as Toolbar
        setSupportActionBar(toolbar)

        val fab = findViewById(R.id.fab) as FloatingActionButton
        fab.setOnClickListener { view ->
            Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                    .setAction("Action", null).show()
        }
        Logger.d(cm)
    }

    override fun onInject() {
        component?.inject(this)
    }


    override fun onResume() {
        super.onResume()
        progressDialog.show()
    }

    override fun onStart() {
        super.onStart()
        subscriptions.add(apiService.ip().subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({
                    ip ->
                    Logger.d("get result ${ip}")
                    progressDialog.hide()
                }) { error ->
                    Logger.e(error, "Aha.. got error message")
                    progressDialog.hide()
                })
    }

    override fun onStop() {
        super.onStop()
        subscriptions.clear()
    }

}
