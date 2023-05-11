package dadm.pokearcade.ui.games

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import dadm.pokearcade.R
import dadm.pokearcade.databinding.FragmentGamesBinding
import dadm.pokearcade.ui.login.LoginFragmentDirections

/**
 * The home screen of the app. It contains the navigation
 * to the games.
 */
class GamesFragment: Fragment(R.layout.fragment_games) {
    private var _binding: FragmentGamesBinding? = null
    private val binding get() = _binding!!

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        _binding = FragmentGamesBinding.bind(view)

        binding.btnWordle.setOnClickListener {
            val action = GamesFragmentDirections.actionGamesFragmentToWordleFragment()
            findNavController().navigate(action)
        }
        binding.btnGuessImage.setOnClickListener {
            val action = GamesFragmentDirections.actionGamesFragmentToGuessPokemonFragment()
            findNavController().navigate(action)
        }
    }
}