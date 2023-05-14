package dadm.pokearcade.ui.signup

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dadm.pokearcade.data.users.UsersRepository
import dadm.pokearcade.domain.model.Difficulty
import dadm.pokearcade.domain.model.User
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject
import kotlin.random.Random

@HiltViewModel
class SignupViewModel @Inject constructor(
    private var usersRepository: UsersRepository
) : ViewModel() {

    private val _signupStatus = MutableLiveData<Result<Unit>>()
    val signupStatus: LiveData<Result<Unit>> get() = _signupStatus

    fun signupUser(username: String, password: String) {
        viewModelScope.launch {
            try {
                usersRepository.postUser(User(Random.nextInt(), username, password, 0, 0, Difficulty.EASY))
                _signupStatus.value = Result.success(Unit)
            } catch (e: Exception) {
                _signupStatus.value = Result.failure(e)
            }
        }
    }
}