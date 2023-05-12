package dadm.pokearcade.data.pokemons

import dadm.pokearcade.data.pokemons.model.PokemonDto
import dadm.pokearcade.domain.model.Pokemon
import okhttp3.MediaType
import okhttp3.ResponseBody
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.http.GET
import javax.inject.Inject

class PokemonsDataSourceImpl
@Inject constructor(retrofit: Retrofit): PokemonsDataSource {

    private val retrofitPokemonService = retrofit.create(PokemonRetrofit::class.java)

    interface PokemonRetrofit {
        @GET("https://pokeapi.co/api/v2/pokemon/151") //Número ramdom de 1 a 151 (primera generación)
        suspend fun getPokemon(): Response<PokemonDto>
    }

    override suspend fun getPokemon(): Response<PokemonDto> {
        //https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/other/official-artwork/151.png
        return try {
            retrofitPokemonService.getPokemon()
        } catch (e: Exception) {
            Response.error(
                400,
                ResponseBody.create(
                    MediaType.parse("text/plain"), e.toString()
                )
            )
        }
    }
}