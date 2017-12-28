package mingsin.github.ui

import android.content.Context
import android.content.Intent
import android.databinding.DataBindingUtil
import android.databinding.ViewDataBinding
import android.net.Uri
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
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
import mingsin.github.databinding.ItemRepoBinding
import mingsin.github.databinding.RecyclerviewFooterBinding
import mingsin.github.model.Repository
import java.util.*
import javax.inject.Inject

/**
 * Created by trevorwang on 17/12/2016.
 */
class TrendingFragment : BaseFragment() {
    @Inject lateinit var api: GithubApiService
    @Inject lateinit var lanUtils: LanguageUtility
    lateinit var adapter: TrendingAdapter
    private lateinit var binding: FragmentTrendingBinding

    override fun onCreateContentView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_trending, container, false)
        binding.rvRepos.layoutManager = LinearLayoutManager(context)
        adapter = TrendingAdapter(context!!, lanUtils)
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
        subscriptions.add(api.trending("created:>2016-12-17", page = page).subscribeOn(io()).observeOn(AndroidSchedulers.mainThread())
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


    class TrendingAdapter(val context: Context, private val languageUtility: LanguageUtility) : RecyclerView.Adapter<ItemHolder<ViewDataBinding>>() {
        private val itemTypeFooter = 0x100
        var repos: List<Repository> = ArrayList()
            set(value) {
                val oldSize = field.size
                field = value
                if (oldSize < field.size) {
                    notifyItemRangeChanged(oldSize, itemCount)
                } else {
                    notifyDataSetChanged()
                }
            }

        override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): ItemHolder<ViewDataBinding> {
            val inflater = LayoutInflater.from(context)
            if (viewType == itemTypeFooter) {
                val footerBinding = DataBindingUtil.inflate<RecyclerviewFooterBinding>(inflater, R.layout.recyclerview_footer, parent, false)
                return ItemHolder(footerBinding)
            }

            val binding = DataBindingUtil.inflate<ItemRepoBinding>(inflater, R.layout.item_repo, parent, false)
            return ItemHolder<ItemRepoBinding>(binding)
        }


        override fun onBindViewHolder(holder: ItemHolder<ViewDataBinding>?, position: Int) {
            val binding = holder?.binding
            when (binding) {
                is ItemRepoBinding -> {
                    val repo = repos[position]
                    binding.repo = repo
                    binding.lanUtility = languageUtility
                    binding.root.setOnClickListener { v ->
                        openProjectPage(repo)
                    }
                }
                is RecyclerviewFooterBinding -> {

                }
            }
        }

        override fun getItemViewType(position: Int): Int {
            if (repos.size == position) {
                return itemTypeFooter
            }
            return super.getItemViewType(position)
        }

        private fun openProjectPage(repo: Repository) {
            val intent = Intent(Intent.ACTION_VIEW)
            intent.data = Uri.parse(repo.htmlUrl)
            context.startActivity(intent)
        }

        override fun getItemCount(): Int {
            if (repos.isEmpty()) {
                return 0
            }
            return repos.count() + 1
        }
    }

}