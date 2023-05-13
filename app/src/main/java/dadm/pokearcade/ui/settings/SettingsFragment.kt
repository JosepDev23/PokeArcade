package dadm.pokearcade.ui.settings

import android.content.SharedPreferences
import android.content.res.Configuration
import android.os.Bundle
import androidx.preference.PreferenceFragmentCompat
import androidx.preference.PreferenceManager
import dadm.pokearcade.R
import dagger.hilt.android.AndroidEntryPoint
import java.util.*

/**
 * The setting screen with the functionality to change language.
 */
@AndroidEntryPoint
class SettingsFragment : PreferenceFragmentCompat(), SharedPreferences.OnSharedPreferenceChangeListener {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        PreferenceManager.getDefaultSharedPreferences(requireContext())
            .registerOnSharedPreferenceChangeListener(this)
    }

    override fun onDestroy() {
        super.onDestroy()
        PreferenceManager.getDefaultSharedPreferences(requireContext())
            .unregisterOnSharedPreferenceChangeListener(this)
    }

    override fun onCreatePreferences(savedInstanceState: Bundle?, rootKey: String?) {
        setPreferencesFromResource(R.xml.preferences_settings, rootKey)
    }

    override fun onSharedPreferenceChanged(sharedPreferences: SharedPreferences?, key: String?) {
        if (key == "language") {
            val language = sharedPreferences?.getString("language", "en")
            setLocale(language)
        }
    }

    private fun setLocale(language: String?) {
        language?.let {
            val locale = Locale(it)
            Locale.setDefault(locale)
            val config = Configuration()
            config.setLocale(locale)
            requireContext().resources.updateConfiguration(config, requireContext().resources.displayMetrics)
            requireActivity().recreate() // Actualiza la actividad para aplicar los cambios de idioma
        }
    }
}