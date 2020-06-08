package mingsin.github.ui

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.recyclerview.widget.RecyclerView
import mingsin.github.R
import mingsin.github.databinding.ItemRepoBinding
import mingsin.github.databinding.ItemUserBinding
import mingsin.github.databinding.RecyclerviewFooterBinding
import mingsin.github.model.Repository
import mingsin.github.model.User

class UserListAdapter(val context: Context) : RecyclerView.Adapter<ItemHolder<ViewDataBinding>>() {
    private val itemTypeFooter = 0x100
    var users: List<User> = arrayListOf()
        set(value) {
            val oldSize = field.size
            field = value
            if (oldSize < field.size) {
                notifyItemRangeChanged(oldSize, itemCount)
            } else {
                notifyDataSetChanged()
            }
        }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemHolder<ViewDataBinding> {
        val inflater = LayoutInflater.from(context)
        if (viewType == itemTypeFooter) {
            val footerBinding = DataBindingUtil.inflate<RecyclerviewFooterBinding>(inflater, R.layout.recyclerview_footer, parent, false)
            return ItemHolder(footerBinding)
        }

        val binding = ItemUserBinding.inflate(inflater, parent, false)
        return ItemHolder(binding)
    }

    override fun onBindViewHolder(holder: ItemHolder<ViewDataBinding>, position: Int) {
        when (val binding = holder.binding) {
            is ItemUserBinding -> {
                val user = users[position]
                binding.user = user
            }
            is RecyclerviewFooterBinding -> {

            }
        }
    }

    override fun getItemViewType(position: Int): Int {
        if (users.size == position) {
            return itemTypeFooter
        }
        return super.getItemViewType(position)
    }


    override fun getItemCount(): Int {
        if (users.isEmpty()) {
            return 0
        }
        return users.count() + 1
    }
}