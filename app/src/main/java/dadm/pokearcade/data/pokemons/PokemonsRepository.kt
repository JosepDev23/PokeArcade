package dadm.pokearcade.data.pokemons

import dadm.pokearcade.domain.model.Difficulty
import dadm.pokearcade.domain.model.Pokemon

interface PokemonsRepository {
    suspend fun getPokemon(): Result<Pokemon>
    suspend fun getPokemonList(difficulty: Difficulty): List<Result<Pokemon>>
}