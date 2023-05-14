package dadm.pokearcade.ui.profile

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import dadm.pokearcade.R
import dadm.pokearcade.databinding.FragmentProfileBinding
import dadm.pokearcade.ui.login.LoginViewModel
import dagger.hilt.android.AndroidEntryPoint

/**
 * Represents the current user information with the username, a default profile picture
 * and the number of games played and won.
 */
@AndroidEntryPoint
class ProfileFragment : Fragment(R.layout.fragment_profile) {
    private var _binding: FragmentProfileBinding? = null
    private val binding get() = _binding!!

    private val loginViewModel: LoginViewModel by activityViewModels()


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        _binding = FragmentProfileBinding.bind(view)
        val user = loginViewModel.user.value

        binding.usernameProfile.text = getString(R.string.username_profile, user?.username)
        binding.totalWins.text = getString(R.string.total_wins, user?.wins)
        binding.totalGames.text = getString(R.string.total_games, user?.games)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}