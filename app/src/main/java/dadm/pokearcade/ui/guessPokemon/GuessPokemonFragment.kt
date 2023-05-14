package dadm.pokearcade.ui.guessPokemon

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions
import com.google.android.material.snackbar.Snackbar
import dadm.pokearcade.R
import dadm.pokearcade.databinding.FragmentGuesspokemonBinding
import dadm.pokearcade.ui.login.LoginViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

/**
 * The UI fragment of the game of guessing the Pokemon from a picture.
 */
@AndroidEntryPoint
class GuessPokemonFragment : Fragment(R.layout.fragment_guesspokemon) {
    private var _binding: FragmentGuesspokemonBinding? = null
    private val binding get() = _binding!!
    private val _answerPokemonName: MutableLiveData<String> = MutableLiveData()

    private val viewModel: GuessPokemonViewModel by viewModels()
    private val loginViewModel: LoginViewModel by activityViewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        _binding = FragmentGuesspokemonBinding.bind(view)

        viewModel.getPokemonList(loginViewModel.user.value!!.difficulty)

        viewModel.pokemon.observe(viewLifecycleOwner) { pokemon ->
            _answerPokemonName.value = pokemon.name
            // Load the Pokemon image using Glide
            Glide.with(this)
                .load(pokemon.image)
                .transition(DrawableTransitionOptions.withCrossFade())
                .into(binding.imagePokemon)
        }

        viewModel.pokemonList.observe(viewLifecycleOwner) { pokemonNames ->
            // Shuffle the list of Pokemon names
            val shuffledNames = pokemonNames.shuffled()

            // Set the names on the buttons
            binding.btnPokemon1.text = shuffledNames[0].name
            binding.btnPokemon2.text = shuffledNames[1].name
            binding.btnPokemon3.text = shuffledNames[2].name
            binding.btnPokemon4.text = shuffledNames[3].name
        }

        binding.btnPokemon1.setOnClickListener {
            val isCorrect = _answerPokemonName.value == binding.btnPokemon1.text
            showResultDialog(isCorrect, _answerPokemonName.value.toString(), viewModel)
        }

        binding.btnPokemon2.setOnClickListener {
            val isCorrect = _answerPokemonName.value == binding.btnPokemon2.text
            showResultDialog(isCorrect, _answerPokemonName.value.toString(), viewModel)
        }

        binding.btnPokemon3.setOnClickListener {
            val isCorrect = _answerPokemonName.value == binding.btnPokemon3.text
            showResultDialog(isCorrect, _answerPokemonName.value.toString(), viewModel)
        }

        binding.btnPokemon4.setOnClickListener {
            val isCorrect = _answerPokemonName.value == binding.btnPokemon4.text
            showResultDialog(isCorrect, _answerPokemonName.value.toString(), viewModel)
        }

        viewModel.restartGame.observe(viewLifecycleOwner) { restartGame ->
            if (restartGame) {
                restartGame()
                viewModel.resetRestartGame()
            }
        }

        viewModel.error.observe(viewLifecycleOwner) { error ->
            if (error != null) {
                Snackbar.make(binding.root, error.message.toString(), Snackbar.LENGTH_LONG).show()
                viewModel.resetError()
            }

        }
    }

    private fun showResultDialog(
        isCorrect: Boolean,
        pokemonName: String,
        viewModel: GuessPokemonViewModel
    ) {
        if (isCorrect) {
            lifecycleScope.launch {
                loginViewModel.handleVictory()
            }
        } else {
            lifecycleScope.launch {
                loginViewModel.handleLose()
            }
        }
        val guessPokemonResultDialogFragment =
            GuessPokemonResultDialogFragment(isCorrect, pokemonName, viewModel)
        guessPokemonResultDialogFragment.show(parentFragmentManager, "ResultDialog")
    }

    private fun restartGame() {
        viewModel.getPokemonList()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
