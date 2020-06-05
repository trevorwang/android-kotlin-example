package mingsin.github.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.liveData
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import mingsin.github.model.KeyValue
import mingsin.github.repo.local.LocalRepository
import javax.inject.Inject

class LoginViewModel @Inject constructor(private val repo: LocalRepository) : BaseViewModel() {

    val accessToken = liveData {
        emit(repo.getAccessToken())
    } as MutableLiveData


    fun saveData(block: () -> Unit) {
        viewModelScope.launch {
            accessToken.value?.let {
                repo.saveKeyValue(KeyValue(key = "access_token", value = it))
                block()
            }
        }
    }

}