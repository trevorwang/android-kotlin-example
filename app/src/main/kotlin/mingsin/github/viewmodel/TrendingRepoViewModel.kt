package mingsin.github.viewmodel

import androidx.lifecycle.*
import com.orhanobut.logger.Logger
import kotlinx.coroutines.flow.asFlow
import kotlinx.coroutines.launch
import mingsin.github.data.GithubApiService
import mingsin.github.model.Repository
import java.lang.Exception
import javax.inject.Inject


class TrendingRepoViewModel @Inject constructor(private val api: GithubApiService) : ViewModel() {

    var repos: MutableLiveData<List<Repository>> = MutableLiveData()

    private suspend fun loadRepos(page: Int = 0): List<Repository>? {
        try {
            return api.trendingKotlin("created:>2018-12-27", page = page).items
        } catch (e: Exception) {
            Logger.e(e, e.message.toString())
            Result.failure<List<Repository>>(e)
        }
        return null
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