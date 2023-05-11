package dadm.pokearcade.data.users

object UsersContract {
    const val DATABASE = "users.db"

    object entries {
        const val TABLE_USERS = "users"
        const val COLUMN_USERNAME = "username"
        const val COLUMN_PASSWORD = "password"
        const val COLUMN_WINS = "wins"
        const val COLUMN_GAMES = "games"
    }
}