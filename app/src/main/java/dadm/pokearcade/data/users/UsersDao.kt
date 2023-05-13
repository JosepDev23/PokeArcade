package dadm.pokearcade.data.users

import androidx.room.*
import dadm.pokearcade.data.users.UsersContract.entries.COLUMN_PASSWORD
import dadm.pokearcade.data.users.UsersContract.entries.COLUMN_USERNAME
import dadm.pokearcade.data.users.UsersContract.entries.TABLE_USERS
import dadm.pokearcade.data.users.model.UserDto
import kotlinx.coroutines.flow.Flow

/**
 * The user DAO. On conflict username, replace strategy.
 */
@Dao
interface UsersDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun postUser(userDto: UserDto)

    @Query("SELECT * FROM $TABLE_USERS WHERE $COLUMN_USERNAME = :username AND $COLUMN_PASSWORD = :password")
    fun getUserByUsernameAndPassword(username: String, password: String): Flow<UserDto?>

    @Update
    suspend fun updateUser(userDto: UserDto): Int
}