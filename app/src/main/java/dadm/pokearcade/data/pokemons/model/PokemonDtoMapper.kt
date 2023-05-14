package dadm.pokearcade.data.pokemons.model

import dadm.pokearcade.domain.model.Pokemon
import retrofit2.Response
import java.io.IOException

fun PokemonDto.toDomain(): Pokemon = Pokemon(
    id = id,
    name = name,
    image = "https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/other/official-artwork/$id.png"
)

fun Response<PokemonDto>.toDomain() =
    if (isSuccessful) Result.success((body() as PokemonDto).toDomain())
    else Result.failure(IOException())