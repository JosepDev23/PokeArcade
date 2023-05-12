package dadm.pokearcade.ui.guessPokemon

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dadm.pokearcade.data.pokemons.PokemonsRepository
import kotlinx.coroutines.launch
import javax.inject.Inject

class GuessPokemonViewModel @Inject constructor(
    private val pokemonsRepository: PokemonsRepository
) : ViewModel() {
    private val _error = MutableLiveData<Throwable?>()
    val error: LiveData<Throwable?> = _error

    fun getPokemon() {
        viewModelScope.launch {
            pokemonsRepository.getPokemon().fold(
                onSuccess = {

                },
                onFailure = {
                    _error.value = it
                }
            )
        }
    }

    fun resetError() {
        _error.value = null
    }
}