package dadm.pokearcade.ui.pokequiz

import android.app.AlertDialog
import android.app.Dialog
import android.os.Bundle
import android.view.KeyEvent
import androidx.fragment.app.DialogFragment
import androidx.navigation.fragment.findNavController
import dadm.pokearcade.R
import dadm.pokearcade.ui.guessPokemon.GuessPokemonFragmentDirections

class PokequizResultDialogFragment(
    private val isCorrect: Boolean,
    private val correctAnswer: String,
    private val viewModel: PokequizViewModel
) : DialogFragment() {
    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        val title = if (isCorrect) R.string.correctAnswer else R.string.incorrectAnswer
        val message = if (isCorrect) getString(R.string.correctMessage) else getString(
            R.string.incorrectAnswerMessage,
            correctAnswer
        )
        val builder = AlertDialog.Builder(requireContext())
            .setTitle(title)
            .setMessage(message)
            .setCancelable(false)
            .setPositiveButton(R.string.dialogContinue) { _, _ ->
                viewModel.restartGame()
            }
            .setNegativeButton(R.string.dialogExit) { _, _ ->
                val action = PokequizFragmentDirections.actionPokequizFragmentToGamesFragment()
                findNavController().navigate(action)
            }

        val dialog = builder.create()
        dialog.setCanceledOnTouchOutside(false)
        dialog.setCancelable(false)
        dialog.setOnKeyListener { _, keyCode, _ ->
            // You cant close the dialog through back button
            keyCode == KeyEvent.KEYCODE_BACK
        }
        return dialog
    }
}