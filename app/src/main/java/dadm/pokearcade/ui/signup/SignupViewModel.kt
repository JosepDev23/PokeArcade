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

    fun signupUser(username: String, password: String) {
        viewModelScope.launch {
            usersRepository.postUser(User(username, password, 0, 0))
        }
    }
}