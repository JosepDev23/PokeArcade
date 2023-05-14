package dadm.pokearcade.ui.guessPokemon

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dadm.pokearcade.data.pokemons.PokemonsRepository
import dadm.pokearcade.domain.model.Pokemon
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class GuessPokemonViewModel @Inject constructor(
    private val pokemonsRepository: PokemonsRepository
) : ViewModel() {
    private val _pokemon = MutableLiveData<Pokemon>()
    private val _pokemonList = MutableLiveData<List<Pokemon>>()
    private val _error = MutableLiveData<Throwable?>()
    private val _restartGame = MutableLiveData<Boolean>()

    val pokemon: LiveData<Pokemon> = _pokemon
    val pokemonList: LiveData<List<Pokemon>> = _pokemonList
    val error: LiveData<Throwable?> = _error
    val restartGame: LiveData<Boolean> = _restartGame

    fun getPokemonList() {
        viewModelScope.launch {
            _pokemonList.value = pokemonsRepository.getPokemonList()
                .map { it.getOrThrow() }
            _pokemon.value = _pokemonList.value?.random()
        }
    }

    fun restartGame() {
        viewModelScope.launch {
            _restartGame.value = true
        }
    }

    fun resetRestartGame() {
        viewModelScope.launch {
            _restartGame.value = false
        }
    }

    fun resetError() {
        _error.value = null
    }

}