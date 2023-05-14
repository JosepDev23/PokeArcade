package dadm.pokearcade.ui.games

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import dadm.pokearcade.R
import dadm.pokearcade.databinding.FragmentGamesBinding
import dadm.pokearcade.ui.login.LoginViewModel
import dagger.hilt.android.AndroidEntryPoint

/**
 * The home screen of the app. It contains the navigation
 * to the games.
 */
@AndroidEntryPoint
class GamesFragment : Fragment(R.layout.fragment_games) {
    private var _binding: FragmentGamesBinding? = null
    private val binding get() = _binding!!

    private val loginViewModel: LoginViewModel by activityViewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        _binding = FragmentGamesBinding.bind(view)

        loginViewModel.user.observe(viewLifecycleOwner) { user ->
            if (user != null) {
                binding.welcomeText.text = getString(R.string.welcome_msg, user.username)

                binding.difficultyText.text = getString(R.string.difficultyInfo, user.difficulty.toString())
            } else {
                binding.welcomeText.text =
                    getString(R.string.userNotLoggedIn)
            }
        }

        binding.btnPokequiz.setOnClickListener {
            val action = GamesFragmentDirections.actionGamesFragmentToWordleFragment()
            findNavController().navigate(action)
        }
        binding.btnGuessImage.setOnClickListener {
            val action = GamesFragmentDirections.actionGamesFragmentToGuessPokemonFragment()
            findNavController().navigate(action)
        }
    }
}