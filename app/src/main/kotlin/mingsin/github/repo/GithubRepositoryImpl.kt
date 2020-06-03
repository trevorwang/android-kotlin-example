package mingsin.github.repo

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import mingsin.github.data.GithubApiService
import mingsin.github.model.Repository
import javax.inject.Inject


class GithubRepositoryImpl @Inject constructor(val api: GithubApiService) : GithubRepository {
    override fun trendingRepos(page: Int): Flow<List<Repository>> {
        return flow {
            val items = api.trending("created:>2018-12-27", page = page).items
            emit(items)
        }
    }

}