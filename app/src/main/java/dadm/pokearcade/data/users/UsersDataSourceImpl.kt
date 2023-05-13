package dadm.pokearcade.data.users

import dadm.pokearcade.data.users.model.UserDto
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

/**
 * Implement UserDataSource with the CRUD operations.
 */
class UsersDataSourceImpl @Inject constructor(
    private val usersDao: UsersDao
): UsersDataSource{

    /** Register a new user with his DTO. */
    override suspend fun postUser(userDto: UserDto) {
        usersDao.postUser(userDto)
    }

    /** Get an user by his User and Password. */
    override fun getUserByUsernameAndPassword(username: String, password: String): Flow<UserDto?> {
        return usersDao.getUserByUsernameAndPassword(username, password)
    }

    /** Update an user with new info. */
    override suspend fun updateUser(userDto: UserDto): Int {
        return usersDao.updateUser(userDto)
    }

}
