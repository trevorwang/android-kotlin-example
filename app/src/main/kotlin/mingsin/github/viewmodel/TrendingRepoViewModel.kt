package mingsin.github.viewmodel

import androidx.lifecycle.*
import kotlinx.coroutines.flow.asFlow
import kotlinx.coroutines.launch
import mingsin.github.data.GithubApiService
import mingsin.github.model.Repository
import javax.inject.Inject


class TrendingRepoViewModel @Inject constructor(private val api: GithubApiService) : ViewModel() {

    var repos: MutableLiveData<List<Repository>> = MutableLiveData(listOf())

    private suspend fun loadRepos(page: Int = 0): List<Repository> {
        return api.trendingKotlin("created:>2018-12-27", page = page).items
    }

    fun loadMore(page: Int = 0) {
        viewModelScope.launch {
            val data = loadRepos(page)
            repos.value = repos.value!! + data
        }
    }

    fun loadData() {
        loadMore()
    }

}