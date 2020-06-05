package mingsin.github.repo

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.withContext
import mingsin.github.data.GithubApiService
import mingsin.github.model.Repository
import mingsin.github.model.User
import mingsin.github.model.UserDetail
import javax.inject.Inject


class GithubRepositoryImpl @Inject constructor(private val api: GithubApiService) : GithubRepository {
    override fun trendingRepos(page: Int, perPage: Int): Flow<List<Repository>> {
        return flow {
            val items = withContext(Dispatchers.IO) {
                api.trending("created:>2018-12-27", page = page, perPage = perPage).items
            }
            emit(items)
        }
    }

    override fun currentUser(): Flow<User> {
        return flow {
            val user = withContext(Dispatchers.IO) {
                api.currentUser()
            }
            emit(user)
        }
    }

    override fun followers(): Flow<List<User>> {
        return flow {
            emit(withContext(Dispatchers.IO) {
                api.followers()
            })
        }
    }

}