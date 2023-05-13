package dadm.pokearcade.ui.pokequiz

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import dadm.pokearcade.R
import dadm.pokearcade.databinding.FragmentPokequizBinding
import dadm.pokearcade.domain.model.Quiz
import dagger.hilt.android.AndroidEntryPoint

/**
 * The UI fragment of the Pokemon Wordle game. It contains the logic to
 * guess the world in six tries.
 */
@AndroidEntryPoint
class PokequizFragment : Fragment(R.layout.fragment_pokequiz) {
    private var _binding: FragmentPokequizBinding? = null
    private val binding get() = _binding!!

    private var _quiz: Quiz? = null
    private val quiz get() = _quiz!!

    private val viewModel: PokequizViewModel by viewModels()


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        _binding = FragmentPokequizBinding.bind(view)

        _quiz = viewModel.getRandomQuiz()
    }
}