package mingsin.github.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import mingsin.github.model.State
import mingsin.github.repo.GithubRepository
import javax.inject.Inject


class TrendingRepoViewModel @Inject constructor(private val repo: GithubRepository) : BaseViewModel() {

    val repos =
            request { repo.trendingRepos() }.asLiveData() as MutableLiveData


    fun loadData(page: Int = 0, perPage: Int = 30) {
        viewModelScope.launch {
            request {
                repo.trendingRepos(page)
            }.collect {
                if (it is State.Success) {
                    val data = repos.value
                    if (data is State.Success) {
                        repos.postValue(State.success(data.data + it.data))
                    }
                }
            }
        }
    }
}