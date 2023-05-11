package dadm.pokearcade.ui.wordle

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import dadm.pokearcade.R
import dadm.pokearcade.databinding.FragmentWordleBinding

class WordleFragment : Fragment(R.layout.fragment_wordle) {
    private var _binding: FragmentWordleBinding? = null
    private val binding get() = _binding!!

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        _binding = FragmentWordleBinding.bind(view)
    }
}