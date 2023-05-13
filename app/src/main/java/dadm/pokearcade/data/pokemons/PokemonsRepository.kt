package dadm.pokearcade.data.pokemons

import dadm.pokearcade.domain.model.Pokemon

interface PokemonsRepository {
    suspend fun getPokemon(): Result<Pokemon>
    suspend fun getPokemonList(): List<Result<Pokemon>>
}