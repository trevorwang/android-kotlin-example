package mingsin.github.ui

import androidx.databinding.DataBindingUtil
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import dagger.android.support.DaggerFragment
import mingsin.github.R
import mingsin.github.databinding.FragmentBaseBinding

/**
 * Created by trevorwang on 27/12/2017.
 */
abstract class BaseFragment : DaggerFragment() {
    private lateinit var binding: FragmentBaseBinding
    private var inited = false

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_base, container, false)
        val v = onCreateContentView(inflater, binding.container, savedInstanceState)
        if (v != null) {
            binding.container.addView(v, 0)
        }
        return binding.root
    }

    override fun onResume() {
        super.onResume()
        if (inited) {
            showLoadingView()
        }
    }

    abstract fun onCreateContentView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View?

    private fun showLoadingView() {
        binding.loading = true
    }

    fun hideLoadingView() {
        binding.loading = false
    }
}