package dadm.pokearcade.ui.games

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import dadm.pokearcade.R
import dadm.pokearcade.databinding.FragmentGamesBinding

class GamesFragment: Fragment(R.layout.fragment_games) {
    private var _binding: FragmentGamesBinding? = null
    private val binding get() = _binding!!

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        _binding = FragmentGamesBinding.bind(view)
    }
}