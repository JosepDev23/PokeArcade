package dadm.pokearcade.ui.signup

import androidx.lifecycle.*
import dadm.pokearcade.data.users.UsersRepository
import dadm.pokearcade.domain.model.User
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SignupViewModel @Inject constructor(
    var usersRepository: UsersRepository
): ViewModel() {

    private val _signupStatus = MutableLiveData<Result<Unit>>()
    val signupStatus: LiveData<Result<Unit>> get() = _signupStatus

    fun signupUser(username: String, password: String) {
        viewModelScope.launch {
            try {
                usersRepository.postUser(User(username, password, 0, 0))
                _signupStatus.value = Result.success(Unit)
            } catch (e: Exception) {
                _signupStatus.value = Result.failure(e)
            }
        }
    }
}