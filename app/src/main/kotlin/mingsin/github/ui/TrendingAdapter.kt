package mingsin.github.ui;

import android.content.Context
import android.content.Intent
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import android.net.Uri
import androidx.recyclerview.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import mingsin.github.R
import mingsin.github.data.LanguageUtility
import mingsin.github.databinding.ItemRepoBinding
import mingsin.github.databinding.RecyclerviewFooterBinding
import mingsin.github.model.Repository

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

    override fun onCreateViewHolder(p0: ViewGroup, viewType: Int): ItemHolder<ViewDataBinding> {
        val inflater = LayoutInflater.from(context)
        if (viewType == itemTypeFooter) {
            val footerBinding = DataBindingUtil.inflate<RecyclerviewFooterBinding>(inflater, R.layout.recyclerview_footer, p0, false)
            return ItemHolder(footerBinding)
        }

        val binding = DataBindingUtil.inflate<ItemRepoBinding>(inflater, R.layout.item_repo, p0, false)
        return ItemHolder<ItemRepoBinding>(binding)
    }


    override fun onBindViewHolder(holder: ItemHolder<ViewDataBinding>, position: Int) {
        when (val binding = holder.binding) {
            is ItemRepoBinding -> {
                val repo = repos[position]
                binding.repo = repo
                binding.lanUtility = languageUtility
                binding.root.setOnClickListener {
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
