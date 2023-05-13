package dadm.pokearcade.data.pokemons

import dadm.pokearcade.data.pokemons.model.PokemonDto
import okhttp3.MediaType
import okhttp3.ResponseBody
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query
import javax.inject.Inject
import kotlin.random.Random

class PokemonsDataSourceImpl
@Inject constructor(retrofit: Retrofit): PokemonsDataSource {

    private val retrofitPokemonService = retrofit.create(PokemonRetrofit::class.java)

    interface PokemonRetrofit {
        @GET("api/v2/pokemon/{id}") //Número ramdom de 1 a 151 (primera generación)
        suspend fun getPokemon(@Path("id") id: Int): Response<PokemonDto>
    }

    override suspend fun getPokemon(): Response<PokemonDto> {
        val randomPokemonId = Random.nextInt(1, 152) // Generate a random Pokemon Id
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
}