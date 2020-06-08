package mingsin.github.viewmodel

import androidx.lifecycle.liveData
import kotlinx.coroutines.flow.collect
import mingsin.github.model.State
import mingsin.github.repo.GithubRepository
import javax.inject.Inject

class DrawerViewModel @Inject constructor(repo: GithubRepository) : BaseViewModel() {

    val user = liveData {

        request { repo.currentUser() }.collect {
            if (it is State.Success) {
                emit(it.data)
            }
        }
    }


}