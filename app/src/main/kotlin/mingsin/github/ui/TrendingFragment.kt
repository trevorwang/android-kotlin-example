package mingsin.github.ui

import android.databinding.DataBindingUtil
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.orhanobut.logger.Logger
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers.io
import mingsin.github.R
import mingsin.github.data.GithubApiService
import mingsin.github.data.LanguageUtility
import mingsin.github.databinding.FragmentTrendingBinding
import javax.inject.Inject

/**
 * Created by trevorwang on 17/12/2016.
 */
class TrendingFragment : BaseFragment() {
    @Inject lateinit var api: GithubApiService
    @Inject lateinit var lanUtil: LanguageUtility
    lateinit var adapter: TrendingAdapter
    private lateinit var binding: FragmentTrendingBinding

    override fun onCreateContentView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_trending, container, false)
        adapter = TrendingAdapter(context!!, lanUtil)
        binding.rvRepos.layoutManager = LinearLayoutManager(context)
        binding.rvRepos.adapter = adapter
        binding.rvRepos.addOnScrollListener(object : InfiniteScrollListener(10) {
            override fun loadMore(page: Int) {
                Logger.v("loadMore.......page : %d", page)
                loadData(page)
            }
        })
        return binding.root
    }

    override fun onResume() {
        super.onResume()
        loadData()
    }

    private fun loadData(page: Int = 1) {
        subscriptions.add(api.trending("created:>2017-12-27", page = page).subscribeOn(io()).observeOn(AndroidSchedulers.mainThread())
                .subscribe({
                    Logger.v("get repos %s", it)
                    adapter.repos += it.items
                    hideLoadingView()
                    inited = true
                }) {
                    Logger.e(it, "")
                    hideLoadingView()
                })
    }
}