package dadm.pokearcade.ui.guessPokemon

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import dadm.pokearcade.R
import dadm.pokearcade.databinding.FragmentGuesspokemonBinding

class GuessPokemonFragment : Fragment(R.layout.fragment_guesspokemon) {
    private var _binding: FragmentGuesspokemonBinding? = null
    private val binding get() = _binding!!

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        _binding = FragmentGuesspokemonBinding.bind(view)
    }
}