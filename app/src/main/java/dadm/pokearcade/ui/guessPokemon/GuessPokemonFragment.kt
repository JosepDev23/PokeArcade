package dadm.pokearcade.ui.guessPokemon

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions
import com.google.android.material.snackbar.Snackbar
import dadm.pokearcade.R
import dadm.pokearcade.databinding.FragmentGuesspokemonBinding
import dadm.pokearcade.domain.model.Pokemon
import dagger.hilt.android.AndroidEntryPoint

/**
 * The UI fragment of the game of guessing the Pokémon from a picture.
 */
@AndroidEntryPoint
class GuessPokemonFragment : Fragment(R.layout.fragment_guesspokemon) {
    private var _binding: FragmentGuesspokemonBinding? = null
    private val binding get() = _binding!!
    private val _answerPokemonName: MutableLiveData<String> = MutableLiveData()
    val answerPokemonName: LiveData<String> = _answerPokemonName

    private val viewModel: GuessPokemonViewModel by viewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        _binding = FragmentGuesspokemonBinding.bind(view)

        viewModel.getPokemonList()

        viewModel.pokemon.observe(viewLifecycleOwner) { pokemon ->
            System.out.println("Pokemon: " + pokemon.name)
            _answerPokemonName.value = pokemon.name
            // Load the Pokémon image using Glide
            Glide.with(this)
                .load(pokemon.image)
                .transition(DrawableTransitionOptions.withCrossFade())
                .into(binding.imagePokemon)
        }

        viewModel.pokemonList.observe(viewLifecycleOwner) { pokemonNames ->
            // Shuffle the list of Pokémon names
            val shuffledNames = pokemonNames.shuffled()

            // Set the names on the buttons
            binding.btnPokemon1.text = shuffledNames[0].name
            binding.btnPokemon2.text = shuffledNames[1].name
            binding.btnPokemon3.text = shuffledNames[2].name
            binding.btnPokemon4.text = shuffledNames[3].name
        }

        binding.btnPokemon1.setOnClickListener {
            if (_answerPokemonName.value == binding.btnPokemon1.text) Snackbar.make(
                binding.root,
                "Correct!",
                Snackbar.LENGTH_LONG
            ).show()
            else Snackbar.make(binding.root, "Incorrect!", Snackbar.LENGTH_LONG).show()
        }

        binding.btnPokemon2.setOnClickListener {
            if (_answerPokemonName.value == binding.btnPokemon2.text) Snackbar.make(
                binding.root,
                "Correct!",
                Snackbar.LENGTH_LONG
            ).show()
            else Snackbar.make(binding.root, "Incorrect!", Snackbar.LENGTH_LONG).show()
        }

        binding.btnPokemon3.setOnClickListener {
            if (_answerPokemonName.value== binding.btnPokemon3.text) Snackbar.make(
                binding.root,
                "Correct!",
                Snackbar.LENGTH_LONG
            ).show()
            else Snackbar.make(binding.root, "Incorrect!", Snackbar.LENGTH_LONG).show()
        }

        binding.btnPokemon4.setOnClickListener {
            if (_answerPokemonName.value == binding.btnPokemon4.text) Snackbar.make(
                binding.root,
                "Correct!",
                Snackbar.LENGTH_LONG
            ).show()
            else Snackbar.make(binding.root, "Incorrect!", Snackbar.LENGTH_LONG).show()
        }

        viewModel.error.observe(viewLifecycleOwner) { error ->
            if (error != null) {
                Snackbar.make(binding.root, error.message.toString(), Snackbar.LENGTH_LONG).show()
                viewModel.resetError()
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
