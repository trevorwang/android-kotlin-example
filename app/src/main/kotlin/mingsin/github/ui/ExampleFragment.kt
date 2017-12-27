package mingsin.github.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.orhanobut.logger.Logger
import dagger.android.support.DaggerFragment
import mingsin.github.data.GithubApiService
import javax.inject.Inject

/**
 * Created by Trevor Wang on 2/17/17.
 */
class ExampleFragment : DaggerFragment() {
    @Inject lateinit var apiService: GithubApiService

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val textView = TextView(context)
        textView.text = "Hello fragment!!!!"
        return textView
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        Logger.d(activity)
    }
}