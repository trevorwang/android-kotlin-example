package mingsin.github.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import mingsin.github.databinding.RecyclerviewBinding
import mingsin.github.di.ViewModelFactory
import mingsin.github.extension.toast
import mingsin.github.model.State
import mingsin.github.viewmodel.FollowersViewModel
import javax.inject.Inject

class UserListFragment() : BaseFragment() {
    @Inject
    lateinit var factory: ViewModelFactory
    val viewModel by viewModels<FollowersViewModel> { factory }
    lateinit var binding: RecyclerviewBinding
    lateinit var adapter: UserListAdapter

    override fun onCreateContentView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = RecyclerviewBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        adapter = UserListAdapter(requireContext())
        binding.recyclerView.layoutManager = LinearLayoutManager(requireContext())
        binding.recyclerView.adapter = adapter


        viewModel.users.observe(viewLifecycleOwner, Observer {
            when (it) {
                is State.Success -> {
                    hideLoadingView()
                    adapter.users = it.data
                }
                is State.Error -> {
                    requireContext().toast(it.error.message ?: "")
                }
            }
        })


    }

}