package dadm.pokearcade.ui.login

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dadm.pokearcade.data.users.UsersRepository
import dadm.pokearcade.domain.model.User
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(
    private var usersRepository: UsersRepository
) : ViewModel() {

    private val _user = MutableLiveData<User?>()
    val user: LiveData<User?> get() = _user

    private val _loginStatus = MutableLiveData<Result<Unit>>()
    val loginStatus: LiveData<Result<Unit>> get() = _loginStatus

    fun loginUser(username: String, password: String) {
        viewModelScope.launch {
            try {
                val userFlow = usersRepository.getUserByUsernameAndPassword(username, password)
                userFlow.collect {
                    if (it != null) {
                        _loginStatus.value = Result.success(Unit)
                        _user.value = it
                    } else {
                        _loginStatus.value = Result.failure(Exception("User not found!"))
                    }
                }
            } catch (e: Exception) {
                _loginStatus.value = Result.failure(e)
            }
        }
    }
}