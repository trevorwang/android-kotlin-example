package mingsin.github.repo

import kotlinx.coroutines.flow.Flow
import mingsin.github.model.Repository
import mingsin.github.model.User
import mingsin.github.model.UserDetail

interface GithubRepository {
    fun trendingRepos(page: Int = 0, perPage: Int = 30): Flow<List<Repository>>
    fun currentUser(): Flow<User>
    fun followers(): Flow<List<User>>
}