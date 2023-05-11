package dadm.pokearcade.domain.model

data class User(
    val username: String,
    val password: String,
    val wins: Int,
    val games: Int
)
