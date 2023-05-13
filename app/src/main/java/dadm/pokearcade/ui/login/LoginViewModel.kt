package dadm.pokearcade.ui.login

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import dadm.pokearcade.data.users.UsersRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(
    var usersRepository: UsersRepository
) : ViewModel() {

    private val _loginStatus = MutableLiveData<Result<Unit>>()
    val loginStatus: LiveData<Result<Unit>> get() = _loginStatus

    fun loginUser(username: String, password: String) {
        try {
            usersRepository.getUserByUsernameAndPassword(username, password)
            _loginStatus.value = Result.success(Unit)
        } catch (e: Exception) {
            _loginStatus.value = Result.failure(e)
        }
    }
}