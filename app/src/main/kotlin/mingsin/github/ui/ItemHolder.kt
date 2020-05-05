package mingsin.github.ui

import androidx.databinding.ViewDataBinding
import androidx.recyclerview.widget.RecyclerView

/**
 * Created by trevorwang on 25/12/2016.
 */

class ItemHolder<out T : ViewDataBinding>(val binding: T) : androidx.recyclerview.widget.RecyclerView.ViewHolder(binding.root)