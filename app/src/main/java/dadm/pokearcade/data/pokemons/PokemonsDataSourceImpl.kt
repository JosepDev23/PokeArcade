package dadm.pokearcade.data.pokemons

import android.util.Log
import dadm.pokearcade.data.pokemons.model.PokemonDto
import dadm.pokearcade.domain.model.Difficulty
import okhttp3.MediaType
import okhttp3.ResponseBody
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.http.GET
import retrofit2.http.Path
import javax.inject.Inject
import kotlin.random.Random
class PokemonsDataSourceImpl
@Inject constructor(retrofit: Retrofit) : PokemonsDataSource {

    private val retrofitPokemonService = retrofit.create(PokemonRetrofit::class.java)

    interface PokemonRetrofit {
        @GET("api/v2/pokemon/{id}")
        suspend fun getPokemon(@Path("id") id: Int): Response<PokemonDto>
    }

    override suspend fun getPokemon(): Response<PokemonDto> {
        val randomPokemonId = Random.nextInt(1, 152) // Generate a random Pokemon Id from 1st gen
        return try {
            retrofitPokemonService.getPokemon(randomPokemonId)
        } catch (e: Exception) {
            System.err.println(e.toString())
            Response.error(
                400,
                ResponseBody.create(MediaType.parse("text/plain"), e.toString())
            )
        }
    }

    override suspend fun getPokemonList(difficulty: Difficulty): List<Response<PokemonDto>> {
        var count = 0
        val limit = 4
        val pokemonList = mutableListOf<Response<PokemonDto>>()



        while (count < limit) {
            val randomPokemonId = getRandomPokemonId(difficulty) // Generate a random Pokemon Id
            try {
                pokemonList.add(retrofitPokemonService.getPokemon(randomPokemonId))
            } catch (e: Exception) {
                System.err.println(e.toString())
                return emptyList()
            }
            count++
        }
        Log.d("pokemonList", pokemonList.toString())
        return pokemonList
    }

    private fun getRandomPokemonId(difficulty: Difficulty): Int {
        val maxPokemonId = when (difficulty) {
            Difficulty.EASY -> 151 // Only 151 Pokémon in the first generation
            Difficulty.NORMAL -> 151+100+135+107 // Until 4 generation included
            Difficulty.HARD -> 1008 // All
        }
        return Random.nextInt(1, maxPokemonId + 1)
    }
}