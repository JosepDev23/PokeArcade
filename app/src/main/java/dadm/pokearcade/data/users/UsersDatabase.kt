package dadm.pokearcade.data.users

import androidx.room.Database
import androidx.room.RoomDatabase
import dadm.pokearcade.data.users.model.UserDto

@Database(entities = [UserDto::class], version = 1)
abstract class UsersDatabase : RoomDatabase() {
    abstract fun usersDao(): UsersDao
}