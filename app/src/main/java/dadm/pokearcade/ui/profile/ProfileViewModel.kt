package dadm.pokearcade.ui.profile

import androidx.lifecycle.ViewModel
import dadm.pokearcade.data.users.UsersRepository
import javax.inject.Inject

class ProfileViewModel @Inject constructor(
    usersRepository: UsersRepository
) : ViewModel()