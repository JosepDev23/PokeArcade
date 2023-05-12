package dadm.pokearcade.data.pokemons

import dadm.pokearcade.domain.model.Pokemon
import javax.inject.Inject

class PokemonsRepositoryImpl
@Inject constructor(private val connectivityChecker: ConnectivityChecker)
    : PokemonsRepository {
    override suspend fun getPokemon(): Result<Pokemon> {
        TODO("Not yet implemented")
        /*return if (connectivityChecker.isConnectionAvailable()) {

        } else {
            Result.failure(NoInternetException("No hay acceso a Internet").toString())
        }*/
    }
}