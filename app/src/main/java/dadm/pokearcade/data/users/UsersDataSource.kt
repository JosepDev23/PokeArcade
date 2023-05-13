package dadm.pokearcade.data.users

import dadm.pokearcade.data.users.model.UserDto
import kotlinx.coroutines.flow.Flow

/**
 * The User Data Source with his CRUD methods.
 */
interface UsersDataSource {

    suspend fun postUser(userDto: UserDto)

    fun getUserByUsernameAndPassword(username: String, password: String): Flow<UserDto?>

    suspend fun updateUser(userDto: UserDto): Int

}
