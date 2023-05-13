package dadm.pokearcade.data.pokemons.model

import android.net.Uri
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class PokemonDto(
    val id: String,
    val name: String
)
