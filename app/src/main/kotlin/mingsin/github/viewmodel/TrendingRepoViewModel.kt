package mingsin.github.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.liveData
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import mingsin.github.model.State
import mingsin.github.model.Success
import mingsin.github.repo.GithubRepository
import javax.inject.Inject


class TrendingRepoViewModel @Inject constructor(private val repo: GithubRepository) : BaseViewModel() {

    val repos = liveData {
        request {
            repo.trendingRepos(0)
        }.collect {
            emit(it)
        }
    } as MutableLiveData

    fun loadData(page: Int = 0) {
        viewModelScope.launch {
            request {
                repo.trendingRepos(page)
            }.collect {
                if (it is Success) {
                    val data = repos.value
                    if (data is Success) {
                        repos.postValue(State.success(data.data + it.data))
                    }
                }
            }
        }
    }
}