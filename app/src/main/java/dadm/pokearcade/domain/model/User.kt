package dadm.pokearcade.domain.model

data class User(
    var id: Int,
    var username: String,
    val password: String,
    val wins: Int,
    val games: Int
)
