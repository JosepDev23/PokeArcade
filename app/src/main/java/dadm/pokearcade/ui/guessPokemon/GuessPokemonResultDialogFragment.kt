package dadm.pokearcade.ui.guessPokemon

import android.app.AlertDialog
import android.app.Dialog
import android.os.Bundle
import android.view.KeyEvent
import androidx.fragment.app.DialogFragment
import androidx.navigation.fragment.findNavController
import dadm.pokearcade.R

class GuessPokemonResultDialogFragment(
    private val isCorrect: Boolean,
    private val pokemonName: String,
    private val viewModel: GuessPokemonViewModel
) : DialogFragment() {

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        val title = if (isCorrect) R.string.correctAnswer else R.string.incorrectAnswer
        val message = if (isCorrect) getString(R.string.correctMessage) else getString(
            R.string.incorrectMessage,
            pokemonName
        )
        val builder = AlertDialog.Builder(requireContext())
            .setTitle(title)
            .setMessage(message)
            .setCancelable(false)
            .setPositiveButton(R.string.dialogContinue) { _, _ ->
                viewModel.restartGame()
            }
            .setNegativeButton(R.string.dialogExit) { _, _ ->
                val action = GuessPokemonFragmentDirections.actionGuessPokemonFragmentToGamesFragment()
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