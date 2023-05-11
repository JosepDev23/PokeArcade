package dadm.pokearcade.data.users

import dadm.pokearcade.data.users.model.toDomain
import dadm.pokearcade.data.users.model.toDto
import dadm.pokearcade.domain.model.User
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class UsersRepositoryImpl @Inject constructor(
    private val usersDataSource: UsersDataSource
): UsersRepository {

    override suspend fun postUser(user: User) {
        usersDataSource.postUser(user.toDto())
    }

    override fun getUserByUsernameAndPassword(username: String, password: String): Flow<User?> {
        return usersDataSource.getUserByUsernameAndPassword(username, password).map {
            it?.toDomain()
        }
    }

    override suspend fun updateUser(user: User): Int {
        return usersDataSource.updateUser(user.toDto())
    }
}