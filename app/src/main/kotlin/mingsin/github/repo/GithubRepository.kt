package mingsin.github.repo

import kotlinx.coroutines.flow.Flow
import mingsin.github.model.Repository

interface GithubRepository {
    fun trendingRepos(page: Int = 0): Flow<List<Repository>>
}