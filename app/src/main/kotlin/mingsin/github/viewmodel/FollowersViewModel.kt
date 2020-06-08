package mingsin.github.viewmodel

import androidx.lifecycle.liveData
import kotlinx.coroutines.flow.collect
import mingsin.github.repo.GithubRepository
import javax.inject.Inject


class FollowersViewModel @Inject constructor(repo: GithubRepository) : BaseViewModel() {
    val users = liveData {
        request { repo.followers() }
                .collect {
                    emit(it)
                }
    }
}