package dadm.pokearcade.data.users

import dadm.pokearcade.domain.model.User
import kotlinx.coroutines.flow.Flow

interface UsersRepository {

    suspend fun postUser(user: User)

    fun getUserByUsernameAndPassword(username: String, password: String): Flow<User?>

    suspend fun updateUser(user: User): Int
}