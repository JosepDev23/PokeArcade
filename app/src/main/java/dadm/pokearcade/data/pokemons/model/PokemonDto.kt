package dadm.pokearcade.data.pokemons.model

import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class PokemonDto(
    val id: String,
    val name: String
)
