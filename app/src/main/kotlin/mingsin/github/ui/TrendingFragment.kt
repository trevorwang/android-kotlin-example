package mingsin.github.ui

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil.inflate
import androidx.recyclerview.widget.LinearLayoutManager
import com.bumptech.glide.Glide
import com.bumptech.glide.ListPreloader.PreloadModelProvider
import com.bumptech.glide.RequestBuilder
import com.bumptech.glide.integration.recyclerview.RecyclerViewPreloader
import com.bumptech.glide.util.FixedPreloadSizeProvider
import com.bumptech.glide.util.ViewPreloadSizeProvider
import com.orhanobut.logger.Logger
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers.io
import mingsin.github.R
import mingsin.github.data.GithubApiService
import mingsin.github.data.LanguageUtility
import mingsin.github.databinding.FragmentTrendingBinding
import mingsin.github.model.Repository
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
//        binding.rvRepos.addOnScrollListener(object : RecyclerView.OnScrollListener() {
//            override fun onScrollStateChanged(recyclerView: RecyclerView, newState: Int) {
//                if (newState == SCROLL_STATE_IDLE) {
//                    Glide.with(recyclerView).resumeRequests()
//                } else {
//                    Glide.with(recyclerView).pauseAllRequests()
//                }
//            }
//        })

        val sizeProvider = ViewPreloadSizeProvider<String>()

        val modelLoader = object : PreloadModelProvider<String> {
            override fun getPreloadItems(position: Int): List<String> {
                if (position >= adapter.repos.size) {
                    return listOf()
                }
                val url: String = adapter.repos[position].owner.avatarUrl
                return if (url.isBlank()) {
                    listOf()
                } else listOf(url)
            }

            override fun getPreloadRequestBuilder(item: String): RequestBuilder<*>? {
                return Glide.with(this@TrendingFragment).load(item)
            }
        }

        val preLoader = RecyclerViewPreloader(this, modelLoader, sizeProvider, 10)
        binding.rvRepos.addOnScrollListener(preLoader)


        binding.rvRepos.addOnItemTouchListener(RecyclerItemClickListener(binding.rvRepos.context,
                object : RecyclerItemClickListener.OnItemClickListener {
                    override fun onItemClicked(view: View, position: Int) {
                        if (position < adapter.repos.size) {
                            openProjectPage(adapter.repos[position])
                        }
                    }
                }))

        return binding.root
    }


    private fun openProjectPage(repo: Repository) {
        val intent = Intent(Intent.ACTION_VIEW)
        intent.data = Uri.parse(repo.htmlUrl)
        context?.startActivity(intent)
    }

    override fun onResume() {
        super.onResume()
        loadData()
    }

    private fun loadData(page: Int = 1) {
        subscriptions.add(api.trending("created:>2018-12-27", page = page).subscribeOn(io()).observeOn(AndroidSchedulers.mainThread())
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
