package dadm.pokearcade.data.pokemons

import dadm.pokearcade.data.pokemons.model.PokemonDto
import retrofit2.Response

interface PokemonsDataSource {
    suspend fun getPokemon(): Response<PokemonDto>
    suspend fun getPokemonList(): List<Response<PokemonDto>>
}