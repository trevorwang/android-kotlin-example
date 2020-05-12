package mingsin.github.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil.inflate
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.SCROLL_STATE_IDLE
import com.orhanobut.logger.Logger
import com.squareup.picasso.Picasso
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
    @Inject
    lateinit var api: GithubApiService

    @Inject
    lateinit var lanUtil: LanguageUtility
    lateinit var adapter: TrendingAdapter
    private lateinit var binding: FragmentTrendingBinding

    override fun onCreateContentView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = inflate(inflater, R.layout.fragment_trending, container, false)
        adapter = TrendingAdapter(context!!, lanUtil)
        binding.rvRepos.layoutManager = LinearLayoutManager(context)
        binding.rvRepos.adapter = adapter
//        binding.rvRepos.addItemDecoration(SimpleDividerDecoration())
        binding.rvRepos.addItemDecoration(SectionDecoration(object : SectionDecorationCallback {
            override fun groupId(position: Int): Int {
                return position / 4
            }

            override fun groupFirstLine(position: Int): String {
                return (position / 4 + 1).toString()
            }

        }))
        binding.rvRepos.addOnScrollListener(object : InfiniteScrollListener(10) {
            override fun loadMore(page: Int) {
                Logger.v("loadMore.......page : %d", page)
                loadData(page)
            }
        })
        binding.rvRepos.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrollStateChanged(recyclerView: RecyclerView, newState: Int) {
                if (newState == SCROLL_STATE_IDLE) {
                    Picasso.get().resumeTag("trending")
                } else {
                    Picasso.get().pauseTag("trending")
                }
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
