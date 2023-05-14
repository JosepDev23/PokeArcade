package dadm.pokearcade.ui.pokequiz

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import dadm.pokearcade.R
import dadm.pokearcade.databinding.FragmentPokequizBinding
import dadm.pokearcade.domain.model.Quiz
import dadm.pokearcade.ui.login.LoginViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

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
    private val loginViewModel: LoginViewModel by activityViewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        _binding = FragmentPokequizBinding.bind(view)

        _quiz = viewModel.getRandomQuiz(loginViewModel.user.value!!.difficulty)
        binding.questionTextView.text = quiz.question

        binding.submitButton.setOnClickListener {
            if (binding.answerTextInputEditText.text.toString().lowercase() == quiz.response.lowercase()) {
                lifecycleScope.launch {
                    loginViewModel.handleVictory()
                }
                val pokequizResultDialogFragment = PokequizResultDialogFragment(true, quiz.response, viewModel)
                pokequizResultDialogFragment.show(parentFragmentManager, "PokeQuiz result")
            } else {
                lifecycleScope.launch {
                    loginViewModel.handleLose()
                }
                val pokequizResultDialogFragment = PokequizResultDialogFragment(false, quiz.response, viewModel)
                pokequizResultDialogFragment.show(parentFragmentManager, "PokeQuiz result")
            }
        }

        viewModel.restartGame.observe(viewLifecycleOwner) {
            restartGame()
        }
    }

    private fun restartGame() {
        _quiz = viewModel.getRandomQuiz(loginViewModel.user.value!!.difficulty)
        binding.questionTextView.text = quiz.question
    }
}