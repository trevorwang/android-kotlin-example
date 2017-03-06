package mingsin.androidkotlinexample.ui

import android.app.ProgressDialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.orhanobut.logger.Logger
import mingsin.androidkotlinexample.data.ApiService
import rx.android.schedulers.AndroidSchedulers
import rx.schedulers.Schedulers
import javax.inject.Inject

/**
 * Created by Trevor Wang on 2/17/17.
 */
class ExampleFragment : DaggerFragment() {
    @Inject lateinit var apiService: ApiService
    @Inject lateinit var progressDialog: ProgressDialog
    override fun onInject() {
        component?.inject(this)
    }

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val textView = TextView(context)
        textView.text = "Hello fragm/ent!!!!"
        return textView
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        progressDialog.show()
        apiService.ip().subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread())
                .subscribe {
                    Logger.d(it)
                }
    }
}