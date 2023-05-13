package dadm.pokearcade.data.users.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import dadm.pokearcade.data.users.UsersContract.entries.COLUMN_GAMES
import dadm.pokearcade.data.users.UsersContract.entries.COLUMN_PASSWORD
import dadm.pokearcade.data.users.UsersContract.entries.COLUMN_USERNAME
import dadm.pokearcade.data.users.UsersContract.entries.COLUMN_WINS
import dadm.pokearcade.data.users.UsersContract.entries.TABLE_USERS

/**
 * The userDTO with his four columns.
 * The username and password to login, and the numer of games played and won.
 */
@Entity(tableName = TABLE_USERS)
data class UserDto(
    @PrimaryKey
    @ColumnInfo(name = COLUMN_USERNAME)
    val username: String,

    @ColumnInfo(name = COLUMN_PASSWORD)
    val password: String,

    @ColumnInfo(name = COLUMN_WINS)
    val wins: Int,

    @ColumnInfo(name = COLUMN_GAMES)
    val games: Int
)
