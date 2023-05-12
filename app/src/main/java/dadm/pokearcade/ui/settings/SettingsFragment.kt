package dadm.pokearcade.ui.settings

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import dadm.pokearcade.R
import dadm.pokearcade.databinding.FragmentSettingsBinding

/**
 * The setting screen with the functionality to change language.
 */
class SettingsFragment : Fragment(R.layout.fragment_settings) {
    private var _binding: FragmentSettingsBinding? = null
    private val binding get() = _binding!!

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        _binding = FragmentSettingsBinding.bind(view)
    }
}