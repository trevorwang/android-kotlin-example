package mingsin.github.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import mingsin.github.data.GithubApiService
import mingsin.github.model.Repository
import javax.inject.Inject


class TrendingRepoViewModel @Inject constructor(private val api: GithubApiService) : BaseViewModel() {

    var repos: MutableLiveData<List<Repository>> = MutableLiveData()

    private suspend fun loadRepos(page: Int = 0): List<Repository>? {
        return handleExceptions {
            api.trendingKotlin("created:>2018-12-27", page = page).items
        }
    }

    fun loadData(page: Int = 0) {
        viewModelScope.launch {
            val data = loadRepos(page)
            if (repos.value == null) {
                repos.value = data
            } else {
                data?.let {
                    repos.value = repos.value!! + it
                }
            }
        }
    }
}