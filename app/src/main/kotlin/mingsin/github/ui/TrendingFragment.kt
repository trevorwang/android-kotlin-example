package mingsin.github.ui

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil.inflate
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Observer
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.bumptech.glide.Glide
import com.bumptech.glide.ListPreloader.PreloadModelProvider
import com.bumptech.glide.RequestBuilder
import com.bumptech.glide.integration.recyclerview.RecyclerViewPreloader
import com.bumptech.glide.util.ViewPreloadSizeProvider
import com.orhanobut.logger.Logger
import mingsin.github.R
import mingsin.github.data.GithubApiService
import mingsin.github.data.LanguageUtility
import mingsin.github.databinding.FragmentTrendingBinding
import mingsin.github.di.ViewModelFactory
import mingsin.github.extension.toast
import mingsin.github.model.Repository
import mingsin.github.viewmodel.TrendingRepoViewModel
import javax.inject.Inject


/**
 * Created by trevorwang on 17/12/2016.
 */
class TrendingFragment : BaseFragment() {
    @Inject
    lateinit var api: GithubApiService

    @Inject
    lateinit var factory: ViewModelFactory
    val model by activityViewModels<TrendingRepoViewModel> { factory }

    @Inject
    lateinit var lanUtil: LanguageUtility
    lateinit var adapter: TrendingAdapter
    private lateinit var binding: FragmentTrendingBinding


    init {
        lifecycleScope.launchWhenCreated {
            model.loadData()
        }
    }

    override fun onCreateContentView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = inflate(inflater, R.layout.fragment_trending, container, false)
        setupRecyclerViewData()
        return binding.root
    }

    private fun setupRecyclerViewData() {
        adapter = TrendingAdapter(requireContext(), lanUtil)
        binding.rvRepos.layoutManager = LinearLayoutManager(context)
        binding.rvRepos.adapter = adapter
        binding.rvRepos.addOnScrollListener(object : InfiniteScrollListener(10) {
            override fun loadMore(page: Int) {
                Logger.v("loadMore.......page : %d", page)
                model.loadData(page)
            }
        })
        setupRecyclerViewPreLoader()
        binding.rvRepos.addOnItemTouchListener(RecyclerItemClickListener(binding.rvRepos.context,
                binding.rvRepos,
                object : RecyclerItemClickListener.OnItemClickListener {
                    override fun onItemClicked(view: View, position: Int) {
                        if (position < adapter.repos.size) {
                            openProjectPage(adapter.repos[position])
                        }
                    }
                },
                object : RecyclerItemClickListener.OnItemLongClickListener {
                    override fun onItemLongClicked(view: View, position: Int) {
                        context?.toast("hello world!")
                    }
                }))
    }

    private fun setupRecyclerViewPreLoader() {
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
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        model.repos.observe(viewLifecycleOwner, Observer {
            if (it != null) {
                Logger.i("${it.size}")
                adapter.repos = it
            }
            hideLoadingView()
        })

        model.error.observe(viewLifecycleOwner, Observer {
            it?.let { th ->
                requireContext().toast(th.message!!)
            }

        })
    }


    private fun openProjectPage(repo: Repository) {
        val intent = Intent(Intent.ACTION_VIEW)
        intent.data = Uri.parse(repo.htmlUrl)
        context?.startActivity(intent)
    }
}
