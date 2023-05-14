package dadm.pokearcade.data.pokemons

import dadm.pokearcade.data.pokemons.model.toDomain
import dadm.pokearcade.domain.model.Pokemon
import dadm.pokearcade.utils.NoInternetException
import javax.inject.Inject

class PokemonsRepositoryImpl
@Inject constructor(
    private val connectivityChecker: ConnectivityChecker,
    private val pokemonsDataSource: PokemonsDataSource
) : PokemonsRepository {
    override suspend fun getPokemon(): Result<Pokemon> {
        return if (connectivityChecker.isConnectionAvailable()) {
            pokemonsDataSource.getPokemon().toDomain()
        } else {
            Result.failure(NoInternetException())
        }
    }

    override suspend fun getPokemonList(): List<Result<Pokemon>> {
        return if (connectivityChecker.isConnectionAvailable()) {
            pokemonsDataSource.getPokemonList().map { it.toDomain() }
        } else {
            error(NoInternetException())
        }
    }
}