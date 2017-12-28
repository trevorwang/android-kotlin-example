package mingsin.github.ui

import android.databinding.ViewDataBinding
import android.support.v7.widget.RecyclerView

/**
 * Created by trevorwang on 25/12/2016.
 */

class ItemHolder<out T : ViewDataBinding>(val binding: T) : RecyclerView.ViewHolder(binding.root)