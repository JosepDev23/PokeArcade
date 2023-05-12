package dadm.pokearcade.ui.settings

import android.os.Bundle
import androidx.preference.PreferenceFragmentCompat
import dadm.pokearcade.R
import dagger.hilt.android.AndroidEntryPoint

/**
 * The setting screen with the functionality to change language.
 */
@AndroidEntryPoint
class SettingsFragment : PreferenceFragmentCompat() {
    override fun onCreatePreferences(savedInstanceState: Bundle?, rootKey: String?) {
        setPreferencesFromResource(R.xml.preferences_settings, rootKey)
    }
}