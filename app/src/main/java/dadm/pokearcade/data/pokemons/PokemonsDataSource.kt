package dadm.pokearcade.data.pokemons

import dadm.pokearcade.data.pokemons.model.PokemonDto
import dadm.pokearcade.domain.model.Difficulty
import retrofit2.Response

interface PokemonsDataSource {
    suspend fun getPokemon(): Response<PokemonDto>
    suspend fun getPokemonList(difficulty: Difficulty): List<Response<PokemonDto>>
}