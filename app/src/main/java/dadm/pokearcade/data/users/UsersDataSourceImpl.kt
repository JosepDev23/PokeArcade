package dadm.pokearcade.data.users

import dadm.pokearcade.data.users.model.UserDto
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class UsersDataSourceImpl @Inject constructor(
    private val usersDao: UsersDao
): UsersDataSource{

    override suspend fun postUser(userDto: UserDto) {
        usersDao.postUser(userDto)
    }

    override fun getUserByUsernameAndPassword(username: String, password: String): Flow<UserDto?> {
        return usersDao.getUserByUsernameAndPassword(username, password)
    }

    override suspend fun updateUser(userDto: UserDto): Int {
        return usersDao.updateUser(userDto)
    }

}
