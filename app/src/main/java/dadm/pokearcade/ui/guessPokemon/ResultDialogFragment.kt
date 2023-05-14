package dadm.pokearcade.ui.guessPokemon

import android.app.AlertDialog
import android.app.Dialog
import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.KeyEvent
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.activityViewModels
import dadm.pokearcade.R

class ResultDialogFragment (private val isCorrect: Boolean, private val pokemonName: String, private val viewModel: GuessPokemonViewModel) : DialogFragment() {

    //private val viewModel: GuessPokemonViewModel by activityViewModels()

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        val title = if (isCorrect) R.string.correctAnswer else R.string.incorrectAnswer
        val message = if (isCorrect) getString(R.string.correctMessage) else getString(R.string.incorrectMessage,pokemonName)
        val builder = AlertDialog.Builder(requireContext())
            .setTitle(title)
            .setMessage(message)
            .setCancelable(false)
            .setPositiveButton(R.string.dialogContinue) { dialog, _ ->
                viewModel.restartGame()
            }
            .setNegativeButton(R.string.dialogExit) { _, _ ->
                requireActivity().finish()
            }

        val dialogo = builder.create()
        dialogo.setCanceledOnTouchOutside(false)
        dialogo.setCancelable(false)
        dialogo.setOnKeyListener { dialog, keyCode, event ->
            // Prevenir el cierre del diálogo al presionar el botón de retroceso
            keyCode == KeyEvent.KEYCODE_BACK
        }
        return dialogo
    }
}