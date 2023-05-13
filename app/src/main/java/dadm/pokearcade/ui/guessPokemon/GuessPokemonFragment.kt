package dadm.pokearcade.ui.guessPokemon

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions
import com.google.android.material.snackbar.Snackbar
import dadm.pokearcade.R
import dadm.pokearcade.databinding.FragmentGuesspokemonBinding
import dagger.hilt.android.AndroidEntryPoint

/**
 * The UI fragment of the game of guessing the pokemon from a picture.
 */
@AndroidEntryPoint
class GuessPokemonFragment : Fragment(R.layout.fragment_guesspokemon) {
    private var _binding: FragmentGuesspokemonBinding? = null
    private val binding get() = _binding!!

    private val viewModel: GuessPokemonViewModel by viewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        _binding = FragmentGuesspokemonBinding.bind(view)

        binding.btnPokemon1.setOnClickListener {
            viewModel.getPokemon()
        }

        viewModel.pokemon.observe(viewLifecycleOwner) { pokemon ->
            System.out.println("Pokemon: " + pokemon.name)

            Glide.with(binding.root)
                .load(pokemon.image)
                .transition(DrawableTransitionOptions.withCrossFade())
                .into(binding.imagePokemon)
        }

        viewModel.error.observe(viewLifecycleOwner) { error ->
            if (error != null) {
                Snackbar.make(binding.root, error.message.toString(), Snackbar.LENGTH_LONG).show()
                viewModel.resetError()
            }
        }
    }
}