package mingsin.androidkotlinexample.ui


import android.content.Intent
import android.databinding.DataBindingUtil
import android.net.ConnectivityManager
import android.os.Bundle
import android.support.design.widget.FloatingActionButton
import android.support.v7.widget.Toolbar
import com.orhanobut.logger.Logger
import dagger.android.support.DaggerAppCompatActivity
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import mingsin.androidkotlinexample.R
import mingsin.androidkotlinexample.data.ApiService
import mingsin.androidkotlinexample.databinding.ActivityMainBinding
import mingsin.androidkotlinexample.viewmodel.MainViewModel
import javax.inject.Inject

class MainActivity : DaggerAppCompatActivity() {
    @Inject lateinit var apiService: ApiService
    @Inject lateinit var cm: ConnectivityManager
    @Inject lateinit var viewModel: MainViewModel

    private val subscriptions = CompositeDisposable()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val binding = DataBindingUtil.setContentView<ActivityMainBinding>(this, R.layout.activity_main)
        binding.greating = "Hello Kotlin Data Binding!!!"

        val toolbar = findViewById<Toolbar>(R.id.toolbar)
        setSupportActionBar(toolbar)

        val fab = findViewById<FloatingActionButton>(R.id.fab)
        fab.setOnClickListener {
            val intent = Intent(this, WithFragmentActivity::class.java)
            startActivity(intent)
        }
        Logger.d(cm)
        viewModel.sayHello()
    }

    override fun onStart() {
        super.onStart()
        subscriptions.add(apiService.ip().subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({
                    Logger.d("get result $it")
                }, {
                    Logger.e(it, "Aha.. got error message")
                }))
    }

    override fun onStop() {
        super.onStop()
        subscriptions.clear()
    }

}
